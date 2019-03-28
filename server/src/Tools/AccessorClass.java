package Tools;

import company.Models.PracownikList;
import company.Networking.RMI.Token;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.rmi.RemoteException;

@WebService
public class AccessorClass {
    public AccessorClass() {

    }
    @WebMethod
    public String hello() {
        return "Hello";
    }
    @WebMethod
    public PracownikList getEmployees(Token token) {
        if(RMIService.getStub().isTokenValid(token)) {
            try {
                return RMIService.getStub().getEmplyees(token);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return new PracownikList();
    }
}
