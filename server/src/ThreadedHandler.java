import DAO.DatabaseDao;
import DAO.IEmployeeDao;
import Model.Dyrektor;
import Model.Handlowiec;
import Model.IPracownik;
import Model.IPracownikList;
import XMLUtilities.XMLMarshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;


public class ThreadedHandler implements Runnable {
    private Socket s;
    IEmployeeDao dao = null;
    public ThreadedHandler(Socket s) throws IOException, SQLException {
        this.s = s;
        dao = new DatabaseDao();
    }
    @Override
    public void run() {
        OutputStream outStream = null;
        XMLMarshaller marshaller = new XMLMarshaller();
        PrintWriter out = null;
        try {
            outStream = s.getOutputStream();
            out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);
            out.println(marshaller.marshall(getTest()));
        }
        catch (IOException | JAXBException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(s != null)
                    s.close();
                if(out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public IPracownikList getTest() throws IOException, SQLException {
        return dao.getAllEmployees();
    }
}
