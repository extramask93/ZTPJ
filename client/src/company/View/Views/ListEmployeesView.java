package company.View.Views;
import Models.PracownikList;
import company.View.Cnt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class ListEmployeesView implements IView {
    @Override
    public String getName() {
        return "Lista pracownikow";
    }

    @Override
    public void run() throws IOException, SQLException {
        printEmplyess();
    }
    public void printEmplyess() throws IOException, SQLException {
        PracownikList pracownicy = Cnt.getMC().getEmployees();
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        char choice = '\r';
        if(pracownicy.getList().size() ==0)
            return;
        while(choice != 'Q') {
            System.out.println("1. Lista pracownikow");
            if(index < pracownicy.getList().size()) System.out.print(pracownicy.getList().get(index));
            System.out.printf("[Pozycja : %d/%d]\n",index+1,pracownicy.getList().size());
            System.out.println("[Enter] : nastepny");
            System.out.println("[Q] : powrot");
            String response = scanner.nextLine();
            if(response.isEmpty()) {index = (++index) % (pracownicy.getList().size());}
            else {choice = response.charAt(0);}
        }
    }
}
