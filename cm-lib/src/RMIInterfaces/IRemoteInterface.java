package RMIInterfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteInterface extends Remote {
    String validate(String login, String password) throws RemoteException;
    boolean isTokenValid(String token) throws RemoteException;
}
