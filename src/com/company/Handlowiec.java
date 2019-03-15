package com.company;

import java.math.BigDecimal;

@SuppressWarnings("Duplicates")
public class Handlowiec extends IPracownik {
    protected BigDecimal Commision;
    protected BigDecimal commisionMonthlyLimit;
    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder();
        builder.append("Identyfikator PESEL\t:\t"+Pesel);
        builder.append("Imie\t:\t"+FirstName);
        builder.append("Nazwisko\t:\t"+LastName);
        builder.append("Stanowisko\t:\t"+JobTitle.toString());
        builder.append("Wynagrodzenie\t:\t"+Salary.toString());
        builder.append("Telefon sluzbowy numer\t:\t"+PhoneNumber);
        builder.append("Prowizja (%)\t:\t"+Commision);
        builder.append("Limit prowizji/miesiac \t:\t"+commisionMonthlyLimit);

        return builder.toString();
    }
}
