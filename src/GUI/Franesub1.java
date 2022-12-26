package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Console.WestminsterSkinConsultationManager.doctorArray;

public class Franesub1 extends GUI_table implements ActionListener {
    private JButton back;
    private  JButton Cancel;
    private  JButton submit;

    JTextField getname;
    Franesub1(){

        JLabel topic = new JLabel();
        topic.setText("ALL DOCTORS DETAILS");
        topic.setBounds(290,20,550,20);
        topic.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));


        JLabel colum = new JLabel();
        colum.setText("|     Name     |    SurName    |   Phone-No   |   Licence    | Specialisation |");
        colum.setBounds(39, -70, 750, 300);
        colum.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
        colum.setForeground(new Color(246, 10, 43, 255));

        String[] colom = {"Name", "SurName", "Phone-No", "Licence", "Specialisation"};
        DefaultTableModel tableModel = new DefaultTableModel(colom, 0);
        JTable table = new JTable(tableModel);
        table.setBounds(40, 100, 700, 300);

        for (Console.Doctor doctor : doctorArray) {
            String[] details = {doctor.getName(), doctor.getSurname(), doctor.getMobileNo(), doctor.getMedicalLicence(), doctor.getSpecialisation()};
            tableModel.addRow(details);
        }

        JLabel topic1 = new JLabel();
        topic1.setText("DOCTOR CONSULTATION");
        topic1.setBounds(280, 420, 550, 20);
        topic1.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));

        JLabel name = new JLabel();
        name.setText("NAME             :                      SURNAME          :");
        name.setBounds(30, 480, 550, 20);
        name.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));

        getname = new JTextField();



        JLabel birthday = new JLabel();
        birthday.setText("BIRTHDAY         :                      PHONE NUMBER     :");
        birthday.setBounds(30, 530, 550, 20);
        birthday.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));

        JLabel id = new JLabel();
        id.setText("PATIENT ID       :                      CONSULTATION TIME:");
        id.setBounds(30, 580, 550, 20);
        id.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));

        JLabel date = new JLabel();
        date.setText("CONSULTATION DATE (YYY-MM-DD):");
        date.setBounds(30, 630, 550, 20);
        date.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));



        this.add(topic);

        this.add(colum);
        this.add(table);
        this.add(topic1);
        this.add(name);
        this.add(birthday);
        this.add(id);
        this.add(date);



        System.out.println("GUI file eka wada hutto");
        button();
        set_textfild(getname,20,500);
        window("GUI eke  2nd page eka pago",800,1000);
    }

    public void set_textfild(JTextField textfild,int x, int y ){
        textfild.setBounds(x,y,100,100);
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
        if (e.getSource()== Cancel) {
            this.dispose();
            new Gui_main();
        } else if (e.getSource()== submit) {
            System.out.println("fuuuuuuuuu");
            this.dispose();
            new Frame1();

        }else{
            this.dispose();
            new Gui_main();
        }
//        else if (e.getSource()== Cancel){
//            System.out.println("fuuuuuuuuu");
//            this.dispose();
//            new Frame1();
//        }

    }
    @Override
    public void button() {
        back = new JButton();
        back.setFont(new Font("SansSerif", Font.BOLD, 12));
        button_set(back,"Back",30,20,65,30);

        submit = new JButton();
        button_set(submit,"Submit",550,900,80,30);



        Cancel = new JButton();
        button_set(Cancel,"Cancel",660,900,80,30);

    }
}
