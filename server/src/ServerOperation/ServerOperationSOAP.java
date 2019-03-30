package ServerOperation;
import Const.Constants;
import DAO.IEmployeeDao;
import RMI.RMIService;
import SOAP.SOAPAccessorClass;

import javax.xml.ws.Endpoint;
import java.rmi.RemoteException;
import java.util.concurrent.Executors;


public class ServerOperationSOAP implements IServerOperation {
    private IEmployeeDao dao = null;
    public ServerOperationSOAP(IEmployeeDao dao) throws RemoteException {
        RMIService.activate(Constants.RMI_PORT);
        this.dao = dao;
    }
    @Override
    public void run() {
        String url = "http://localhost:"+ Constants.SOAP_PORT +"/"+Constants.SOAP_NAME;
        Endpoint endpoint = Endpoint.create(new SOAPAccessorClass(dao));
        endpoint.setExecutor(Executors.newFixedThreadPool(5));
        endpoint.publish(url);
        System.out.println("Service started @ " + url);
    }
}
