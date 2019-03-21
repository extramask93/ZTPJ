package com.company.View;
import com.company.Controller.MasterController;
import com.company.Model.Dyrektor;
import com.company.Model.Handlowiec;
import com.company.Model.IPracownik;
import com.company.Model.JobTitleType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class UI {
    public UI(MasterController controller) {
        this.controller = controller;
    }
    private MasterController controller;
    public void Run()
    {
        int choice = 0;
        while(choice != 4) {
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
                        var employee = getEmplyee();
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
                        controller.removeEmployee(result);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4: {
                    /*Quit*/
                    break;
                }
                default:
                    break;
            }
        }
    }

    private String getPesel() {
        System.out.print("Podaj pesel: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void printEmplyess(HashMap<String, IPracownik> pracownicy) {
        Scanner scanner = new Scanner(System.in);
        var keys = pracownicy.keySet().toArray();
        int index = 0;
        char choice = '\r';
        while(choice != 'Q') {
            System.out.println("1. Lista pracownikow");
            if(index < pracownicy.size()) System.out.print(pracownicy.get(keys[index]));
            System.out.printf("[Pozycja : %d/%d]\n",index+1,pracownicy.size());
            System.out.println("[Enter] : nastepny");
            System.out.println("[Q] : powrot");
            String response = scanner.nextLine();
            if(response.isEmpty()) {index = (++index) % (keys.length);}
            else {choice = response.charAt(0);}
        }
    }
    public int printMenu() {
        System.out.println("MENU:");
        System.out.println("\t 1. Lista pracowanikow");
        System.out.println("\t 2. Dodaj pracownika");
        System.out.println("\t 3. Usun pracownika");
        System.out.println("\t 4. Kopia zapasowa");
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
