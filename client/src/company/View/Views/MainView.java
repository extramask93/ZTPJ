package company.View.Views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainView implements IView {
    HashMap<Integer, IView> menu = new HashMap<>();
    IView currentView = null;
    public MainView() {
        menu.putIfAbsent(1, new ListEmployeesView());
        menu.putIfAbsent(2, new AddEmployeeView());
        menu.putIfAbsent(3, new RemoveEmployeeView());
        menu.putIfAbsent(4, new DownloadDataView());
        menu.putIfAbsent(5, new ExitView());
    }

    @Override
    public String getName() {
        return "Menu";
    }

    public void run() throws IOException, SQLException {
        while(true) {
            int selection = printMenu();
            currentView = menu.get(selection);
            currentView.run();
        }

    }
    private int printMenu() {
        System.out.println("MENU:");
        for(Map.Entry<Integer, IView> entry : menu.entrySet()) {
            System.out.println(entry.getKey().toString()+". "+entry.getValue().getName());
        }
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
