package SOAP;

import DAO.IEmployeeDao;
import Models.PracownikList;
import RMI.RMIService;
import XMLUtilities.XMLMarshaller;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

@WebService
public class SOAPAccessorClass {
    IEmployeeDao dao = null;
    public SOAPAccessorClass(IEmployeeDao dao) {
        this.dao = dao;
    }
    public SOAPAccessorClass() {

    }
    @WebMethod
    public String getEmployees(String token) throws RemoteException {
        String result = null;
        if(RMIService.getStub().isTokenValid(token)) {
            try {
                PracownikList ll = dao.getAllEmployees();
                XMLMarshaller marshaller = new XMLMarshaller();
                result = marshaller.marshall(ll);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
