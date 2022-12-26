package GUI;

import Console.Consultation;
import Console.Doctor;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Console.WestminsterSkinConsultationManager.doctorArray;

import  static Console.WestminsterSkinConsultationManager.consult;

public class Frame1 extends GUI_table implements ActionListener {


    private String name,surname,phoneno,patId,docconsulId,note,sttimeHou,sttimeMin,stasettime,entimeHou,entimeMin,ensettime;

    private int cost;

    private LocalDate dateOfBirth,cousulDate;

    private LocalTime consulStart,consulEnd;
    private JButton back,Cancel,submit;
    JTextField getname,getbirthday,getid,getdate,getsurname,getphone;
    JComboBox getdoc,getstarttime1,getstarttime2,getendtime1,getendtime2;
    JTextArea getnote;
    JLabel topic,colum,topic1,jname,birthday,id,time,date,jnote;
    Frame1(){


        topic = new JLabel();
        topic.setText("ALL DOCTORS DETAILS");
        set_lable(topic,290,20,550,20,20);


        colum = new JLabel();
        colum.setText("|     Name     |    SurName    |   Phone-No   |   Licence    | Specialisation |");
        colum.setForeground(new Color(246, 10, 43, 255));
        set_lable(colum,39, -70, 750, 300,15);


        DefaultTableModel tableModel = new DefaultTableModel(0,5);
        JTable table = new JTable(tableModel);
        table.setBounds(40, 100, 700, 240);

        for (Console.Doctor doctor : doctorArray) {
            String[] details = {doctor.getName(), doctor.getSurname(), doctor.getMobileNo(), doctor.getMedicalLicence(), doctor.getSpecialisation()};
            tableModel.addRow(details);
        }

        topic1 = new JLabel();
        topic1.setText("DOCTOR CONSULTATION");
        set_lable(topic1,280, 360, 550, 20,20);


        jname = new JLabel();
        jname.setText("NAME                 :                    SURNAME           :");
        set_lable(jname,30, 420, 550, 20,15);


        getname = new JTextField();
        set_textfild(getname,240,425,150,20);

        getsurname = new JTextField();
        set_textfild(getsurname,600,425,150,20);


        birthday = new JLabel();
        birthday.setText("BIRTHDAY(YYY-MM-DD)  :                    PHONE NUMBER      :");
        set_lable(birthday,30, 470, 550, 20,15);


        getbirthday = new JTextField();
        set_textfild(getbirthday,240,475,150,20);

        getphone = new JTextField();
        set_textfild(getphone,600,475,150,20);

        id = new JLabel();
        id.setText("PATIENT ID           :                    DOCTOR LICENCE ID :");
        set_lable(id,30, 520, 550, 20,15);


        getid = new JTextField();
        set_textfild(getid,240,525,150,20);


        String [] doc = new String[doctorArray.size()];
        for(int i = 0;doctorArray.size()>i;i++){
            doc[i]=doctorArray.get(i).getMedicalLicence();
        }
        getdoc =new JComboBox(doc);
        set_combobox(getdoc,600,525,150,20);
//        getdoc.setBounds(600,525,150,20);
//        getdoc.setBackground(new Color(0xFFFFFF));

        time = new JLabel();
        time.setText("CONSULTATION START TIME:                 CONSULTATION END TIME:");
        set_lable(time,30, 570, 700, 20,15);


        String[] hours = { "HH","08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20","21","22" };
        String[] min = { "MM","00", "15", "30", "45" };

        //J combo box
        getstarttime1 =new JComboBox(hours);
        set_combobox(getstarttime1,260,575,50,20);
//        getstarttime1.setBounds(260,575,50,20);
//        getstarttime1.setBackground(new Color(0xFFFFFF));

        getstarttime2 =new JComboBox(min);
        set_combobox(getstarttime2,340,575,50,20);
//        getstarttime2.setBounds(340,575,50,20);
//        getstarttime2.setBackground(new Color(0xFFFFFF));

        getendtime1 =new JComboBox(hours);
        set_combobox(getendtime1,618,575,50,20);
//        getendtime1.setBounds(618,575,50,20);
//        getendtime1.setBackground(new Color(0xFFFFFF));

        getendtime2 =new JComboBox(min);
        set_combobox(getendtime2,700,575,50,20);
//        getendtime2.setBounds(700,575,50,20);
//        getendtime2.setBackground(new Color(0xFFFFFF));


        date = new JLabel();
        date.setText("CONSULTATION DATE(YYY-MM-DD):");
        set_lable(date,30, 620, 550, 20,15);


        getdate = new JTextField();
        set_textfild(getdate,300,625,150,20);

        jnote = new JLabel();
        jnote.setText("ADDITIONAL NOTE :");
        set_lable(jnote,30, 670, 550, 20,15);


        getnote = new JTextArea();
        getnote.setBounds(30,700,500,200);
        getnote.setFont(new Font("console",Font.ITALIC,15));
        getnote.setLineWrap(true);

        JLabel addpho = new JLabel();
        addpho.setBounds(540, 700, 220, 200);
        addpho.setBackground(Color.white);
        addpho.setOpaque(true);


        this.add(table);
//        this.add(getdoc);
//        this.add(getstarttime1);
//        this.add(getstarttime2);
//        this.add(getendtime1);
//        this.add(getendtime2);
        this.add(getnote);
        this.add(addpho);


        System.out.println("GUI file eka wada hutto");
        button();
        window("Consultation",800,1000);
    }
    public void check_equal(){
        Duration duration = Duration.between(consulStart, consulEnd);
        int timedura = (int) duration.toHours();
        int again = 0;
        boolean not_equal = true;
        for (Consultation consultation : consult) {
            if (docconsulId.equals(consultation.getDocconsulId())) {
                if (consultation.getConDate().isEqual(cousulDate)) {
                    if (!consultation.getConsulStart().isBefore(consulStart) && !consultation.getConsulEnd().isAfter(consulEnd)) {
                        not_equal = false;
                        break;
                    }
                }
            }
        }
        for(Consultation consultation : consult){
            if(Objects.equals(consultation.getPatientId(), patId)){
                again++;
            }
        }
        if(again==0){
            cost=(15+((timedura-1)*25));
            System.out.println(cost);

        }else{
            cost = 25*timedura;
            System.out.println(cost);
        }
        if(not_equal){
            consult.add(new Consultation(name, surname, dateOfBirth, phoneno,patId,docconsulId, consulStart,consulEnd,cousulDate,note));
        }else{
            int docsiz=doctorArray.size();
            String [] random = new String[docsiz];
            for(int i =0;docsiz > i ;i++){
                random[i] = doctorArray.get(i).getMedicalLicence();
            }
            Random rand = new Random();
            int randomIndex = rand.nextInt(random.length);
            docconsulId = random[randomIndex];
            check_equal();
        }
    }
    public void set_combobox(JComboBox combo,int x, int y , int width , int height){
        combo.setBounds(x,y,width,height);
        combo.setBackground(new Color(0xFFFFFF));
        this.add(combo);
    }
    public void set_lable(JLabel lable ,int x, int y , int width , int height,int font){
        lable.setBounds(x,y,width,height);
        lable.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,font));
        this.add(lable);
    }

    public void set_textfild(JTextField textfild,int x, int y , int width , int height){//150,20
        textfild.setBounds(x,y,width,height);
        textfild.setFont(new Font("console",Font.ITALIC,15));
        this.add(textfild);
    }
    public void button_set(JButton but,String name, int x, int y , int width , int height) {
        but.setBounds(x,y,width,height);
        but.setText(name);
        System.out.println("button set eka asse");
        but.setFocusable(false);
        but.addActionListener(this);
        this.add(but);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()== submit) {

            try {
                boolean virgin = false;
                String regex = "^[A-Za-z]\\w{2,29}$";
                Pattern p = Pattern.compile(regex);
                if (10 > doctorArray.size()) {
                    name = getname.getText().trim();
                    surname = getsurname.getText().trim();
                    Matcher f = p.matcher(name);
                    Matcher s = p.matcher(surname);
                    if (f.matches() && s.matches()) {
                        dateOfBirth = LocalDate.parse(getbirthday.getText().trim());
                            phoneno = getphone.getText().trim();
                            // validation for mobilenumber
                            if (10 == phoneno.length()) {
                                Integer.parseInt(phoneno);
                                patId = getid.getText().trim();
                                docconsulId = (String) getdoc.getSelectedItem();
                                for (Doctor doctor : doctorArray) {
                                    if (docconsulId.equals(doctor.getMedicalLicence())) {
                                        System.out.println("\n-Medical licence already exists-");
                                        System.out.println("-Please re-enter the correct details-\n");
                                        virgin = true;
                                        break;
                                    }
                                }
                                if (virgin) {
                                    sttimeHou = (String) getstarttime1.getSelectedItem();
                                    sttimeMin = (String) getstarttime2.getSelectedItem();
                                    stasettime = sttimeHou+":"+sttimeMin+":00";
                                    consulStart = LocalTime.parse(stasettime);

                                    entimeHou = (String) getendtime1.getSelectedItem();
                                    entimeMin = (String) getendtime2.getSelectedItem();
                                    ensettime = entimeHou+":"+entimeMin+":00";
                                    consulEnd = LocalTime.parse(ensettime);

                                    cousulDate = LocalDate.parse(getdate.getText().trim());

                                    note = getnote.getText();
                                    check_equal();
                                    System.out.println(consult.get(0));

                                    this.dispose();
                                    new Gui_main();
                                }
                            } else {
                                System.out.println("Check entered phone number!!!");
                            }
                    } else {
                        System.out.println("Check entered  First name or Sure name !!!!");
                    }
                }
            }catch (Exception ignored){
                System.out.println("Invalid Input");
            }


//            name = getname.getText().trim();
//
//            surname = getsurname.getText().trim();

//            dateOfBirth = LocalDate.parse(getbirthday.getText().trim());

//            phoneno = getphone.getText().trim();

//            patId = getid.getText().trim();

//            docconsulId = (String) getdoc.getSelectedItem();

//            sttimeHou = (String) getstarttime1.getSelectedItem();
//
//
//            sttimeMin = (String) getstarttime2.getSelectedItem();
//            stasettime = sttimeHou+":"+sttimeMin+":00";
//
//            consulStart = LocalTime.parse(stasettime);
//
//            entimeHou = (String) getendtime1.getSelectedItem();
//
//            entimeMin = (String) getendtime2.getSelectedItem();
//            ensettime = entimeHou+":"+entimeMin+":00";
//
//            consulEnd = LocalTime.parse(ensettime);

//            cousulDate = LocalDate.parse(getdate.getText().trim());
//
//            note = getnote.getText();
//            check_equal();
//            System.out.println(consult.get(0));
//
//            this.dispose();
//            new Gui_main();

        }else{
            this.dispose();
            new Gui_main();
        }

    }

    @Override
    public void button() {
        back = new JButton();
        back.setFont(new Font("SansSerif", Font.BOLD, 12));
        button_set(back,"Back",30,20,65,30);

        submit = new JButton();
        button_set(submit,"Submit",550,920,80,30);

        Cancel = new JButton();
        button_set(Cancel,"Cancel",660,920,80,30);

    }
}
