package company.Networking.NetworkConnectors;

import company.Models.IPracownikList;
import company.XMLUtilities.XMLMarshaller;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPNetworkConnector implements INetworkConnector {
    Socket sc = null;
    InputStream inStream = null;
    Scanner in = null;
    @Override
    public void connect(String ip, int port) throws IOException {
        try {
            sc = new Socket(ip,port);
            inStream = sc.getInputStream();
            in = new Scanner(inStream, "UTF-8");
        } catch (IOException e) {
            throw e;
        }

    }

    @Override
    public void close() {
        try {
            in.close();
            inStream.close();
            sc.close();
        }
        catch (Exception e) {
            //ignore
        }
    }

    @Override
    public boolean authenticate(String login, String password) {
        return false;
    }

    @Override
    public boolean needAuthentication() {
        return false;
    }

    @Override
    public IPracownikList obtainEmployees() throws JAXBException {
        XMLMarshaller marshaller = new XMLMarshaller();
        IPracownikList list = null;
        String tmp = "";
        while(in.hasNextLine()) {
            tmp += in.nextLine();
        }
        try {
            list =  marshaller.unmarshall(tmp);
        } catch (JAXBException e) {
            throw e;
        }
        return list;
    }
}
