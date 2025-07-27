package test.Console;

import Console.Doctor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {
    Doctor doca = new Doctor("Amarabandu","Rupasinha", LocalDate.parse("1987-09-09"),"0987654321","o098","dermatologist");

    @Test
    void getMedicalLicence() {
        assertEquals("o098",doca.getMedicalLicence());
    }

    @Test
    void setMedicalLicence() {
        doca.setMedicalLicence("j44");
        assertEquals("j44",doca.getMedicalLicence());
    }

    @Test
    void getSpecialisation() {
        assertEquals("dermatologist",doca.getSpecialisation());
    }

    @Test
    void setSpecialisation() {
        doca.setSpecialisation("mental");
        assertEquals("mental",doca.getSpecialisation());
    }
}