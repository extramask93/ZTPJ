package DAO;

import Model.IPracownik;
import Model.IPracownikList;

import java.io.IOException;
import java.sql.SQLException;

public class ListDao implements IEmployeeDao {
    IPracownikList list  = null;
    public ListDao() {
        list = new IPracownikList();
    }

    @Override
    public IPracownikList getAllEmployees() throws SQLException, IOException {
        return list;
    }

    @Override
    public void addEmployee(IPracownik employee) throws SQLException, IOException {
        list.getList().add(employee);
    }

    @Override
    public void deleteEmployee(IPracownik employee) throws SQLException, IOException {
        list.getList().remove(employee);
    }
}
