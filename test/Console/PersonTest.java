package Console;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private  Person p1 = new Person("Induranga","Kawishwara", LocalDate.parse("1978-09-09"),"0123456789");

    @Test
    void getName() {
        assertEquals("Induranga",p1.getName());
    }

    @Test
    void setName() {
        p1.setName("madushan");
        assertEquals("madushan",p1.getName());

    }

    @Test
    void getSurname() {
        assertEquals("Kawishwara",p1.getSurname());
    }

    @Test
    void setSurname() {
        p1.setSurname("kavidu");
        assertEquals("kavidu",p1.getSurname());
    }

    @Test
    void getDateOfBirth() {
        assertEquals(LocalDate.parse("1978-09-09"),p1.getDateOfBirth());
    }

    @Test
    void setDateOfBirth() {
        p1.setDateOfBirth(LocalDate.parse("1977-07-09"));
        assertEquals(LocalDate.parse("1977-07-09"),p1.getDateOfBirth());
    }

    @Test
    void getMobileNo() {
        assertEquals("0123456789",p1.getMobileNo());
    }

    @Test
    void setMobileNo() {
        p1.setMobileNo("0987654321");
        assertEquals("0987654321",p1.getMobileNo());
    }
}