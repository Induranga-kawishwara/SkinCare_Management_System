package GUI;

import Console.Consultation;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Base64;

import static Console.WestminsterSkinConsultationManager.consult;

public class Frame1pop extends GUI_table implements ActionListener {
    private  JButton ok;
    private  boolean pop;
    private  String name;


    Frame1pop(boolean pop,int ID , int how){
        this.pop = pop;

        if(pop) {
            name="You Entered Details";

            JLabel topic = new JLabel();
            topic.setText("YOUR CONSULTATION");
            topic.setBounds(150, 20, 550, 20);
            topic.setForeground(new Color(0x5659D0));
            topic.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));


            int loc = (consult.size() - 1);

            JLabel details = new JLabel();
            details.setText("<html><br>" +
                    "01.Name                : " + consult.get(loc).getName() + "<br>" +
                    "02.Surname             : " + consult.get(loc).getSurname() + "<br>" +
                    "03.Date-of-Birth       : " + consult.get(loc).getDateOfBirth() + "<br>" +
                    "04.Mobile-No           : " + consult.get(loc).getMobileNo() + "<br>" +
                    "05.Patient-ID          : " + consult.get(loc).getPatientId() + "<br>" +
                    "06.Consultation-Date   : " + consult.get(loc).getConDate() + "<br>" +
                    "07.Start-Time          : " + consult.get(loc).getConsulStart() + "<br>" +
                    "08.End-Time            : " + consult.get(loc).getConsulEnd() + "<br>" +
                    "09.Consulted-Doctor    : " + consult.get(loc).getDocconsulId() + "<br>" +
                    "10.Cost-for-Consulation: " + consult.get(loc).getCost() + "<br></html>");

            details.setBounds(39, 10, 400, 400);
            details.setFont(new Font(Font.SERIF, Font.BOLD, 15));


            this.add(topic);
            this.add(details);
        }else{
            name="Additional Details";

            JLabel topic = new JLabel();
            topic.setText("ADDITIONAL NOTE : ");
            topic.setBounds(150, 20, 550, 20);
            topic.setForeground(new Color(0x5659D0));
            topic.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));

            JTextArea getnote = new JTextArea();
            getnote.setBounds(30,50,420,150);
            getnote.setFont(new Font("console",Font.ITALIC,15));
            getnote.setEditable(false);
            getnote.setLineWrap(true);

            JLabel topic1 = new JLabel();
            topic1.setText("PICTURE : ");
            topic1.setBounds(50, 290, 550, 20);
            topic1.setForeground(new Color(0x5659D0));
            topic1.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));

            JLabel addpho = new JLabel();
            addpho.setBounds(230, 210, 220, 200);
            addpho.setBackground(Color.white);
            addpho.setOpaque(true);

            for(Consultation con : consult){
                if(con.getWhichco()==how && con.getPatientId()==ID){
                    System.out.println(how);

                    try {

                        // Creating object of Cipher
                        Cipher desCipher;
                        desCipher = Cipher.getInstance("DES");

                        // covert  string to security
                        byte[] encodedKey = Base64.getDecoder().decode(con.getNotenkey());
                        SecretKey notekey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "DES");
                        System.out.println(notekey);

                        /// Decrypting text

                        byte[] output =Base64.getDecoder().decode(con.getConNote());
                        desCipher.init(Cipher.DECRYPT_MODE, notekey);
                        byte[] textDecrypted1 = desCipher.doFinal(output);

                        // Converting decrypted byte array to string
                        String u = new String(textDecrypted1);
                        getnote.setText(u);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        CipherInputStream ciptt=new CipherInputStream(new FileInputStream(con.getPatientId()+con.getName()+con.getWhichco()+"-encrypt.jpg"), desCipher);

                        FileOutputStream fileop=new FileOutputStream(con.getPatientId()+con.getName()+con.getWhichco()+"-decrypt.jpg");

                        int j;
                        while((j=ciptt.read())!=-1)
                        {
                            fileop.write(j);
                        }
                        fileop.close();

                        ImageIcon MyImage = new ImageIcon(con.getPatientId()+con.getName()+con.getWhichco()+"-decrypt.jpg");
                        Image img = MyImage.getImage();
                        Image newImg = img.getScaledInstance(addpho.getWidth(), addpho.getHeight(), Image.SCALE_SMOOTH);
                        addpho.setIcon(new ImageIcon(newImg));

                    }catch (Exception Ignore){
                        topic1.setOpaque(false);
                        addpho.setOpaque(false);

                    }

                }
            }
            this.add(getnote);
            this.add(topic);
            this.add(topic1);
            this.add(addpho);

        }

        button();
        window(name,500,530);
    }


    public void button_set(JButton but,String name) {
        but.setBounds(210,420,70,30);
        but.setText(name);
        System.out.println("button set eka asse");
        but.setFocusable(false);
        but.addActionListener(this);
        this.add(but);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()== ok) {
            if(pop) {
                this.dispose();
                new Gui_main();
            }else{
                this.dispose();
            }
        }

    }
    @Override
    public void button() {

        ok = new JButton();
        button_set(ok,"OK");

    }

}
