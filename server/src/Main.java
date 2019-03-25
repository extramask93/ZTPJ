import ServerOperation.IServerOperation;
import ServerOperation.ServerOperationRMI;

public class Main {
    static IServerOperation operation = null;
    public static void main(String[] args) {
        try {
            operation = new ServerOperationRMI();
            operation.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

