package Tools;
import company.Models.PracownikList;
import company.Networking.RMI.Token;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ThreadedHandlerTCP implements Runnable {
    Token tok = null;
    Socket s;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    public ThreadedHandlerTCP(Socket s) throws IOException {
        this.s = s;
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
                tok = (Token) ois.readObject();
                if(RMIService.getStub().isTokenValid(tok)) {
                        oos.writeObject("INV");
                }
                else {
                    oos.writeObject("OKK");
                    PracownikList list = RMIService.getStub().getEmplyees(tok);
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
