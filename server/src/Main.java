import ServerOperation.IServerOperation;
import ServerOperation.ServerOperationTCP;

public class Main {
    static IServerOperation operation = null;
    public static void main(String[] args) {
        try {
            operation = new ServerOperationTCP();
            operation.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

