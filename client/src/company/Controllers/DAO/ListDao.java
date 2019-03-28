package company.Controllers.DAO;

import company.Models.Pracownik;
import company.Models.PracownikList;

import java.io.IOException;
import java.sql.SQLException;

public class ListDao implements IEmployeeDao {
    PracownikList list  = null;
    public ListDao() {
        list = new PracownikList();
    }

    @Override
    public PracownikList getAllEmployees() throws SQLException, IOException {
        return list;
    }

    @Override
    public void addEmployee(Pracownik employee) throws SQLException, IOException {
        list.getList().add(employee);
    }

    @Override
    public void deleteEmployee(Pracownik employee) throws SQLException, IOException {
        list.getList().remove(employee);
    }
}
