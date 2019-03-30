package DAO;

import Models.Dyrektor;
import Models.Handlowiec;
import Models.Pracownik;
import Models.PracownikList;

import java.io.IOException;
import java.sql.SQLException;

public class ListDao implements IEmployeeDao {
    PracownikList list  = null;
    public ListDao() {
        list = new PracownikList();
        list.getList().add(new Dyrektor());
        list.getList().add(new Handlowiec());
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
