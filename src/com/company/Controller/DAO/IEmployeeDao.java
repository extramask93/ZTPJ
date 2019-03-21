package com.company.Controller.DAO;

import com.company.Model.IPracownik;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public interface IEmployeeDao {
    HashMap<String, IPracownik> getAllEmployees() throws SQLException, IOException;
    void addEmployee(IPracownik employee) throws  SQLException, IOException;
    void deleteEmployee(String pesel) throws SQLException, IOException;
}
