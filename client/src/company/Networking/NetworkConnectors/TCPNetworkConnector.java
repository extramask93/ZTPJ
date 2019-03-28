package company.Networking.NetworkConnectors;

import company.Models.PracownikList;
import company.Networking.RMI.IRemoteInterface;
import company.Networking.RMI.Token;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TCPNetworkConnector implements INetworkConnector {
    Socket sc = null;
    ObjectInputStream ois = null;
    ObjectOutputStream ins = null;
    IRemoteInterface validatorStub = null;
    Token token = null;
    @Override
    public void connect(String ip, int port) throws IOException, NotBoundException {
        try {

            Registry registry = LocateRegistry.getRegistry(ip, 2000);
            validatorStub = (IRemoteInterface) registry.lookup("RemoteInterfaceImpl");
            sc = new Socket(ip,port);
            ins = new ObjectOutputStream(sc.getOutputStream());
            ois  = new ObjectInputStream(sc.getInputStream());
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public void close() {
        try {
            sc.close();
        }
        catch (Exception e) {
            //ignore
        }
    }

    @Override
    public boolean authenticate(String login, String password) {
        try {
            token = validatorStub.validate(login.trim(),password.trim());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        finally {
            if(token == null)
                return false;
            else
                return true;
        }
    }

    @Override
    public boolean needAuthentication() {
        return true;
    }

    @Override
    public PracownikList obtainEmployees() throws Exception {
        PracownikList list = null;
        ins.writeObject(new String("TOK\r\n"));
        ins.writeObject(token);
        String response = (String) ois.readObject();
        response.trim();
        System.out.println(response);
        if(response.substring(0,3).equals("OKK")) {
            ins.writeObject(new String("GET\r\n"));
            list = (PracownikList) ois.readObject();
        }
        else {
            throw new Exception("Token out of date");
        }
        return list;
    }
}
