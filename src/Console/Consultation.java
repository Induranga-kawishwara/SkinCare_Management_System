package Console;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation extends Patient {

    //localDateTime stores both date and time unlike Local date which stores only the date
    private int consul_No;
    private LocalDate consul_Date;
    private LocalTime consul_StartTime;
    private LocalTime consul_EndTime;
    private String consulted_DocId;
    private String additional_Note;
    private String security_Key;
    private double cost;




    public Consultation(int consul_No, String name, String surname, LocalDate dateOfBirth, String mobile_No, int patId, String consulted_DocId, LocalTime consul_StartTime, LocalTime consul_EndTime, LocalDate consul_Date, String additional_Note, double cost, String security_Key) {
        super(name,surname,dateOfBirth,mobile_No,patId);
        this.consul_No = consul_No;
        this.consul_Date = consul_Date;
        this.consul_StartTime = consul_StartTime;
        this.consul_EndTime = consul_EndTime;
        this.consulted_DocId = consulted_DocId;
        this.additional_Note = additional_Note;
        this.cost=cost;
        this.security_Key = security_Key;
    }

    //getters and setters
    //consultation date

    public int getConsul_No() {
        return consul_No;
    }

    public void setConsul_No(int consul_No) {
        this.consul_No = consul_No;
    }

    public LocalDate getConsul_Date() {
        return consul_Date;
    }

    public void setConsul_Date(LocalDate consul_Date) {
        this.consul_Date = consul_Date;
    }
    //consultation time
    public LocalTime getConsul_StartTime() {
        return consul_StartTime;
    }

    public void setConsul_StartTime(LocalTime consul_StartTime) {
        this.consul_StartTime = consul_StartTime;
    }
    //consultation slot
    public String getConsulted_DocId() {
        return consulted_DocId;
    }

    public void setConsulted_DocId(String consulted_DocId) {
        this.consulted_DocId = consulted_DocId;
    }
    //consultation note
    public String getAdditional_Note() {
        return additional_Note;
    }

    public void setAdditional_Note(String additional_Note) {
        this.additional_Note = additional_Note;
    }

    public void setConsul_EndTime(LocalTime consul_EndTime) {
        this.consul_EndTime = consul_EndTime;
    }

    public LocalTime getConsul_EndTime() {
        return consul_EndTime;
    }

    public double getCost() {return cost;}

    public void setCost(double cost) {this.cost = cost;}

    public String getSecurity_Key() {
        return security_Key;
    }

    public void setSecurity_Key(String security_Key) {
        this.security_Key = security_Key;
    }

    @Override
    public String toString() {
        return  "01.Name                 : " + this.getName() + '\n' +
                "02.SurName              : " + this.getSurname() + '\n' +
                "03.Date-of-Birth        : " + this.getDateOfBirth() + '\n' +
                "04.Mobile-No            : " + this.getMobileNo() + '\n' +
                "05.Patient-ID           : " + this.getPatientId() + '\n' +
                "06.Consultation-Date    : " + consul_Date +'\n'+
                "07.Start-Time           : " + consul_StartTime +'\n'+
                "08.End-Time             : " + consul_EndTime +'\n'+
                "09.Consulted-Doctor     : " + consulted_DocId +'\n'+
                "10.Cost-for-Consultation: " + cost+'\n'+
                "11.Additional-Note      : \n" + additional_Note +'\n';
    }
}
