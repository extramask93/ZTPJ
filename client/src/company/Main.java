package company;
import company.Controllers.DAO.IEmployeeDao;
import company.Controllers.DAO.ListDao;
import company.Controllers.MasterController;
import company.Networking.NetworkConnectors.INetworkConnector;
import company.Networking.NetworkConnectors.RMINetworkConnector;
import company.View.UI;


public class Main {
    public static void main(String[] args) {

            try {
                IEmployeeDao dao = new ListDao();
                INetworkConnector connector = new RMINetworkConnector();
                MasterController controller = new MasterController(dao,connector);
                UI ui = new UI(controller);
                ui.Run();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }


}
