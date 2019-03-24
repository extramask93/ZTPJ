package Model;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
@XmlRootElement(name = "ipracownik")
public class IPracownik
{
    protected String Pesel;
    protected String FirstName;
    protected String LastName;
    protected BigDecimal Salary;
    protected String PhoneNumber;
    protected JobTitleType JobTitle;
    protected BigDecimal SalaryAddition;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Identyfikator PESEL\t:\t"+Pesel+'\n');
        builder.append("Imie\t:\t"+FirstName+'\n');
        builder.append("Nazwisko\t:\t"+LastName+'\n');
        builder.append("Stanowisko\t:\t"+JobTitle.name()+'\n');
        builder.append("Wynagrodzenie\t:\t"+Salary.toString()+'\n');
        builder.append("Telefon sluzbowy numer\t:\t"+PhoneNumber+'\n');
        return builder.toString();
    }

    public BigDecimal getSalaryAddition() {
        return SalaryAddition;
    }

    public void setSalaryAddition(BigDecimal salaryAddition) {
        SalaryAddition = salaryAddition;
    }

    public IPracownik() {
        Pesel = "";
        FirstName = "";
        LastName = "";
        Salary = BigDecimal.valueOf(0);
        PhoneNumber = "";
        JobTitle = JobTitleType.Handlowiec;
    }

    public IPracownik(String pesel, String firstName, String lastName, BigDecimal salary, String phoneNumber, JobTitleType jobTitle) {
        Pesel = pesel;
        FirstName = firstName;
        LastName = lastName;
        Salary = salary;
        PhoneNumber = phoneNumber;
        JobTitle = jobTitle;
    }
    public String getPesel() {
        return Pesel;
    }

    public void setPesel(String pesel) {
        Pesel = pesel;
    }
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public BigDecimal getSalary() {
        return Salary;
    }

    public void setSalary(BigDecimal salary) {
        Salary = salary;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public JobTitleType getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(JobTitleType jobTitle) {
        JobTitle = jobTitle;
    }
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            IPracownik q = (IPracownik) obj;
            return getPesel() == q.getPesel();
        }
        return false;
    }
}

