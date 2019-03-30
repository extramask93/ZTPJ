package RMIInterfaces;
import RMIInterfaces.IRemoteInterface;
import RMIInterfaces.Token;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

public class RemoteInterfaceImpl extends UnicastRemoteObject implements IRemoteInterface {

    private HashMap<String,String> users = new HashMap<>();
    private static final int TOKEN_SIZE = 10;
    private static HashMap<String, Token> tokens = new HashMap<>();
    public RemoteInterfaceImpl() throws RemoteException {
        super();
        users.putIfAbsent("root","root");
    }

    @Override
    public String validate(String login, String password) throws RemoteException {
        Token token = null;
        if(users.containsKey(login)) {
            if(users.get(login).equals(password)) {
                token = new Token(TOKEN_SIZE);
                tokens.putIfAbsent(token.getToken(),token);
            }
        }
        return token.getToken();
    }
    @Override
    public boolean isTokenValid(String token) throws RemoteException {
        if (tokens.containsKey(token)) {
            Token temp = tokens.get(token);
            long dt = temp.getTime().plusMinutes(2).toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli();
            long dt2 = LocalDateTime.now().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli();
            if (dt > dt2) {
                return true;
            } else {
                tokens.remove(token);
            }
        }
        return false;
    }
}