package DAO;

import Models.Pracownik;
import Models.PracownikList;

import java.io.IOException;
import java.sql.SQLException;

public interface IEmployeeDao {
    PracownikList getAllEmployees() throws SQLException, IOException;
    void addEmployee(Pracownik employee) throws  SQLException, IOException;
    void deleteEmployee(Pracownik employee) throws SQLException, IOException;
}
