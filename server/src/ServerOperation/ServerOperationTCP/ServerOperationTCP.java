package ServerOperation.ServerOperationTCP;
import Const.Constants;
import DAO.IEmployeeDao;
import RMI.RMIService;
import ServerOperation.IServerOperation;

import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

public class ServerOperationTCP implements IServerOperation {
   static private IEmployeeDao dao = null;
   public ServerOperationTCP(IEmployeeDao dao) throws RemoteException {
      this.dao = dao;
      RMIService.activate(Constants.RMI_PORT);
   }
   @Override
   public void run() throws RemoteException {
      try (ServerSocket s = new ServerSocket(Constants.TCP_PORT)){
         int i = 1;
         while (true) {
            Socket incoming = s.accept();
            System.out.println("Spawning "+ i);
            Runnable r = new ThreadedHandlerTCP(incoming,dao);
            Thread t = new Thread(r);
            t.start();
            i++;
         }
      } catch (Exception e) {
         throw new RemoteException(e.getMessage());
      }
   }
}
