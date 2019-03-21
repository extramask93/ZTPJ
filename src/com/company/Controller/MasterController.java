package com.company.Controller;

import com.company.Controller.DAO.EmployeeDao;
import com.company.Controller.DAO.IEmployeeDao;
import com.company.Model.IPracownik;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class MasterController {
    private HashMap<String, IPracownik> pracownicy = null;
    private IEmployeeDao dao = null;

    public MasterController(IEmployeeDao dao)
    {
        pracownicy = new HashMap<>();
        this.dao = dao;
    }

    public void addEmployee(IPracownik employee) throws IOException, SQLException {
            dao.addEmployee(employee);
    }
    public HashMap<String,IPracownik> getEmployees() throws IOException, SQLException {
        pracownicy = dao.getAllEmployees();
        return pracownicy;
    }
    public void removeEmployee(String pesel) throws IOException, SQLException {
            dao.deleteEmployee(pesel);
    }
}
