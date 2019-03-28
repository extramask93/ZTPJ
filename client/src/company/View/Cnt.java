package company.View;

import company.Controllers.DAO.ListDao;
import company.Controllers.MasterController;
import company.Networking.NetworkConnectors.TCPNetworkConnector;

public class Cnt {
    static MasterController controller = null;
    public static MasterController getMC() {
        if(controller == null)
            controller = new MasterController(new ListDao(),new TCPNetworkConnector());
        return controller;
    }
}
