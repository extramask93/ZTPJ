import DAO.DatabaseDao;
import DAO.IEmployeeDao;
import ServerOperation.IServerOperation;
import ServerOperation.ServerOperationSOAP;
import ServerOperation.ServerOperationTCP.ServerOperationTCP;

public class Main {
    static IServerOperation operation = null;
    public static void main(String[] args) {
        try {
            IEmployeeDao dao = new DatabaseDao("database.properties");
            operation = new ServerOperationTCP(dao);
            operation.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

