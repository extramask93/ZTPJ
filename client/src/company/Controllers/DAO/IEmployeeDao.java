package company.Controllers.DAO;

import company.Models.IPracownik;
import company.Models.IPracownikList;

import java.io.IOException;
import java.sql.SQLException;

public interface IEmployeeDao {
    IPracownikList getAllEmployees() throws SQLException, IOException;
    void addEmployee(IPracownik employee) throws  SQLException, IOException;
    void deleteEmployee(IPracownik employee) throws SQLException, IOException;
}
