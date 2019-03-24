package com.company.Controller;
import Model.IPracownikList;
import DAO.IEmployeeDao;
import Model.IPracownik;
import java.io.*;
import java.sql.SQLException;


public class MasterController {
    private IPracownikList pracownicy = null;
    private IEmployeeDao dao = null;
    public NetworkController tcpController = null;
    public MasterController(IEmployeeDao dao)
    {
        tcpController = new NetworkController();
        pracownicy = new IPracownikList();
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
