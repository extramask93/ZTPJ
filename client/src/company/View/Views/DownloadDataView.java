package company.View.Views;

import company.Models.Pracownik;
import company.Models.PracownikList;
import company.Networking.NetworkConnectors.SOAPNetworkConnector;
import company.Networking.NetworkConnectors.TCPNetworkConnector;
import company.View.Cnt;
import javafx.util.Pair;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class DownloadDataView implements IView {
    @Override
    public String getName() {
        return "Pobierz dane";
    }

    @Override
    public void run() throws IOException, SQLException {
        try {
            askRemote();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void askRemote() throws Exception {
        Pair<String, String> id = null;
        PracownikList a = null;
        System.out.println("Wybierz protokol[T]CP/[S]OAP : ");
        Scanner sc= new Scanner(System.in);
        if(sc.nextLine().charAt(0) == 'T') {
            Cnt.getMC().networkController.setConnector(new TCPNetworkConnector());
        }
        else {
            Cnt.getMC().networkController.setConnector(new SOAPNetworkConnector());
        }
        Pair<String, Integer> host = getIpAndPort();
        if(Cnt.getMC().networkController.getConnector().needAuthentication())
            id = getLoginAndPassword();
        try {
            System.out.print("Ustanawianie polaczenia...");
            Cnt.getMC().networkController.getConnector().connect(host.getKey(),
                    host.getValue());
            System.out.println("Sukces");

        }
        catch (Exception e) {
            System.out.println("Fail");
            throw e;
        }
        try {
            if (Cnt.getMC().networkController.getConnector().needAuthentication()) {
                System.out.print("Autentykacja...");
                Cnt.getMC().networkController.getConnector().authenticate(
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
            a = Cnt.getMC().networkController.getConnector().obtainEmployees();
            System.out.println("Sukces");

        }
        catch (Exception e) {
            System.out.println("Fail");
            throw e;
        }
        System.out.print("Czy zapisac dane? [T]/[N] : ");
        char odp = (char) sc.nextLine().charAt(0);
        if (odp == 'T') {
            System.out.print("Zapisywanie...");
            if(a==null) return;
            for (Pracownik pracownik : a.getList()) {
                Cnt.getMC().addEmployee(pracownik);
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
        return new Pair<>(ip,port);
    }
}
