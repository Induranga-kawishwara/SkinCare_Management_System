package Console;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ConsultationTest {
    Consultation consul = new Consultation(Integer.parseInt("1"),"Saman","pissa", LocalDate.parse("2009-09-09"),"0987654321",Integer.parseInt("32"),"Dco23", LocalTime.parse("02:12:21"),LocalTime.parse("05:12:21"),LocalDate.parse("2023-09-09"),"rabbit",Double.parseDouble("123"),"key");


    @Test
    void getConsul_No() {
        assertEquals(Integer.parseInt("1"),consul.getConsul_No());
    }

    @Test
    void setConsul_No() {
        consul.setConsul_No(Integer.parseInt("10"));
        assertEquals(Integer.parseInt("10"),consul.getConsul_No());
    }

    @Test
    void getConsul_Date() {
        assertEquals(LocalDate.parse("2023-09-09"),consul.getConsul_Date());
    }

    @Test
    void setConsul_Date() {
        consul.setConsul_Date(LocalDate.parse("2023-01-05"));
        assertEquals(LocalDate.parse("2023-01-05"),consul.getConsul_Date());
    }

    @Test
    void getConsul_StartTime() {
        assertEquals(LocalTime.parse("02:12:21"),consul.getConsul_StartTime());
    }

    @Test
    void setConsul_StartTime() {
        consul.setConsul_StartTime(LocalTime.parse("04:12:21"));
        assertEquals(LocalTime.parse("04:12:21"),consul.getConsul_StartTime());
    }

    @Test
    void getConsulted_DocId() {
        assertEquals("Dco23",consul.getConsulted_DocId());
    }

    @Test
    void setConsulted_DocId() {
        consul.setConsulted_DocId("doc10");
        assertEquals("doc10",consul.getConsulted_DocId());
    }

    @Test
    void getAdditional_Note() {
        assertEquals("rabbit",consul.getAdditional_Note());
    }

    @Test
    void setAdditional_Note() {
        consul.setAdditional_Note("catt");
        assertEquals("catt",consul.getAdditional_Note());
    }

    @Test
    void setConsul_EndTime() {
        consul.setConsul_EndTime(LocalTime.parse("10:12:21"));
        assertEquals(LocalTime.parse("10:12:21"),consul.getConsul_EndTime());
    }

    @Test
    void getConsul_EndTime() {
        assertEquals(LocalTime.parse("05:12:21"),consul.getConsul_EndTime());
    }

    @Test
    void getCost() {
        assertEquals(Double.parseDouble("123"),consul.getCost());
    }

    @Test
    void setCost() {
        consul.setCost(Double.parseDouble("34"));
        assertEquals(Double.parseDouble("34"),consul.getCost());
    }

    @Test
    void getSecurity_Key() {
        assertEquals("key",consul.getSecurity_Key());
    }

    @Test
    void setSecurity_Key() {
        consul.setSecurity_Key("keytest");
        assertEquals("keytest",consul.getSecurity_Key());
    }

}