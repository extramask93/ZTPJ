package company.Networking;

import Models.PracownikList;

import java.io.IOException;
import java.rmi.NotBoundException;

public interface INetworkConnector {
    void connect(String ip, int port) throws IOException, NotBoundException;
    void close();
    boolean authenticate(String login, String password);
    boolean needAuthentication();
    PracownikList obtainEmployees() throws Exception;
}
