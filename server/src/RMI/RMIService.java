package RMI;

import RMIInterfaces.IRemoteInterface;
import RMIInterfaces.RemoteInterfaceImpl;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIService {
    static Registry registry = null;
    static RemoteInterfaceImpl stub = null;
    static String registryURL = null;
    public static void activate(int port) throws RemoteException {
        try {
            stub = new RemoteInterfaceImpl();
            startRegistry( port );
            registryURL = "rmi://localhost:" + String.valueOf(port)+ "/some";
            Naming.rebind(registryURL, stub);
            System.out.println("RMI service ready at : " + registryURL);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }
    private static void startRegistry(int rmiPortNum) throws RemoteException{
        try {
            Registry registry = LocateRegistry.getRegistry(rmiPortNum);
            registry.list( );
            // The above call will throw an exception
            // if the registry does not already exist
        } catch (RemoteException ex) {
            // No valid registry at that port.
            System.out.println("RMI registry is not located at port " + rmiPortNum);
            Registry registry = LocateRegistry.createRegistry(rmiPortNum);
            System.out.println("RMI registry created at port " + rmiPortNum);
        }
    }
    public static IRemoteInterface getStub() {
        return stub;
    }
}
