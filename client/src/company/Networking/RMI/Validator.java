package company.Networking.RMI;

import company.Controllers.DAO.DatabaseDao;
import company.Models.IPracownik;
import company.Models.IPracownikList;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Validator implements IRemoteInterface, Serializable {

    private HashMap<String,String> users = new HashMap<>();
    private List<Token> tokens = new ArrayList<>();
    public Validator() throws IOException, SQLException {

    }

    @Override
    public Token validate(String login, String password) throws RemoteException {
        Token token = new Token(5);
        if(login.equals("root")) {
            if(password.equals("root")) {
                token = new Token(10);
                tokens.add(token);
            }
        }
        return token;
    }

    @Override
    public IPracownikList getEmplyees(Token token) throws RemoteException {
        IPracownikList list = new IPracownikList();
        list.setList(new ArrayList<>());
        IPracownik pracownik = new IPracownik();
        pracownik.setPesel("123");
        list.getList().add(pracownik);
        try {
            if(tokens.contains(token)) {
                if (token.getTime().plusMinutes(2).isBefore(LocalDateTime.now())) {
                    list = new DatabaseDao().getAllEmployees();
                }
                else {
                    tokens.remove(token);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}