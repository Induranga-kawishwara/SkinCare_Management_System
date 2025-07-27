package GUI;

import Console.Doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;


import static Console.AdminPanel.doctorArray;

public class Doctor_frame extends GUI_table implements ActionListener {

    private JButton back,reset,sort;

    Doctor_frame(boolean start){
        //GOT IMAGE PATH USING ImageIcon
        ImageIcon img = new ImageIcon("src/GUI/Doctor_frame.jpg");

        //ADDED A TOPIC USING JLabel
        JLabel topic = new JLabel();
        topic.setText("ALL DOCTORS DETAILS");
        topic.setBounds(300,20,550,20);
        topic.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));

        //ADDED COLUMNS IN TO THE TABLE USING JLabel
        JLabel colum = new JLabel();
        colum.setText("|    Name     |   SurName   |   Birthday    |   Phone-NO   |   Licence   |Specialisation|");
        colum.setBounds(33,-70,850,300);
        colum.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,15));
        colum.setForeground(new Color(253, 2, 2, 255));

        //CREATED A TABLE
        DefaultTableModel tableModel = new DefaultTableModel(0,6);
        JTable table = new JTable(tableModel);
        table.setBounds(35,100,800,300);
       if (start){
           for (Console.Doctor doctor : doctorArray) {
               String[] details = {doctor.getName(), doctor.getSurname(), String.valueOf(doctor.getDateOfBirth()), doctor.getMobileNo(), doctor.getMedicalLicence(), doctor.getSpecialisation()};
               tableModel.addRow(details);

           }
       }else{
           //CREATED A ARRAYLIST TO SAVE DOCTOR'S SURNAMES
           ArrayList<String> surname = new ArrayList<>();
           //CREATED A ARRAYLIST TO SAVE SORTED SURNAME LIST
           ArrayList <String> sort = new ArrayList<>();
           //GET SURNAME FROM doctorArray LIST
           for (Doctor value : doctorArray) {
               surname.add(value.getSurname());
           }
           //REMOVING DUPLICATED SURNAMES USING CONTAINS
           for (String value : surname) {
               if (!sort.contains(value)) {
                   sort.add(value);
               }
           }
           Collections.sort(sort);
           for (String s : sort) {
               for (Doctor doctor : doctorArray) {
                   if (s.equals(doctor.getSurname())) {
                       //GETTING DATA AND ASSAYING INTO THE ARRAY
                       String[] details = {doctor.getName(), doctor.getSurname(), String.valueOf(doctor.getDateOfBirth()),doctor.getMobileNo(), doctor.getMedicalLicence(), doctor.getSpecialisation()};
                       //ADDING THE details ARRAY INTO THE TABLE AS ROW
                       tableModel.addRow(details);
                   }
               }
           }
       }
       //ADDED BACKGROUND USING JLabel
        JLabel background = new JLabel();
        background.setIcon(img);
        background.setBounds(0,0,900,500);


        this.add(topic);
        this.add(colum);
        this.add(table);



        button();
        this.add(background);
        //CALL THE MAKE FRAME TO MAKE FRAME FOR THIS
        makeFrame("All Doctors Exist",900,500);

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
        if (e.getSource()==back) {
            this.dispose();
            new Gui_main();//ANONIMUS class call
        } else if (e.getSource()==reset) {
            this.dispose();
            new Doctor_frame(true);

        } else if (e.getSource()==sort){
            this.dispose();
            new Doctor_frame(false);
        }
    }

    @Override
    public void button() {
        //CREATING BUTTON OBJECTS
        back = new JButton();
        button_set(back,"Back",30,20,65,30);

        reset = new JButton();
        button_set(reset,"reset",40,410,65,30);

        sort = new JButton();
        button_set(sort,"sort",750,410,80,30);

    }
}
