package company.View.Views;

import Models.Dyrektor;
import Models.Handlowiec;
import Models.Pracownik;
import Models.JobTitleType;
import company.View.Cnt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class AddEmployeeView implements IView {
    @Override
    public String getName() {
        return "Dodaj pracownika";
    }

    @Override
    public void run() throws IOException, SQLException {
        Pracownik employee = getEmplyee();
        Cnt.getMC().addEmployee(employee);
    }
    public Pracownik getEmplyee() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("[D]yrektor/[H]handlowiec:");
        char type = scanner.nextLine().charAt(0);
        Pracownik pracownik = type == 'H' ? new Handlowiec(): new Dyrektor();
        System.out.print("Identyfikator PESEL : ");
        pracownik.setPesel(scanner.nextLine());
        System.out.print("Imie : ");
        pracownik.setFirstName(scanner.nextLine());
        System.out.print("Nazwisko : ");
        pracownik.setLastName(scanner.nextLine());
        System.out.print("Wynagrodzenie : ");
        pracownik.setSalary(scanner.nextBigDecimal());
        scanner.nextLine();
        System.out.print("Telefon : ");
        pracownik.setPhoneNumber(scanner.nextLine());
        switch (type) {
            case 'D':
            {
                pracownik.setJobTitle(JobTitleType.Dyrektor);
                System.out.println("Dodatek : ");
                pracownik.setSalaryAddition(scanner.nextBigDecimal());
                scanner.nextLine();
                System.out.println("Numer Karty : ");
                ((Dyrektor) pracownik).setCardNumber(scanner.nextLine());
                System.out.println("Limit Kosztow : ");
                ((Dyrektor) pracownik).setExpansesLimit(scanner.nextBigDecimal());
                break;
            }
            case 'H':
            {
                pracownik.setJobTitle(JobTitleType.Handlowiec);
                System.out.print("Dodatek : ");
                pracownik.setSalaryAddition(scanner.nextBigDecimal());
                System.out.print("Prowizja : ");
                ((Handlowiec) pracownik).setCommision(scanner.nextBigDecimal());
                System.out.print("Limit prowizji : ");
                ((Handlowiec) pracownik).setCommisionMonthlyLimit(scanner.nextBigDecimal());
                break;
            }
            default:
                throw new IOException("Nieznany typ pracownika");
        }
        return pracownik;
    }
}
