package Console;

import java.time.LocalDate;

public class Patient extends Person{

    //should be unique(can proceed with an array also)

    private int patientId;

    public Patient(String name, String surname, LocalDate dateOfBirth ,String mobileNo, int patientId) {
        super(name, surname, dateOfBirth, mobileNo);
        this.patientId = patientId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

}
