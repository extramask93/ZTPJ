package company.Networking.RMI;
import company.Models.IPracownikList;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteInterface extends Remote {
    Token validate(String login, String password) throws RemoteException;
    IPracownikList getEmplyees(Token token) throws RemoteException;
}
