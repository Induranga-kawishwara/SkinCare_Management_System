package GUI;

import Console.Consultation;


import javax.crypto.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.Base64;


import static Console.WestminsterSkinConsultationManager.doctorArray;

import  static Console.WestminsterSkinConsultationManager.consult;

public class Consultation_frame extends GUI_table implements ActionListener {


    private String name;
    private String surname;
    private String mobile_No;
    private String consulted_DocId;
    private String additional_Note;
    private String consult_Endtime_Hours;
    private String security_Key;

    private String filename = null;

    private int patient_Id;
    private int again = 1;

    private LocalDate dateOfBirth, consul_Date;

    private LocalTime consul_StartTime, consul_EndTime;
    private JButton submit;
    private JButton pic;
    private int  randomnum = 0;
    private final JTextField get_name;
    private final JTextField get_birthday;
    private final JTextField get_patientId;
    private final JTextField get_consultDate;
    private final JTextField get_surname;
    private final JTextField get_mobile;
    private final JComboBox get_docId;
    private final JComboBox get_startTime1;
    private final JComboBox get_startTime2;
    private final JComboBox get_duration;
    private final JTextArea get_additional_note;
    private final JLabel add_image;
    private final JLabel add_imagePath;
    Consultation_frame(){

        //GOT IMAGE PATH USING ImageIcon
        ImageIcon img = new ImageIcon("src/GUI/Consultation_frame.jpg");

        //ADDED A TOPIC USING JLabel
        JLabel topic = new JLabel();
        topic.setText("ALL DOCTORS DETAILS");
        set_lable(topic,290,20,550,20,20);

        //ADDED COLUMNS IN TO THE TABLE USING JLabel
        JLabel colum = new JLabel();
        colum.setText("|     Name     |    SurName    |   Phone-No   |   Licence    | Specialisation |");
        colum.setForeground(new Color(246, 10, 43, 255));
        set_lable(colum,39, -70, 750, 300,15);

        //CREATED A TABLE
        DefaultTableModel tableModel = new DefaultTableModel(0,5);
        JTable table = new JTable(tableModel);
        table.setBounds(40, 100, 700, 240);

        for (int i =0; doctorArray.size()>i;i++) {
            //GETTING DATA AND ASSAYING INTO THE ARRAY
            String[] details = {doctorArray.get(i).getName(), doctorArray.get(i).getSurname(), doctorArray.get(i).getMobileNo(), doctorArray.get(i).getMedicalLicence(), doctorArray.get(i).getSpecialisation()};
            //ADDING THE details ARRAY INTO THE TABLE AS ROW
            tableModel.addRow(details);
        }

        //ADDED SECOND TOPIC  USING JLabel
        JLabel topic1 = new JLabel();
        topic1.setText("DOCTOR CONSULTATION");
        set_lable(topic1,280, 360, 550, 20,20);

        //ADDED LABEL USING JLabel
        JLabel jname = new JLabel();
        jname.setText("NAME                 :                    SURNAME           :");
        set_lable(jname,30, 420, 550, 20,15);

        //GETTING THE USER INPUT USING JTextField
        get_name = new JTextField();
        set_textfild(get_name,240,425,150,20);

        get_surname = new JTextField();
        set_textfild(get_surname,600,425,150,20);

        //ADDED LABEL USING JLabel
        JLabel birthday = new JLabel();
        birthday.setText("BIRTHDAY(YYY-MM-DD)  :                    PHONE NUMBER      :");
        set_lable(birthday,30, 470, 550, 20,15);

        //GETTING THE USER INPUT USING JTextField
        get_birthday = new JTextField();
        set_textfild(get_birthday,240,475,150,20);

        get_mobile = new JTextField();
        set_textfild(get_mobile,600,475,150,20);

        //ADDED LABEL USING JLabel
        JLabel id = new JLabel();
        id.setText("PATIENT ID           :                    DOCTOR LICENCE ID :");
        set_lable(id,30, 520, 550, 20,15);

        //GETTING THE USER INPUT USING JTextField
        get_patientId = new JTextField();
        set_textfild(get_patientId,240,525,150,20);

        //ADDING DOCTOR'S IDS IN TO THE doc ARRAY
        String [] doc = new String[doctorArray.size()];
        for(int i = 0;doctorArray.size()>i;i++){
            doc[i]=doctorArray.get(i).getMedicalLicence();
        }
        //CREATED JComboBOX WITH  DOCTOR'S IDS
        get_docId =new JComboBox(doc);
        set_combobox(get_docId,600,525,150,20);

        //ADDED LABEL USING JLabel
        JLabel time = new JLabel();
        time.setText("CONSULTATION START TIME:                 CONSULTATION DURATION:");
        set_lable(time,30, 570, 700, 20,15);


        String[] hours = { "HH","08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20","21","22" };
        String[] min = { "MM","00", "15", "30", "45" };
        String[] dura = { "HH","01", "02", "03", "04", "05"};

        //J combo box
        get_startTime1 =new JComboBox(hours);
        set_combobox(get_startTime1,260,575,50,20);


        get_startTime2 =new JComboBox(min);
        set_combobox(get_startTime2,340,575,50,20);

        get_duration =new JComboBox(dura);
        set_combobox(get_duration,600,575,150,20);

        //ADDED LABEL USING JLabel
        JLabel date = new JLabel();
        date.setText("CONSULTATION DATE(YYY-MM-DD):");
        set_lable(date,30, 620, 550, 20,15);


        get_consultDate = new JTextField();
        set_textfild(get_consultDate,300,625,150,20);

        //ADDED LABEL USING JLabel
        JLabel jnote = new JLabel();
        jnote.setText("ADDITIONAL NOTE :");
        set_lable(jnote,30, 670, 550, 20,15);


        get_additional_note = new JTextArea();
        get_additional_note.setText(".");
        get_additional_note.setBounds(30,700,500,200);
        get_additional_note.setFont(new Font("console",Font.ITALIC,15));
        get_additional_note.setLineWrap(true);

        add_image = new JLabel();
        add_image.setBounds(540, 700, 220, 200);
        add_image.setBackground(Color.white);
        add_image.setOpaque(true);

        add_imagePath = new JLabel();
        add_imagePath.setBounds(540, 660, 150, 20);
        add_imagePath.setBackground(Color.white);
        add_imagePath.setOpaque(true);

        JLabel rat = new JLabel();
        rat.setIcon(img);
        rat.setBounds(0,0,800,1000);
//        rat.setpr(new Dimension(900,500));
        rat.setOpaque(true);


        this.add(table);
        this.add(get_additional_note);
        this.add(add_image);
        this.add(add_imagePath);


        button();
        this.add(rat);
        makeFrame("Consultation",800,1000);
    }
    //MADE THIS METHOD TO CHECKS THE CONSULTATION
    public void checks_equality(){
        //CONVERTING TO THE INTEGER VALUE USING Integer.paresInt
        int timedura = Integer.parseInt(consult_Endtime_Hours);
        boolean not_equal = true;
        //CHECKING AVAILABILITY THE USER ENTERED TIME AND DATE
        for (Consultation consultation : consult) {
            if (consulted_DocId.equals(consultation.getConsulted_DocId())) {
                if (consultation.getConsul_Date().isEqual(consul_Date)) {
                    if ((consultation.getConsul_StartTime().isBefore(consul_StartTime)) && (consultation.getConsul_EndTime().isAfter(consul_StartTime)) ||
                            (consultation.getConsul_StartTime().isBefore(consul_EndTime)) && ((consultation.getConsul_EndTime()).isAfter(consul_EndTime)) ||
                            ((consultation.getConsul_StartTime()).equals(consul_StartTime)) || ((consultation.getConsul_EndTime()).equals(consul_EndTime))) {
                        not_equal = false;
                        break;
                    }
                }
            }
        }
        //GENERATING THE COST FOR CONSULTATION
        int cost;
        if(again==1){
            cost =(timedura*15);



        }else{
            cost = 25*timedura;
        }


        if(not_equal){
            randomnum=0;
            //ADD DATA INTO THE consult ARRAYLIST
            consult.add(new Consultation(again,name, surname, dateOfBirth, mobile_No, patient_Id, consulted_DocId, consul_StartTime, consul_EndTime, consul_Date, additional_Note, cost, security_Key));
            try {
                //WRITING DATA INTO THE patient.txt USING BufferWriter
                BufferedWriter temp = new BufferedWriter(new FileWriter("patient.txt"));
                for (Consultation con : consult) {
                    temp.write(con.getConsul_No() + "\n" +con.getName() + "\n" + con.getSurname() + "\n" + con.getDateOfBirth() + "\n" + con.getMobileNo() + "\n" + con.getPatientId() + "\n" + con.getConsulted_DocId() + "\n" + con.getConsul_StartTime() + "\n" + con.getConsul_EndTime() + "\n" + con.getConsul_Date() + "\n" + con.getAdditional_Note() + "\n" + con.getCost() + "\n" + con.getSecurity_Key()+"\n\n");
                }
                temp.close();
            } catch (IOException e) {
                System.out.println("Something Wrong !!!!! ");
            }


        }else{
            //RANDOMLY CHOOSE THE DOCTOR FOR PATIENTS
            if(randomnum==0){
                JOptionPane.showMessageDialog(null, "The time selected for this doctor's appointment is already reserved and we selected another doctor for you..!!", "Important", JOptionPane.WARNING_MESSAGE);
            }
            int docsiz=doctorArray.size();
            //CREATED ARRAY TO STORE DOCTOR'S IDS
            String [] random = new String[docsiz];
            for(int i =0;docsiz > i ;i++){
                //GETTING DOCTOR'S IDS AND ASSIGN TO THE RANDOM ARRAY
                random[i] = doctorArray.get(i).getMedicalLicence();
            }
            Random rand = new Random();
            //GETTING RANDOM INDEX NUMBER USING random
            int randomIndex = rand.nextInt(random.length);
            consulted_DocId = random[randomIndex];
            randomnum ++;
            //CALL THE CHECK_EQUALITY METHOD AGAIN
            checks_equality();
        }
    }
    //CREATED METHOD TO CREATE JComboBOX AND ADD TO THE FRAME
    public void set_combobox(JComboBox combo,int x, int y , int width , int height){
        combo.setBounds(x,y,width,height);
        combo.setBackground(new Color(0xFFFFFF));
        this.add(combo);
    }
    //CREATED METHOD TO CREATE JLabel AND ADD TO THE FRAME
    public void set_lable(JLabel lable ,int x, int y , int width , int height,int font){
        lable.setBounds(x,y,width,height);
        lable.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,font));
        this.add(lable);
    }
    //CREATED METHOD TO CREATE TextFiled AND ADD TO THE FRAME

    public void set_textfild(JTextField textfild,int x, int y , int width , int height){//150,20
        textfild.setBounds(x,y,width,height);
        textfild.setFont(new Font("console",Font.ITALIC,15));
        this.add(textfild);
    }
    //CREATED METHOD TO CREATE JButton AND ADD TO THE FRAME
    public void button_set(JButton but,String name, int x, int y , int width , int height) {
        but.setBounds(x,y,width,height);
        but.setText(name);
        but.setFocusable(false);
        but.addActionListener(this);
        this.add(but);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()== submit) {
            try {
                String name_validate = "[a-zA-Z]+";
                String numberValidate ="\\A[0-9]{10}\\z";
                Pattern num = Pattern.compile(numberValidate);
                Pattern word = Pattern.compile(name_validate);
                name = get_name.getText().trim();
                if(word.matcher(name).matches()) {
                    surname = get_surname.getText().trim();
                    if(word.matcher(surname).matches()){
                        try {
                            dateOfBirth = LocalDate.parse(get_birthday.getText().trim());
                            if (dateOfBirth.isBefore(LocalDate.now().minusYears(2)) && dateOfBirth.isAfter(LocalDate.now().minusYears(100))) {
                                mobile_No = get_mobile.getText().trim();
                                // validation for mobilenumber
                                if (num.matcher(mobile_No).matches()) {
                                        try {
                                            patient_Id = Integer.parseInt(get_patientId.getText());
                                            consulted_DocId = (String) get_docId.getSelectedItem();
                                            String sttimeHou = (String) get_startTime1.getSelectedItem();
                                            String sttimeMin = (String) get_startTime2.getSelectedItem();
                                            String stasettime = sttimeHou + ":" + sttimeMin + ":00";
                                            try {
                                                consul_StartTime = LocalTime.parse(stasettime);
                                                consult_Endtime_Hours = (String) get_duration.getSelectedItem();
                                                consul_EndTime = consul_StartTime.plusHours(Long.parseLong(consult_Endtime_Hours));
                                                try {
                                                    consul_Date = LocalDate.parse(get_consultDate.getText().trim());
                                                    if (consul_Date.isAfter(LocalDate.now()) && consul_Date.isBefore(LocalDate.now().plusYears(3))) {
                                                        additional_Note = get_additional_note.getText();

                                                        for (Consultation consultation : consult) {
                                                            if (Objects.equals(consultation.getPatientId(), patient_Id)) {
                                                                again++;
                                                            }
                                                        }
                                                        try {

                                                            ///// ENCRYPTION PART /////////////////////


                                                            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
                                                            SecretKey myDesKey = keygenerator.generateKey();

                                                            // CREATED AN OBJECT OF CIPHER
                                                            Cipher desCipher;
                                                            desCipher = Cipher.getInstance("DES");


                                                            // CREATED BYTE ARRAY TO STORE THE STRING
                                                            byte[] text = additional_Note.getBytes(StandardCharsets.UTF_8);

                                                            // Encrypting text
                                                            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
                                                            byte[] textEncrypted = desCipher.doFinal(text);

                                                            //CONVERTING BYTE ARRAY TO STRING
                                                            additional_Note = Base64.getEncoder().encodeToString(textEncrypted);


                                                            //// CONVERTING SECURITY key TO STRING

                                                            security_Key = Base64.getEncoder().encodeToString(myDesKey.getEncoded());

                                                            try {
                                                                //GETTING THE IMAGE PATH
                                                                CipherInputStream cipt = new CipherInputStream(new FileInputStream(filename), desCipher);
                                                                //CREATING NEW ENCRYPTED IMAGE WITH USER NAME , PATIENT ID AND CONSULTATION NO
                                                                FileOutputStream fileip = new FileOutputStream(patient_Id + name + again + "-encrypt.jpg");

                                                                int i;
                                                                //USED WHILE LOOP TO RUN AGAIN AND AGAIN UNTIL FINISH LINES IN THE FILE
                                                                while ((i = cipt.read()) != -1) {
                                                                    fileip.write(i);

                                                                }
                                                                fileip.close();
                                                            } catch (Exception ignored) {}
                                                            //CALL THE CHECKS_EQUALITY METHOD
                                                            checks_equality();
                                                            // DISPOSING THIS FRAME USING "this.dispose()"
                                                            this.dispose();
                                                            //OPEN THE MESSAGE_VIEWER FRAME
                                                            new Message_Viewer(true, 0, 0);

                                                        } catch (Exception ignored) {
                                                            //CALL THE CHECKS_EQUALITY METHOD
                                                            checks_equality();
                                                            // DISPOSING THIS FRAME USING "this.dispose()"
                                                            this.dispose();
                                                            //OPEN THE MESSAGE_VIEWER FRAME
                                                            new Message_Viewer(true, 0, 0);
                                                        }
                                                    //  CREATED POPUP-BOX USING JOPtionPane TO SHOW VALIDATION MESSAGES
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "Check You Entered Consultation Date", "Error", JOptionPane.ERROR_MESSAGE);
                                                    }

                                                } catch (Exception ignored) {
                                                    JOptionPane.showMessageDialog(null, "Check Your Entered Date!!", "Error", JOptionPane.ERROR_MESSAGE);
                                                }
                                            } catch (Exception ignored) {
                                                JOptionPane.showMessageDialog(null, "Check You Entered Time and Duration!!", "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                        } catch (Exception ignored) {
                                            JOptionPane.showMessageDialog(null, "Check Your Patient ID!!", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Check Your Phone Number!!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Check Your Birthday!!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception ignored) {
                            JOptionPane.showMessageDialog(null, "Check Your Birthday!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Check Your First Name Or Surname!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Check Your First Name !!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch (Exception ignored){
                JOptionPane.showMessageDialog(null,"SOMETHING WRONG!!","Error", JOptionPane.ERROR_MESSAGE);
            }



        }else if (e.getSource()== pic){
            try {
                //GETTING FILE PATH USING JFileChooser AND ADDING  TO THE LABEL
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                filename = f.getAbsolutePath();
                add_imagePath.setText(filename);
                ImageIcon MyImage = new ImageIcon(filename);
                Image img = MyImage.getImage();
                Image newImg = img.getScaledInstance(add_image.getWidth(), add_image.getHeight(), Image.SCALE_SMOOTH);
                add_image.setIcon(new ImageIcon(newImg));


            }catch(Exception ignored){}
        }else{
            this.dispose();
            new Gui_main();
        }
    }

    @Override
    public void button() {
        //CREATING BUTTON OBJECTS
        JButton back = new JButton();
        back.setFont(new Font("SansSerif", Font.BOLD, 12));
        button_set(back,"Back",30,20,65,30);

        pic = new JButton();
        button_set(pic,"Pic",700, 660, 60, 20);

        submit = new JButton();
        button_set(submit,"Submit",550,920,80,30);

        JButton cancel = new JButton();
        button_set(cancel,"Cancel",660,920,80,30);

    }
}
