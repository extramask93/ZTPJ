package com.company.NetworkConnectors;

import Model.Dyrektor;
import Model.Handlowiec;
import Model.IPracownik;
import Model.IPracownikList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TCPNetworkConnector implements INetworkConnector {
    public HashMap<String, IPracownik> getRemoteEmployees(String ip, Integer port) {
        System.out.print("Ustanawianie polaczenia...");
        HashMap<String, IPracownik> map = new HashMap<>();
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
            map = unmarshall(tmp);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return map;
    }
    public HashMap<String,IPracownik> unmarshall(String a) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(IPracownikList.class, Dyrektor.class,IPracownik.class, Handlowiec.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringReader rader = new StringReader(a);
        IPracownikList pracownicy = (IPracownikList) jaxbUnmarshaller.unmarshal(rader);
        ArrayList<IPracownik> pracownicyl = (ArrayList<IPracownik>) pracownicy.getList();
        HashMap<String,IPracownik> pracownicyhash = new HashMap<>();
        for(IPracownik pracownik : pracownicyl) {
            pracownicyhash.put(pracownik.getPesel(),pracownik);
        }
        return pracownicyhash;

    }

    @Override
    public void connect(String ip, int port) {

    }

    @Override
    public void authenticate(String login, String password) {

    }

    @Override
    public boolean needAuthentication() {
        return false;
    }

    @Override
    public IPracownikList obtainEmployees() {
        return null;
    }
}
