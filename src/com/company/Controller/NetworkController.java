package com.company.Controller;
import Model.IPracownik;
import Model.IPracownikList;
import XMLUtilities.XMLMarshaller;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Scanner;

public class NetworkController {
    public HashMap<String, IPracownik> getRemoteEmployees(String ip, Integer port) {
        XMLMarshaller marshaller = new XMLMarshaller();
        System.out.print("Ustanawianie polaczenia...");
        IPracownikList list = new IPracownikList();
        try (Socket sc = new Socket(ip,port);
             InputStream inStream = sc.getInputStream();
             OutputStream outStream = sc.getOutputStream();
             Scanner in = new Scanner(inStream, "UTF-8"))
        {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);
            String tmp = "";
            System.out.println("Sukces");
            System.out.print("Pobieranie danych...");
            while(in.hasNextLine()) {
                tmp += in.nextLine();
            }
            System.out.println("Sukces");
            list = marshaller.unmarshall(tmp);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return list.toHashMap();
    }
}
