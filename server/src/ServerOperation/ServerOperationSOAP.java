package ServerOperation;
import Tools.AccessorClass;
import Tools.RMIService;

import javax.xml.ws.Endpoint;
import java.rmi.RemoteException;
import java.util.concurrent.Executors;


public class ServerOperationSOAP implements IServerOperation {
    private static final int RMI_PORT = 2000;
    public ServerOperationSOAP() throws RemoteException {
        RMIService.activate(RMI_PORT);
    }
    @Override
    public void run() {
        String url = "http://localhost:1212/accessor";
        Endpoint endpoint = Endpoint.create(new AccessorClass());
        endpoint.setExecutor(Executors.newFixedThreadPool(5));
        endpoint.publish(url);
        System.out.println("Service started @ " + url);
    }
}
