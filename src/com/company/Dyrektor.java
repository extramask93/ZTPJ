package com.company;

import java.math.BigDecimal;

@SuppressWarnings("Duplicates")
public class Dyrektor extends IPracownik {
    private BigDecimal SalaryAddition;
    private String CardNumber;
    private BigDecimal ExpansesLimit;
    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder();
        builder.append("Identyfikator PESEL\t:\t"+Pesel);
        builder.append("Imie\t:\t"+FirstName);
        builder.append("Nazwisko\t:\t"+LastName);
        builder.append("Stanowisko\t:\t"+JobTitle.toString());
        builder.append("Wynagrodzenie\t:\t"+Salary.toString());
        builder.append("Telefon sluzbowy numer\t:\t"+PhoneNumber);
        builder.append("Dodatek sluzbowy\t:\t"+SalaryAddition.toString());
        builder.append("Karta sluzbowa numer\t:\t"+CardNumber);
        builder.append("Limit kosztow/miesiac\t:\t"+ExpansesLimit.toString());
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
