package com.company.Model;

import java.math.BigDecimal;

@SuppressWarnings("Duplicates")
public class Handlowiec extends IPracownik {
    protected BigDecimal Commision;
    protected BigDecimal commisionMonthlyLimit;

    public BigDecimal getCommision() {
        return Commision;
    }

    public void setCommision(BigDecimal commision) {
        Commision = commision;
    }

    public BigDecimal getCommisionMonthlyLimit() {
        return commisionMonthlyLimit;
    }

    public void setCommisionMonthlyLimit(BigDecimal commisionMonthlyLimit) {
        this.commisionMonthlyLimit = commisionMonthlyLimit;
    }

    public Handlowiec() {
        setJobTitle(JobTitleType.Handlowiec);
        Commision = BigDecimal.valueOf(0);
        commisionMonthlyLimit = BigDecimal.valueOf(0);
    }

    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append("Prowizja (%)\t:\t"+Commision+'\n');
        builder.append("Limit prowizji/miesiac \t:\t"+commisionMonthlyLimit+'\n');
        return builder.toString();
    }
}
