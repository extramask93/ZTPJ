package company.View;

import DAO.ListDao;
import company.Controllers.MasterController;
import company.Networking.TCPNetworkConnector;

public class Cnt {
    static MasterController controller = null;
    public static MasterController getMC() {
        if(controller == null)
            controller = new MasterController(new ListDao(),new TCPNetworkConnector());
        return controller;
    }
}
