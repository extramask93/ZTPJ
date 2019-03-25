package company.Networking.NetworkConnectors;
import company.Models.IPracownikList;
import company.Networking.RMI.IRemoteInterface;
import company.Networking.RMI.Token;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMINetworkConnector implements INetworkConnector {
    IRemoteInterface validatorStub = null;
    Token token = null;

    public RMINetworkConnector() {
    }

    @Override
    public void connect(String ip, int port) throws IOException {
        try {
            Registry registry = LocateRegistry.getRegistry(ip, port);
            validatorStub = (IRemoteInterface) registry.lookup("Validator");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IOException();
        }
    }

    @Override
    public void close() {
        //nothing to do
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
    public IPracownikList obtainEmployees() {
        IPracownikList list = null;
        try {
            list = validatorStub.getEmplyees(token);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
