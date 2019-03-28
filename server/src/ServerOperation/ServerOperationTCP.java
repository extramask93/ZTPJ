package ServerOperation;

import Tools.RMIService;
import Tools.ThreadedHandlerTCP;

import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

public class ServerOperationTCP implements IServerOperation{
   static final int PORT_RMI = 2000;
   static final int PORT_SOCK = 8198;
   public ServerOperationTCP() throws RemoteException {
      RMIService.activate(PORT_RMI);
   }
   @Override
   public void run() throws RemoteException {
      try (ServerSocket s = new ServerSocket(PORT_SOCK)){
         int i = 1;
         while (true) {
            Socket incoming = s.accept();
            System.out.println("Spawning "+ i);
            Runnable r = new ThreadedHandlerTCP(incoming);
            Thread t = new Thread(r);
            t.start();
            i++;
         }
      } catch (Exception e) {
         throw new RemoteException(e.getMessage());
      }
   }
}
