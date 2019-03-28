package company.Networking.RMI;
import company.Controllers.DAO.DatabaseDao;
import company.Controllers.DAO.IEmployeeDao;
import company.Models.PracownikList;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RemoteInterfaceImpl implements IRemoteInterface, Serializable {

    private HashMap<String,String> users = new HashMap<>();
    private static final int TOKEN_SIZE = 10;
    private static List<Token> tokens = new ArrayList<>();
    private IEmployeeDao dao = null;
    public RemoteInterfaceImpl() {
        users.putIfAbsent("root","root");
    }
    public RemoteInterfaceImpl(IEmployeeDao dao) {
        this.dao = dao;
        users.putIfAbsent("root","root");
    }
    @Override
    public Token validate(String login, String password) throws RemoteException {
        Token token = null;
        if(users.containsKey(login)) {
            if(users.get(login).equals(password)) {
                token = new Token(TOKEN_SIZE);
                tokens.add(token);
            }
        }
        return token;
    }
    @Override
    public boolean isTokenValid(Token token) {
        if(tokens.contains(token)) {
            if (token.getTime().plusMinutes(2).isBefore(LocalDateTime.now())) {
                return true;
            }
            else {
                tokens.remove(token);
            }
        }
        return false;
    }
    @Override
    public PracownikList getEmplyees(Token token) throws RemoteException {
        PracownikList list = null;
        if(isTokenValid(token)) {
            try {
                list = dao.getAllEmployees();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}