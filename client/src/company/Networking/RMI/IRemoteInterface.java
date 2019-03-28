package company.Networking.RMI;
import company.Models.PracownikList;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteInterface extends Remote {
    Token validate(String login, String password) throws RemoteException;
    boolean isTokenValid(Token token);
    PracownikList getEmplyees(Token token) throws RemoteException;
}
