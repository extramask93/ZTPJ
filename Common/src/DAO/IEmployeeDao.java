package DAO;

import Model.IPracownik;
import Model.IPracownikList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public interface IEmployeeDao {
    IPracownikList getAllEmployees() throws SQLException, IOException;
    void addEmployee(IPracownik employee) throws  SQLException, IOException;
    void deleteEmployee(IPracownik employee) throws SQLException, IOException;
}
