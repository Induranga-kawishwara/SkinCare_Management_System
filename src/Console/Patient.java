package Console;

import java.time.LocalDate;

public class Patient extends Person{


    private int patientId;
    //CREATED A CONSTRUCTOR FOR THIS CLASS

    public Patient(String name, String surname, LocalDate dateOfBirth ,String mobileNo, int patientId) {
        super(name, surname, dateOfBirth, mobileNo);
        this.patientId = patientId;
    }
    //GETTERS AND SETTERS

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

}
