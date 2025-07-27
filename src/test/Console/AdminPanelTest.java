package test.Console;

import Console.Doctor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdminPanelTest {

    private static final ArrayList<Doctor> DoctorArrayList = new ArrayList<>();

    @Test
    void addDoctor() {
        Doctor doctor=new Doctor("induranga","kawishwara", LocalDate.parse("1987-02-17"),"0112781951","D013","dermatologist");
        DoctorArrayList.add(doctor);
        assertTrue(DoctorArrayList.contains(doctor));

    }

    @Test
    void deleteDoctor() {
        Doctor doctor=new Doctor("induranga","kawishwara", LocalDate.parse("1987-02-17"),"0112781951","D013","dermatologist");
        DoctorArrayList.add(doctor);
        DoctorArrayList.remove(doctor);
        assertFalse(DoctorArrayList.contains(doctor));
    }
}