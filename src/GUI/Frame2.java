package GUI;

import Console.Doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


import static Console.WestminsterSkinConsultationManager.doctorArray;

public class Frame2 extends GUI_table implements ActionListener {

    private JButton back,reset,sort;

    Frame2( boolean cat){

        ImageIcon img = new ImageIcon("src/GUI/frame2.jpg");
        JLabel topic = new JLabel();
        topic.setText("ALL DOCTORS DETAILS");
        topic.setBounds(300,20,550,20);
        topic.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));
        //topic.setForeground(new Color(17, 17, 17));

        JLabel colum = new JLabel();
        colum.setText("|    Name     |   SurName   |   Birthday    |   Phone-NO   |   Licence   |Specialisation|");
        colum.setBounds(33,-70,850,300);
        colum.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,15));
        colum.setForeground(new Color(253, 2, 2, 255));


        DefaultTableModel tableModel = new DefaultTableModel(0,6);
        JTable table = new JTable(tableModel);
        table.setBounds(35,100,800,300);
       if (cat){
           for (Console.Doctor doctor : doctorArray) {
               String[] details = {doctor.getName(), doctor.getSurname(), String.valueOf(doctor.getDateOfBirth()), doctor.getMobileNo(), doctor.getMedicalLicence(), doctor.getSpecialisation()};
               tableModel.addRow(details);

           }
       }else{
           int i = doctorArray.size();
           String [] sort = new String[i];
           for (int j = 0; j < i;j++) {
               sort[j]=doctorArray.get(j).getSurname();
           }
//           for (int k = 0 ; k < sort.length ;k++) {
//               for(int l =k+1 ;l<sort.length; l++ ){
//                  if(sort[k].compareTo(sort[l])>0){
//                      String temp = sort[k];
//                      sort[k] = sort[l];
//                      sort[l]=temp;
//                  }
//               }
//           }
           Arrays.sort(sort);
           for (String s : sort) {
               for (Doctor doctor : doctorArray) {
                   if (s.equals(doctor.getSurname())) {
                       String[] details = {doctor.getName(), doctor.getSurname(), String.valueOf(doctor.getDateOfBirth()),doctor.getMobileNo(), doctor.getMedicalLicence(), doctor.getSpecialisation()};
                       tableModel.addRow(details);
                   }
               }
           }
       }
        JLabel rat = new JLabel();
        rat.setIcon(img);
        rat.setBounds(0,0,900,500);
//        rat.setpr(new Dimension(900,500));
        //rat.setOpaque(true);

        this.add(topic);
        this.add(colum);
        this.add(table);



        button();
        this.add(rat);
        window("All Doctors Exist",900,500);

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
            new Gui_main();//animus class call
        } else if (e.getSource()==reset) {
            this.dispose();
            new Frame2(true);

        } else if (e.getSource()==sort){
            this.dispose();
            new Frame2(false);
        }
    }
    @Override
    public void button() {
        back = new JButton();
        button_set(back,"Back",30,20,65,30);

        reset = new JButton();
        button_set(reset,"reset",40,410,65,30);

        sort = new JButton();
        button_set(sort,"sort",750,410,80,30);

    }
}
