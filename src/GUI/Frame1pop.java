package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Console.WestminsterSkinConsultationManager.consult;

public class Frame1pop extends GUI_table implements ActionListener {
    private  JButton ok;


    Frame1pop(){

        JLabel topic = new JLabel();
        topic.setText("YOUR CONSULTATION");
        topic.setBounds(150,20,550,20);
        topic.setForeground(new Color(0x5659D0));
        topic.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));


        int loc = (consult.size()-1);

        JLabel details = new JLabel();
        details.setText("<html><br><br>"+
                          "01.Name                : "+consult.get(loc).getName()+"<br>"+
                          "02.Surname             : "+consult.get(loc).getSurname()+"<br>"+
                          "03.Date-of-Birth       : "+consult.get(loc).getDateOfBirth()+"<br>"+
                          "04.Mobile-No           : "+consult.get(loc).getMobileNo()+"<br>"+
                          "05.Patient-ID          : "+consult.get(loc).getPatientId()+"<br>"+
                          "06.Consultation-Date   : "+consult.get(loc).getConDate()+"<br>"+
                          "07.Start-Time          : "+consult.get(loc).getConsulStart()+"<br>"+
                          "08.End-Time            : "+consult.get(loc).getConsulEnd()+"<br>"+
                          "09.Consulted-Doctor    : "+consult.get(loc).getDocconsulId()+"<br>"+
                          "10.Cost-for-Consulation: "+consult.get(loc).getCost()+"<br></html>");

        details.setBounds(39, 10, 400, 400);
        details.setFont(new Font(Font.SERIF, Font.BOLD, 15));


        this.add(topic);
        this.add(details);

        System.out.println("GUI file eka wada hutto");
        button();
        window("You Entered Details",500,500);
    }
    public void button_set(JButton but,String name) {
        but.setBounds(210,400,70,30);
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
        }

    }
    @Override
    public void button() {

        ok = new JButton();
        button_set(ok,"OK");

    }
}
