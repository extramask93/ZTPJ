package com.company;
import com.company.UI;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    public static String connString = "jdbc:derby://localhost:1527/COREJAVA;create=true";
    public static List<IPracownik> employees = new ArrayList<IPracownik>();
    public static void main(String[] args) {
	// write your code here
        //UI ui = new UI(employees);
        //ui.Run();
        try {
            //Class.forName("org.apache.derby.jdbc.ClientDriver");
            runTest();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    public static void runTest() throws SQLException, IOException {
        try(Connection conn = getConnection();
            Statement stat = conn.createStatement()) {
            stat.executeUpdate("CREATE TABLE Greetings (Message CHAR(20))");
        }
    }
    public static Connection getConnection() throws SQLException,IOException{
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("database.properties"))){
            props.load(in);
        }
        String drivers = props.getProperty("jdbc.drivers");
        if(drivers != null ) System.setProperty("jdbc.drivers",drivers);
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        return DriverManager.getConnection(url,username,password);
    }
}
