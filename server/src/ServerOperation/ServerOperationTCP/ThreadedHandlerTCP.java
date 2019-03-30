package ServerOperation.ServerOperationTCP;
import DAO.IEmployeeDao;
import Models.PracownikList;
import RMI.RMIService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ThreadedHandlerTCP implements Runnable {
    String tok = null;
    Socket s;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    IEmployeeDao dao = null;
    public ThreadedHandlerTCP(Socket s, IEmployeeDao dao) throws IOException {
        this.s = s;
        this.dao = dao;
        oos = new ObjectOutputStream( s.getOutputStream());
        ois = new ObjectInputStream(s.getInputStream());
    }

    @Override
    public void run() {
        try {
            String request = (String) ois.readObject();
            request = request.trim();
            System.out.println(request);
            if(request.equals("TOK")) {
                tok = (String) ois.readObject();
                if(!RMIService.getStub().isTokenValid(tok)) {
                        oos.writeObject("INV");
                }
                else {
                    oos.writeObject("OKK");
                    PracownikList list = dao.getAllEmployees();
                    oos.writeObject(list);
                }
            }
            else {
                oos.writeObject("UNK");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
