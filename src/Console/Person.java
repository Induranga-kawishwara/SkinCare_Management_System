package Console;

import java.time.LocalDate;
import java.util.*;
public class Person {
    private String name;
    private String surname;
    //Local date is being used, because it is hard to compare strings .
    private LocalDate dateOfBirth;
    private String mobileNo;

    public Person(String name,String surname,LocalDate dateOfBirth,String mobileNo){
        this.name=name;
        this.surname=surname;
        this.dateOfBirth=dateOfBirth;
        this.mobileNo=mobileNo;
    }
    //GETTERS AND SETTERS


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

}
