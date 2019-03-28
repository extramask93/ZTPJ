package company.View.Views;

import java.util.Scanner;

public class ExitView implements IView {
    @Override
    public String getName() {
        return "Wyjscie";
    }

    @Override
    public void run() {
        System.out.print("Napewno chcesz wyjsc? [Y/N] : ");
        Scanner sc = new Scanner(System.in);
        if(sc.nextLine().charAt(0)=='Y');
            System.exit(0);
    }
}
