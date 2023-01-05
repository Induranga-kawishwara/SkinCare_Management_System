package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Gui_main extends GUI_table implements ActionListener {
    private JButton button1;
    private JButton button2;

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

        window("Westminster Skin Consultation Manager",800,500);
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
        if (e.getSource()==button1) {
            this.dispose();
            new Frame1();
        } else if (e.getSource()==button2) {
            this.dispose();
            new Frame2(true);

        }else{
            this.dispose();
            new frame3(true,0);
        }
    }
    @Override
    public void button() {
        button1 = new JButton();
        button_set(button1,"CONSULTATION",120);
        button2 = new JButton();
        button_set(button2,"All DOCTORS DETAILS",200);
        JButton button3 = new JButton();
        button_set(button3,"VIEW HISTORY",280);


    }
}
