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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Base64;


import static Console.WestminsterSkinConsultationManager.doctorArray;

import  static Console.WestminsterSkinConsultationManager.consult;

public class Frame1 extends GUI_table implements ActionListener {


    private String name,surname,phoneno,docconsulId,note,sttimeHou,sttimeMin,stasettime,entimeHou,notenkey;

    private SecretKey notekey;

    private String filename = null;

    private int cost,patId;
    private int again = 1;

    private LocalDate dateOfBirth,cousulDate;

    private LocalTime consulStart,consulEnd,ensettime;
    private JButton back,Cancel,submit,pic;
    private JTextField getname,getbirthday,getid,getdate,getsurname,getphone;
    private JComboBox getdoc,getstarttime1,getstarttime2, getdura;
    private JTextArea getnote;
    private JLabel topic,colum,topic1,jname,birthday,id,time,date,jnote,addpho,addphopath;
    Frame1(){

        ImageIcon img = new ImageIcon("src/GUI/frame1.jpg");


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

        for (int i =0; doctorArray.size()>i;i++) {
            String[] details = {doctorArray.get(i).getName(), doctorArray.get(i).getSurname(), doctorArray.get(i).getMobileNo(), doctorArray.get(i).getMedicalLicence(), doctorArray.get(i).getSpecialisation()};
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


        time = new JLabel();
        time.setText("CONSULTATION START TIME:                 CONSULTATION DURATION:");
        set_lable(time,30, 570, 700, 20,15);


        String[] hours = { "HH","08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20","21","22" };
        String[] min = { "MM","00", "15", "30", "45" };
        String[] dura = { "HH","01", "02", "03", "04", "05"};

        //J combo box
        getstarttime1 =new JComboBox(hours);
        set_combobox(getstarttime1,260,575,50,20);


        getstarttime2 =new JComboBox(min);
        set_combobox(getstarttime2,340,575,50,20);

        getdura =new JComboBox(dura);
        set_combobox(getdura,600,575,150,20);


        date = new JLabel();
        date.setText("CONSULTATION DATE(YYY-MM-DD):");
        set_lable(date,30, 620, 550, 20,15);


        getdate = new JTextField();
        set_textfild(getdate,300,625,150,20);

        jnote = new JLabel();
        jnote.setText("ADDITIONAL NOTE :");
        set_lable(jnote,30, 670, 550, 20,15);


        getnote = new JTextArea();
        getnote.setText(".");
        getnote.setBounds(30,700,500,200);
        getnote.setFont(new Font("console",Font.ITALIC,15));
        getnote.setLineWrap(true);

        addpho = new JLabel();
        addpho.setBounds(540, 700, 220, 200);
        addpho.setBackground(Color.white);
        addpho.setOpaque(true);

        addphopath = new JLabel();
        addphopath.setBounds(540, 660, 150, 20);
        addphopath.setBackground(Color.white);
        addphopath.setOpaque(true);

        JLabel rat = new JLabel();
        rat.setIcon(img);
        rat.setBounds(0,0,800,1000);
//        rat.setpr(new Dimension(900,500));
        rat.setOpaque(true);


        this.add(table);
        this.add(getnote);
        this.add(addpho);
        this.add(addphopath);


        button();
        this.add(rat);
        window("Consultation",800,1000);
    }
    public void check_equal(){
        int timedura = Integer.parseInt(entimeHou);
        boolean not_equal = true;
        for (Consultation consultation : consult) {
            if (docconsulId.equals(consultation.getDocconsulId())) {
                if (consultation.getConDate().isEqual(cousulDate)) {
                    if(consultation.getConsulStart().isBefore(consulStart) && consultation.getConsulEnd().isAfter(consulStart) ||
                            consultation.getConsulStart().isBefore(consulEnd) && consultation.getConsulEnd().isAfter(consulEnd) ||
                            consultation.getConsulStart().isBefore(consulStart) && consultation.getConsulEnd().isAfter(consulEnd) ||
                            consultation.getConsulStart().isAfter(consulStart) && consultation.getConsulEnd().isBefore(consulEnd) )
                    {
                        not_equal = false;
                        break;
                    }
                }
            }
        }

        if(again==1){
            cost=(timedura*15);


        }else{
            cost = 25*timedura;
        }

        if(not_equal){

            consult.add(new Consultation(again,name, surname, dateOfBirth, phoneno,patId,docconsulId, consulStart,consulEnd,cousulDate,note,cost,notenkey));

        }else{
            int docsiz=doctorArray.size();
            String [] random = new String[docsiz];
            for(int i =0;docsiz > i ;i++){
                random[i] = doctorArray.get(i).getMedicalLicence();
            }
            Random rand = new Random();
            int randomIndex = rand.nextInt(random.length);
            docconsulId = random[randomIndex];
            again = 0 ;
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
        but.setFocusable(false);
        but.addActionListener(this);
        this.add(but);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()== submit) {
            try {
                String regex = "^[A-Za-z]\\w{2,29}$";
                Pattern p = Pattern.compile(regex);
                name = getname.getText().trim();
                surname = getsurname.getText().trim();
                Matcher f = p.matcher(name);
                Matcher s = p.matcher(surname);
                if (f.matches() && s.matches()) {
                    try {
                        dateOfBirth = LocalDate.parse(getbirthday.getText().trim());
                        phoneno = getphone.getText().trim();
                        // validation for mobilenumber
                        if (10 == phoneno.length()) {
                            try {
                                Integer.parseInt(phoneno);
                                try {
                                    patId = Integer.parseInt(getid.getText());
                                    docconsulId = (String) getdoc.getSelectedItem();
                                    sttimeHou = (String) getstarttime1.getSelectedItem();
                                    sttimeMin = (String) getstarttime2.getSelectedItem();
                                    stasettime = sttimeHou + ":" + sttimeMin + ":00";
                                    try {
                                        consulStart = LocalTime.parse(stasettime);
                                        entimeHou = (String) getdura.getSelectedItem();
                                        consulEnd = consulStart.plusHours(Long.parseLong(entimeHou));
                                        try {
                                            cousulDate = LocalDate.parse(getdate.getText().trim());
                                            if (cousulDate.isAfter(LocalDate.now()) && cousulDate.isBefore(LocalDate.now().plusYears(3))) {
                                                note = getnote.getText();

                                                for(Consultation consultation : consult){
                                                    if(Objects.equals(consultation.getPatientId(), patId)){
                                                        again++;
                                                    }
                                                }
                                                try {

                                                    ///// encrypt/////////////////////


                                                    KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
                                                    SecretKey myDesKey = keygenerator.generateKey();

                                                    // Creating object of Cipher
                                                    Cipher desCipher;
                                                    desCipher = Cipher.getInstance("DES");


                                                    // Creating byte array to store string
                                                    byte[] text = note.getBytes(StandardCharsets.UTF_8);

                                                    // Encrypting text
                                                    desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
                                                    byte[] textEncrypted = desCipher.doFinal(text);

                                                    // Converting encrypted byte array to string
                                                    note =  Base64.getEncoder().encodeToString(textEncrypted);


                                                    //// covert security key

                                                    notenkey = Base64.getEncoder().encodeToString(myDesKey.getEncoded());




                                                    //////////////////////////////////////////////////////////////////////
                                                    try
                                                    {

//                                                        desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);

                                                        CipherInputStream cipt=new CipherInputStream(new FileInputStream(filename), desCipher);

                                                        FileOutputStream fileip=new FileOutputStream(patId+name+again+"-encrypt.jpg");

                                                        int i;
                                                        while((i=cipt.read())!=-1)
                                                        {
                                                            fileip.write(i);

                                                        }
                                                        fileip.close();
                                                    }
                                                    catch(Exception ignored) {}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                    check_equal();
                                                    this.dispose();
                                                    new Frame1pop(true,0,0);

                                                } catch (Exception ignored) {
                                                    check_equal();
                                                    this.dispose();
                                                    new Frame1pop(true,0,0);
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Check You Entered Consultation Date", "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                            ///validate cousulDAte

                                        } catch (Exception ignored) {
                                            JOptionPane.showMessageDialog(null, "Check You Entered Date!!", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } catch (Exception ignored) {
                                        JOptionPane.showMessageDialog(null, "Check You Entered Time and Duration!!", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (Exception ignored) {
                                    JOptionPane.showMessageDialog(null, "Check Your Patient ID!!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }catch (Exception ignored){
                                JOptionPane.showMessageDialog(null, "Check Your Phone Number!!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Check Your Phone Number!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }catch (Exception ignored){
                        JOptionPane.showMessageDialog(null, "Check Your Birthday!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else{
                        JOptionPane.showMessageDialog(null, "Check Your First Name And Surname!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
            }catch (Exception ignored){
                JOptionPane.showMessageDialog(null,"SOMETHING WRONG!!","Error", JOptionPane.ERROR_MESSAGE);
            }



        }else if (e.getSource()== pic){
            try {

                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                filename = f.getAbsolutePath();
                addphopath.setText(filename);
                ImageIcon MyImage = new ImageIcon(filename);
                Image img = MyImage.getImage();
                Image newImg = img.getScaledInstance(addpho.getWidth(), addpho.getHeight(), Image.SCALE_SMOOTH);
                addpho.setIcon(new ImageIcon(newImg));


            }catch(Exception ignored){}
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

        pic = new JButton();
        button_set(pic,"Pic",700, 660, 60, 20);

        submit = new JButton();
        button_set(submit,"Submit",550,920,80,30);

        Cancel = new JButton();
        button_set(Cancel,"Cancel",660,920,80,30);

    }
}
