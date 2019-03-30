package company.Controllers;
import DAO.IEmployeeDao;
import Models.PracownikList;
import Models.Pracownik;
import company.Networking.INetworkConnector;

import java.io.*;
import java.sql.SQLException;


public class MasterController {
    private PracownikList pracownicy = null;
    private IEmployeeDao dao = null;
    public NetworkController networkController = null;
    public MasterController(IEmployeeDao dao, INetworkConnector connector)
    {
        this.networkController = new NetworkController(connector);
        this.pracownicy = new PracownikList();
        this.dao = dao;
    }
    public void addEmployee(Pracownik employee) throws IOException, SQLException {
            dao.addEmployee(employee);
    }
    public PracownikList getEmployees() throws IOException, SQLException {
        pracownicy = dao.getAllEmployees();
        return pracownicy;
    }
    public void removeEmployee(Pracownik pracownik_) throws IOException, SQLException {
            dao.deleteEmployee(pracownik_);
    }
}
