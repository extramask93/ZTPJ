package ServerOperation;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

public class ServerOperationTCP implements IServerOperation {
    @Override
    public void run() throws RemoteException {
        try (ServerSocket s = new ServerSocket(8198)){
            int i = 1;
            while (true) {
                Socket incoming = s.accept();
                System.out.println("Spawning "+ i);
                Runnable r = new ThreadedHandler(incoming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }
}
