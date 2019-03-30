package company.Networking;
import Const.Constants;
import Models.PracownikList;
import RMIInterfaces.IRemoteInterface;
import XMLUtilities.XMLMarshaller;
import web.SOAPAccessorClass;
import web.SOAPAccessorClassService;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class SOAPNetworkConnector implements INetworkConnector {
    IRemoteInterface validatorStub = null;
    String token = null;

    @Override
    public void connect(String ip, int port) throws IOException {
        try {
            String url = "rmi://localhost:"+ Constants.RMI_PORT+"/"+ Constants.RMI_NAME;
            validatorStub=(IRemoteInterface) Naming.lookup(url);
        } catch (IOException e) {
            throw e;
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() {
    }

    @Override
    public boolean authenticate(String login, String password)
    {
        try {
            token = validatorStub.validate(login.trim(),password.trim());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        finally {
            if(token == null)
                return false;
            else
                return true;
        }
    }

    @Override
    public boolean needAuthentication() {
        return true;
    }

    @Override
    public PracownikList obtainEmployees() throws JAXBException {
        SOAPAccessorClassService service = new SOAPAccessorClassService();
        SOAPAccessorClass ac =  service.getSOAPAccessorClassPort();
        String str =  ac.getEmployees(token);
        XMLMarshaller marshaller = new XMLMarshaller();
        PracownikList ll = marshaller.unmarshall(str);
        return ll;
    }
}
