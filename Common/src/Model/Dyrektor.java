package Model;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
@XmlRootElement(name = "dyrektor")
public class Dyrektor extends IPracownik {
    private String CardNumber;
    private BigDecimal ExpansesLimit;

    public Dyrektor() {
        setJobTitle(JobTitleType.Dyrektor);
        SalaryAddition = BigDecimal.valueOf(0);
        CardNumber = "";
        ExpansesLimit = BigDecimal.valueOf(0);
    }

    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append("Dodatek sluzbowy\t:\t"+SalaryAddition.toString()+'\n');
        builder.append("Karta sluzbowa numer\t:\t"+CardNumber+'\n');
        builder.append("Limit kosztow/miesiac\t:\t"+ExpansesLimit.toString()+'\n');
        return builder.toString();
    }
    public BigDecimal getSalaryAddition() {
        return SalaryAddition;
    }

    public void setSalaryAddition(BigDecimal salaryAddition) {
        SalaryAddition = salaryAddition;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public BigDecimal getExpansesLimit() {
        return ExpansesLimit;
    }

    public void setExpansesLimit(BigDecimal expansesLimit) {
        ExpansesLimit = expansesLimit;
    }
}
