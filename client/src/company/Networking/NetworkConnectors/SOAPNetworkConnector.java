package company.Networking.NetworkConnectors;
import company.Models.PracownikList;
import company.Networking.RMI.IRemoteInterface;
import company.Networking.RMI.Token;
import company.XMLUtilities.XMLMarshaller;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class SOAPNetworkConnector implements INetworkConnector {
    Socket sc = null;
    InputStream inStream = null;
    Scanner in = null;
    IRemoteInterface validatorStub = null;
    Token token = null;

    @Override
    public void connect(String ip, int port) throws IOException {
        try {
            Registry registry = LocateRegistry.getRegistry(ip, port);
            validatorStub = (IRemoteInterface) registry.lookup("RemoteInterfaceImpl");
            sc = new Socket(ip,port+1);
            inStream = sc.getInputStream();
            in = new Scanner(inStream, "UTF-8");
        } catch (IOException e) {
            throw e;
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() {
        try {
            in.close();
            inStream.close();
            sc.close();
        }
        catch (Exception e) {
            //ignore
        }
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
        XMLMarshaller marshaller = new XMLMarshaller();
        PracownikList list = null;
        String tmp = "";
        while(in.hasNextLine()) {
            tmp += in.nextLine();
        }
        try {
            list =  marshaller.unmarshall(tmp);
        } catch (JAXBException e) {
            throw e;
        }
        return list;
    }
}
