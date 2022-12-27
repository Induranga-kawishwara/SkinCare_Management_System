package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Console.WestminsterSkinConsultationManager.consult;

public class Frame1pop extends GUI_table implements ActionListener {
    private JButton back;
    private  JButton Cancel;
    private  JButton ok;

    JTextField getname;
    Frame1pop(){

        JLabel topic = new JLabel();
        topic.setText("YOUR CONSULTATION");
        topic.setBounds(290,20,550,20);
        topic.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));


        int loc = (consult.size()-1);

        JLabel details = new JLabel();
        details.setText("<html>"+
                          "01.Name                : "+consult.get(loc).getName()+"<br>"+
                          "02.Surname             : "+consult.get(loc).getSurname()+"<br>"+
                          "03.Date-of-Birth       : "+consult.get(loc).getDateOfBirth()+"<br>"+
                          "04.Mobile-No           : "+consult.get(loc).getMobileNo()+"<br>"+
                          "05.Patient-ID          : "+consult.get(loc).getPatientId()+"<br>"+
                          "06.Consultation-Date   : "+consult.get(loc).getConDate()+"<br>"+
                          "07.Start-Time          : "+consult.get(loc).getConsulStart()+"<br>"+
                          "08.End-Time            : "+consult.get(loc).getConsulEnd()+"<br>"+
                          "09.Consulted-Doctor    : "+consult.get(loc).getDocconsulId()+"<br>"+
                          "10.Cost-for-Consulation: "+consult.get(loc).getCost()+"<br>"+
                          "11.Additional-Note     : "+"<br>"+consult.get(loc).getConNote()+"</html>");
       // details.setText(String.valueOf(consult.get((consult.size()-1))));
        details.setBounds(39, 10, 400, 300);
        details.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));


//        for (Console.Doctor doctor : doctorArray) {
//            String[] details = {doctor.getName(), doctor.getSurname(), doctor.getMobileNo(), doctor.getMedicalLicence(), doctor.getSpecialisation()};
//            tableModel.addRow(details);
//        }
//
//        JLabel topic1 = new JLabel();
//        topic1.setText("DOCTOR CONSULTATION");
//        topic1.setBounds(280, 420, 550, 20);
//        topic1.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
//
//        JLabel name = new JLabel();
//        name.setText("NAME             :                      SURNAME          :");
//        name.setBounds(30, 480, 550, 20);
//        name.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
//
//        getname = new JTextField();
//
//
//
//        JLabel birthday = new JLabel();
//        birthday.setText("BIRTHDAY         :                      PHONE NUMBER     :");
//        birthday.setBounds(30, 530, 550, 20);
//        birthday.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
//
//        JLabel id = new JLabel();
//        id.setText("PATIENT ID       :                      CONSULTATION TIME:");
//        id.setBounds(30, 580, 550, 20);
//        id.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
//
//        JLabel date = new JLabel();
//        date.setText("CONSULTATION DATE (YYY-MM-DD):");
//        date.setBounds(30, 630, 550, 20);
//        date.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));



        this.add(topic);

        this.add(details);
//        this.add(table);
//        this.add(topic1);
//        this.add(name);
//        this.add(birthday);
//        this.add(id);
//        this.add(date);



        System.out.println("GUI file eka wada hutto");
        button();
//        set_textfild(getname,20,500);
        window("GUI eke  2nd page eka pago",500,500);
    }

//    public void set_textfild(JTextField textfild,int x, int y ){
//        textfild.setBounds(x,y,100,100);
//        textfild.setFont(new Font("console",Font.ITALIC,15));
//        this.add(textfild);
//    }
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
        if (e.getSource()== ok) {
            this.dispose();
            new Gui_main();
//        } else if (e.getSource()== submit) {
//            System.out.println("fuuuuuuuuu");
//            this.dispose();
//            new Frame1();

//        }else{
//            this.dispose();
//            new Gui_main();
        }
//        else if (e.getSource()== Cancel){
//            System.out.println("fuuuuuuuuu");
//            this.dispose();
//            new Frame1();
//        }

    }
    @Override
    public void button() {
//        back = new JButton();
//        back.setFont(new Font("SansSerif", Font.BOLD, 12));
//        button_set(back,"Back",30,20,65,30);

        ok = new JButton();
        button_set(ok,"OK",290,300,80,30);

//
//
//        Cancel = new JButton();
//        button_set(Cancel,"Cancel",660,900,80,30);

    }
}
