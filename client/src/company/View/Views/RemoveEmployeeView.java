package company.View.Views;

import Models.Pracownik;
import company.View.Cnt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveEmployeeView implements IView {
    @Override
    public String getName() {
        return "Usun pracownika";
    }

    @Override
    public void run() throws IOException, SQLException {
        String result = getPesel();
        Pracownik pr = new Pracownik();
        pr.setPesel(result);
        Cnt.getMC().removeEmployee(pr);

    }
    private String getPesel() {
        System.out.print("Podaj pesel: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}
