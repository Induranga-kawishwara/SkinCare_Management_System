package GUI;

import Console.Consultation;
import Console.Doctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static Console.WestminsterSkinConsultationManager.consult;

public class Gui_main extends GUI_table implements ActionListener {
    private JButton button1,button2;

    public Gui_main(){
        LoadFile();
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
        label.setOpaque(true);

        JLabel label1 = new JLabel();
        label1.setBounds(0,0,800,500);
        label1.setBackground(Color.white);
        label1.setOpaque(true);


        System.out.println("GUI file eka wada hutto");
        button();

        this.add(label);
        this.add(label1);

        window("Westminster Skin Consultation Manager",800,500);
    }
    public void LoadFile() {
        ArrayList <String> tempArray = new ArrayList<>();
        try {
            String temp;
            BufferedReader readFile =new BufferedReader(new FileReader("patient.txt"));

            while (((temp= readFile.readLine()) != null)){
                if(temp.equals("")){
                    continue;
                }else {
                    tempArray.add(temp);
                }
            }
            while ( 0 < (tempArray.size() / 11)) {

                consult.add(new Consultation(tempArray.get(0), tempArray.get(1), LocalDate.parse(tempArray.get(2)), tempArray.get(3), Integer.parseInt(tempArray.get(4)), tempArray.get(5), LocalTime.parse(tempArray.get(6)), LocalTime.parse(tempArray.get(7)), LocalDate.parse(tempArray.get(8)), tempArray.get(9), Double.parseDouble(tempArray.get(10))));
                tempArray.subList(0, 11).clear();
                //consult.add(new Consultation(name, surname, dateOfBirth, phoneno,patId,docconsulId, consulStart,consulEnd,cousulDate,note,cost));
//                temparray.add(new Doctor(TempLoadData.get(0),TempLoadData.get(1),LocalDate.parse(TempLoadData.get(2)),TempLoadData.get(3),TempLoadData.get(4),TempLoadData.get(5)));
//                temparray.subList(0, 6).clear();
            }
        }catch (Exception e){

        }
    }
    public void button_set(JButton but,String name , int y) {
        but.setBounds(420,y,350,40);
        but.setText(name);
        but.setFont(new Font(Font.SERIF,Font.BOLD,15));
        but.setForeground(new Color(0xFFFFFF));
        System.out.println("button set eka asse");
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

        }
    }
    @Override
    public void button() {
        button1 = new JButton();
        button_set(button1,"CONSULTATION",160);
        button2 = new JButton();
        button_set(button2,"All DOCTORS DETAILS",240);


    }
}
