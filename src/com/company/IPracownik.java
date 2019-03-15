package com.company;

import java.math.BigDecimal;

public abstract class IPracownik {
    protected String Pesel;
    protected String FirstName;
    protected String LastName;
    protected BigDecimal Salary;
    protected String PhoneNumber;
    protected JobTitleType JobTitle;
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
}

