package Console;

import java.time.LocalDate;

public class Doctor extends Person {
    private String medicalLicence;
    private String specialisation;

    public Doctor(String name, String surname, LocalDate dateOfBirth, String mobileNo, String medicalLicence, String specialisation) {
        super(name, surname, dateOfBirth, mobileNo);
        this.medicalLicence = medicalLicence;
        this.specialisation = specialisation;
    }

    public String getMedicalLicence() {
        return medicalLicence;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    @Override
    public String toString() {
        return  "01.Name             : " + this.getName() + '\n' +
                "02.SurName          : " + this.getSurname() + '\n' +
                "03.Date-of-Birth    : " + this.getDateOfBirth() + '\n' +
                "04.Mobile-No        : " + this.getMobileNo() + '\n' +
                "05.MedicalLicence   : " + medicalLicence + '\n' +
                "06.Specialisation   : " + specialisation +'\n';
    }
}
