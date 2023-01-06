package Console;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    private  Patient patient = new Patient("navindya","denipitiya", LocalDate.parse("2004-09-09"),"0987654321",Integer.parseInt("2004"));

    @Test
    void getPatientId() {
        assertEquals(Integer.parseInt("2004"),patient.getPatientId());
    }

    @Test
    void setPatientId() {
        patient.setPatientId(Integer.parseInt("2005"));
        assertEquals(Integer.parseInt("2005"),patient.getPatientId());
    }
}