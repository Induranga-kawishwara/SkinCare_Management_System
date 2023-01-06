package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Gui_main extends GUI_table implements ActionListener {
    private JButton consultation;
    private JButton doctor_details;

    public Gui_main(){
//        LoadFile();
        ImageIcon img = new ImageIcon("src/GUI/home.jpg");

        JLabel label = new JLabel();                                                      //Adding the Topic.
        label.setText("SKIN CONSULTATION MANAGER");
        label.setIcon(img);
        label.setHorizontalTextPosition(JLabel.CENTER);
        //label.setForeground(new Color(0x00FF));
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setBounds(10,2,400,450);
        label.setFont(new Font("SansSerif", Font.BOLD, 15));
        label.setBackground(Color.white);
       //label.setOpaque(true);

        JLabel label1 = new JLabel();
        label1.setBounds(0,0,800,500);
        label1.setBackground(Color.white);
        label1.setOpaque(true);


        button();

        this.add(label);
        this.add(label1);

        makeFrame("Westminster Skin Consultation Manager",800,500);
    }

    public void button_set(JButton but,String name , int y) {
        but.setBounds(420,y,350,40);
        but.setText(name);
        but.setFont(new Font(Font.SERIF,Font.BOLD,15));
        but.setForeground(new Color(0xFFFFFF));
        but.setFocusable(false);
        but.setBackground(Color.blue);
        but.addActionListener(this);
        this.add(but);
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== consultation) {
            this.dispose();
            new Consultation_frame();
        } else if (e.getSource()== doctor_details) {
            this.dispose();
            new Doctor_frame(true);

        }else{
            this.dispose();
            new ViewHistory_frame(true,0);
        }
    }
    @Override
    public void button() {
        consultation = new JButton();
        button_set(consultation,"CONSULTATION",120);
        doctor_details = new JButton();
        button_set(doctor_details,"All DOCTORS DETAILS",200);
        JButton history = new JButton();
        button_set(history,"VIEW HISTORY",280);


    }
}
