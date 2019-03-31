package DAO;

import DAO.ConnectionPool.BasicConnectionPool;
import Models.*;
import java.io.IOException;
import java.sql.*;

public class DatabaseDao implements IEmployeeDao {
    protected static BasicConnectionPool pool = null;
    protected String  deleteString = "DELETE FROM PRACOWNICY WHERE PESEL = ?";
    protected String  updateString = "INSERT INTO PRACOWNICY" +
            " (stanowisko, PESEL, imie, nazwisko, wynagrodzenie, telefon, dodatek, nrKarty, limitKosztow, " +
            "prowizja,limitProwizji)" +
            " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    protected String getAllString = "SELECT * from PRACOWNICY";

    public DatabaseDao(String propFile) throws SQLException, IOException {
        if(pool == null) {
            pool = BasicConnectionPool.create(propFile);
        }
    }
    @Override
    public void deleteEmployee(Pracownik pracownik) throws SQLException, IOException {
        Connection conn = null;
        PreparedStatement deleteStatement = null;
        try {
            conn = pool.getConnection();
            deleteStatement = conn.prepareStatement(deleteString);
            deleteStatement.setString(1,pracownik.getPesel());
            deleteStatement.executeUpdate();
            conn.commit();
            deleteStatement.close();
        }
        catch (SQLException e) {
            System.out.println("Rolling back data here....");
            try {
                if(conn != null)
                    conn.rollback();
            }
            catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        finally {
            if(deleteStatement != null) {
                deleteStatement.close();
            }
            if(conn != null)
                pool.releaseConnection(conn);
        }
    }
    @Override
    public PracownikList getAllEmployees() throws SQLException, IOException {
        PracownikList pracownicy = null;
        Connection conn = null;
        ResultSet result;
        PreparedStatement stat = null;
        try {
            conn = pool.getConnection();
            stat = conn.prepareStatement(getAllString);
            result = stat.executeQuery();
            pracownicy = employeeFactory(result);
            conn.commit();
            stat.close();
            result.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                if(conn != null)
                    conn.rollback();
            }
            catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        finally {
            if(stat != null) {
                stat.close();
            }
            if(conn != null)
                pool.releaseConnection(conn);
        }
        return pracownicy;
    }
    @Override
    public void addEmployee(Pracownik employee) throws SQLException, IOException {
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            conn = pool.getConnection();
            stat = conn.prepareStatement(updateString);
            if(employee.getJobTitle() == JobTitleType.Handlowiec) {
                stat = addHandlowiec(stat,employee);
            }
            else {
                stat = addDyrektor(stat,employee);
            }
            stat.executeUpdate();
            conn.commit();
            stat.close();
        }
        catch (SQLException e) {
            System.out.println("Rolling back data here....");
            System.out.println(e.getMessage());
            try {
                if(conn != null)
                    conn.rollback();
            }
            catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        finally {
            if(stat != null) {
                stat.close();
            }
            if(conn != null)
                pool.releaseConnection(conn);
        }
    }
    protected Pracownik createEmployee(ResultSet set) throws SQLException {
        int type = set.getInt("stanowisko");
        Pracownik handlowiec = null;
        switch (type) {
            case 0: /*Handlowiec*/
                handlowiec = new Handlowiec();
                ((Handlowiec) handlowiec).setCommision(set.getBigDecimal("prowizja"));
                ((Handlowiec) handlowiec).setCommisionMonthlyLimit(set.getBigDecimal("limitProwizji"));
                break;
            case 1: /*Dyrektor*/
                handlowiec = new Dyrektor();
                ((Dyrektor) handlowiec).setCardNumber(set.getString("nrKarty"));
                ((Dyrektor) handlowiec).setExpansesLimit(set.getBigDecimal("limitKosztow"));
                ((Dyrektor) handlowiec).setSalaryAddition(set.getBigDecimal("dodatek"));
                break;
            default:
                return handlowiec;
        }
        handlowiec.setJobTitle(JobTitleType.values()[type]);
        handlowiec.setFirstName(set.getString("imie"));
        handlowiec.setLastName(set.getString("nazwisko"));
        handlowiec.setSalary(set.getBigDecimal("wynagrodzenie"));
        handlowiec.setPesel(set.getString("PESEL"));
        handlowiec.setPhoneNumber(set.getString("telefon"));
        handlowiec.setSalaryAddition(set.getBigDecimal("dodatek"));
        return handlowiec;
    }
    protected PracownikList employeeFactory(ResultSet set) throws SQLException {
        PracownikList employees = new PracownikList();
        while(set.next()) {
            employees.getList().add(createEmployee(set));
        }
        return employees;
    }
    public PreparedStatement addPracownik(PreparedStatement updateStatement, Pracownik employee) throws SQLException {
        updateStatement.setInt(1,employee.getJobTitle().ordinal());
        updateStatement.setString(2,employee.getPesel());
        updateStatement.setString(3,employee.getFirstName());
        updateStatement.setString(4, employee.getLastName());
        updateStatement.setBigDecimal(5,employee.getSalary());
        updateStatement.setString(6,employee.getPhoneNumber());
        return updateStatement;
    }
    public PreparedStatement addHandlowiec(PreparedStatement updateStatement, Pracownik employee_) throws SQLException,IOException {
            updateStatement =  addPracownik(updateStatement,employee_);
            Handlowiec employee = (Handlowiec) employee_;
            updateStatement.setNull(7,Types.DECIMAL);
            updateStatement.setNull(8,Types.VARCHAR);
            updateStatement.setNull(9,Types.DECIMAL);
            updateStatement.setBigDecimal(10,employee.getCommision());
            updateStatement.setBigDecimal(11,employee.getCommisionMonthlyLimit());
        return updateStatement;
    }
    public PreparedStatement addDyrektor(PreparedStatement updateStatement, Pracownik employee_)
            throws SQLException, IOException {
            updateStatement =  addPracownik(updateStatement,employee_);
            Dyrektor employee = (Dyrektor) employee_;
            updateStatement.setBigDecimal(7, employee.getSalaryAddition());
            updateStatement.setString(8,employee.getCardNumber());
            updateStatement.setBigDecimal(9,employee.getExpansesLimit());
            updateStatement.setNull(10,Types.DECIMAL);
            updateStatement.setNull(11,Types.DECIMAL);
            return updateStatement;
    }
}
