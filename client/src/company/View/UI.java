package company.View;
import company.Models.*;
import company.Controllers.MasterController;
import javafx.util.Pair;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class UI {
    public UI(MasterController controller) {
        this.controller = controller;
    }
    private MasterController controller;
    public void Run() throws IOException, SQLException, JAXBException, NotBoundException {
        int choice = 0;
        while(choice != 5) {
            choice = printMenu();
            switch (choice) {
                case 1:
                {
                    try {
                        printEmplyess(controller.getEmployees());
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 2:
                {
                    try {
                        IPracownik employee = getEmplyee();
                        controller.addEmployee(employee);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3:
                {
                    try {
                        String result = getPesel();
                        IPracownik pr = new IPracownik();
                        pr.setPesel(result);
                        controller.removeEmployee(pr);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4:
                {
                    askRemote();
                    break;
                }
                case 5:
                {
                    System.exit(0);
                    break;
                }
                default:
                    break;
            }
        }
    }

    private void askRemote() throws IOException, SQLException, NotBoundException, JAXBException {
        Pair<String, String> id = null;
        IPracownikList a = null;
        Pair<String, Integer> host = getIpAndPort();
        if(controller.networkController.getConnector().needAuthentication())
            id = getLoginAndPassword();
        try {
            System.out.print("Ustanawianie polaczenia...");
            controller.networkController.getConnector().connect(host.getKey(),
                    host.getValue());
            System.out.println("Sukces");

        }
        catch (Exception e) {
            System.out.println("Fail");
            throw e;
        }
        try {
            if (controller.networkController.getConnector().needAuthentication()) {
                System.out.print("Autentykacja...");
                controller.networkController.getConnector().authenticate(
                        id.getKey(), id.getValue()
                );
                System.out.println("Sukces");

            }
        }
        catch (Exception e) {
            System.out.println("Fail");
        }
        try {
            System.out.print("Pobieranie...");
            a = controller.networkController.getConnector().obtainEmployees();
            System.out.println("Sukces");

        }
        catch (Exception e) {
            System.out.println("Fail");
            throw e;
        }
        System.out.print("Czy zapisac dane? [T]/[N] : ");
        Scanner sc = new Scanner(System.in);
        char odp = (char) sc.nextLine().charAt(0);
        if (odp == 'T') {
            System.out.print("Zapisywanie...");
            if(a==null) return;
            for (IPracownik pracownik : a.getList()) {
                controller.addEmployee(pracownik);
            }
            System.out.println("Sukces");
        }
    }

    private Pair<String, String> getLoginAndPassword() {
        java.io.Console console = System.console();
        String login = null;
        String password = null;
        if(console == null) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Username : ");
            login = sc.nextLine();
            System.out.print("Password : ");
            password = sc.nextLine();
        }
        else {
        login = console.readLine("Username: ");
        password = new String(console.readPassword("Password: "));
        }
        return new Pair<>(login.trim(),password.trim());
    }

    private Pair<String,Integer> getIpAndPort() {
        String ip;
        Integer port;
        Scanner sc = new Scanner(System.in);
        System.out.print("Adres : ");
        ip = sc.nextLine();
        System.out.print("Port : ");
        port = sc.nextInt();
        return new Pair<String, Integer>(ip,port);
    }
    private String getPesel() {
        System.out.print("Podaj pesel: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void printEmplyess(IPracownikList pracownicy) {
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        char choice = '\r';
        while(choice != 'Q') {
            System.out.println("1. Lista pracownikow");
            if(index < pracownicy.getList().size()) System.out.print(pracownicy.getList().get(index));
            System.out.printf("[Pozycja : %d/%d]\n",index+1,pracownicy.getList().size());
            System.out.println("[Enter] : nastepny");
            System.out.println("[Q] : powrot");
            String response = scanner.nextLine();
            if(response.isEmpty()) {index = (++index) % (pracownicy.getList().size());}
            else {choice = response.charAt(0);}
        }
    }
    public int printMenu() {
        System.out.println("MENU:");
        System.out.println("\t 1. Lista pracowanikow");
        System.out.println("\t 2. Dodaj pracownika");
        System.out.println("\t 3. Usun pracownika");
        System.out.println("\t 4. Pobierz dane z sieci");
        System.out.println("\t 5. Wyjscie");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public IPracownik getEmplyee() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("[D]yrektor/[H]handlowiec:");
        char type = scanner.nextLine().charAt(0);
        IPracownik pracownik = type == 'H' ? new Handlowiec(): new Dyrektor();
        System.out.print("Identyfikator PESEL : ");
        pracownik.setPesel(scanner.nextLine());
        System.out.print("Imie : ");
        pracownik.setFirstName(scanner.nextLine());
        System.out.print("Nazwisko : ");
        pracownik.setLastName(scanner.nextLine());
        System.out.print("Wynagrodzenie : ");
        pracownik.setSalary(scanner.nextBigDecimal());
        scanner.nextLine();
        System.out.print("Telefon : ");
        pracownik.setPhoneNumber(scanner.nextLine());
        switch (type) {
            case 'D':
            {
                pracownik.setJobTitle(JobTitleType.Dyrektor);
                System.out.println("Dodatek : ");
                pracownik.setSalaryAddition(scanner.nextBigDecimal());
                scanner.nextLine();
                System.out.println("Numer Karty : ");
                ((Dyrektor) pracownik).setCardNumber(scanner.nextLine());
                System.out.println("Limit Kosztow : ");
                ((Dyrektor) pracownik).setExpansesLimit(scanner.nextBigDecimal());
                break;
            }
            case 'H':
            {
                pracownik.setJobTitle(JobTitleType.Handlowiec);
                System.out.print("Dodatek : ");
                pracownik.setSalaryAddition(scanner.nextBigDecimal());
                System.out.print("Prowizja : ");
                ((Handlowiec) pracownik).setCommision(scanner.nextBigDecimal());
                System.out.print("Limit prowizji : ");
                ((Handlowiec) pracownik).setCommisionMonthlyLimit(scanner.nextBigDecimal());
                break;
            }
            default:
                throw new IOException("Nieznany typ pracownika");
        }
        return pracownik;
    }
}
