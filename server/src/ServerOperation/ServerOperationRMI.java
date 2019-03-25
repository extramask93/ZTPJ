package ServerOperation;

import company.Networking.RMI.IRemoteInterface;
import company.Networking.RMI.Validator;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class ServerOperationRMI implements IServerOperation {
    static Registry registry = null;
    public ServerOperationRMI() throws RemoteException {
        try {
            IRemoteInterface stub = (IRemoteInterface) UnicastRemoteObject.exportObject(new Validator(), 2000);
            registry = LocateRegistry.createRegistry(2000);
            registry.rebind("Validator", stub);
            System.out.println("Server ready");
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void run() {

    }

}
