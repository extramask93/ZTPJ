package company.Controllers;
import company.Models.IPracownikList;
import company.Controllers.DAO.IEmployeeDao;
import company.Models.IPracownik;
import company.Networking.NetworkConnectors.INetworkConnector;

import java.io.*;
import java.sql.SQLException;


public class MasterController {
    private IPracownikList pracownicy = null;
    private IEmployeeDao dao = null;
    public NetworkController networkController = null;
    public MasterController(IEmployeeDao dao, INetworkConnector connector)
    {
        this.networkController = new NetworkController(connector);
        this.pracownicy = new IPracownikList();
        this.dao = dao;
    }
    public void addEmployee(IPracownik employee) throws IOException, SQLException {
            dao.addEmployee(employee);
    }
    public IPracownikList getEmployees() throws IOException, SQLException {
        pracownicy = dao.getAllEmployees();
        return pracownicy;
    }
    public void removeEmployee(IPracownik pracownik_) throws IOException, SQLException {
            dao.deleteEmployee(pracownik_);
    }
}
