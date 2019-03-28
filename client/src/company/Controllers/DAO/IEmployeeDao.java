package company.Controllers.DAO;

import company.Models.Pracownik;
import company.Models.PracownikList;

import java.io.IOException;
import java.sql.SQLException;

public interface IEmployeeDao {
    PracownikList getAllEmployees() throws SQLException, IOException;
    void addEmployee(Pracownik employee) throws  SQLException, IOException;
    void deleteEmployee(Pracownik employee) throws SQLException, IOException;
}
