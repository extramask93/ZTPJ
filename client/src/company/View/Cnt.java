package company.View;

import DAO.DatabaseDao;
import DAO.ListDao;
import company.Controllers.MasterController;
import company.Networking.SOAPNetworkConnector;
import company.Networking.TCPNetworkConnector;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Cnt {
    static MasterController controller = null;
    public static MasterController getMC() {
        if(controller == null) {
            try {
                controller = new MasterController(new DatabaseDao("clientDatabase.properties"),new SOAPNetworkConnector());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return controller;
    }
}
