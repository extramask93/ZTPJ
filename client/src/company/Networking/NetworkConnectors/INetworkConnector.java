package company.Networking.NetworkConnectors;

import company.Models.IPracownikList;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.rmi.NotBoundException;

public interface INetworkConnector {
    void connect(String ip, int port) throws IOException, NotBoundException;
    void close();
    boolean authenticate(String login, String password);
    boolean needAuthentication();
    IPracownikList obtainEmployees() throws JAXBException;
}
