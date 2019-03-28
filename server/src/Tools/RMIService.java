package Tools;

import company.Controllers.DAO.DatabaseDao;
import company.Networking.RMI.IRemoteInterface;
import company.Networking.RMI.RemoteInterfaceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIService {
    static Registry registry = null;
    static IRemoteInterface stub = null;
    public static void activate(int port) throws RemoteException {
        try {
            stub = (IRemoteInterface) UnicastRemoteObject.exportObject(new RemoteInterfaceImpl(new DatabaseDao()), port);
            registry = LocateRegistry.createRegistry(port);
            registry.rebind("RemoteInterfaceImpl", stub);
            System.out.println("RMI service ready");
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }
    public static IRemoteInterface getStub() {
        return stub;
    }
}
