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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;

import static Console.WestminsterSkinConsultationManager.consult;

public class Message_Viewer extends GUI_table implements ActionListener {
    private  JButton ok;
    private final boolean start;
    private String filename;


    Message_Viewer(boolean start, int ID , int how){
        this.start = start;
        ImageIcon background = new ImageIcon("src/GUI/Message_viewer.jpg");

        JLabel image = new JLabel();
        image.setIcon(background);
        image.setBounds(0,0,500,530);

        String name;
        if(start) {
            name ="You Entered Details";

            JLabel topic = new JLabel();
            topic.setText("YOUR CONSULTATION");
            topic.setBounds(150, 20, 550, 20);
            topic.setForeground(new Color(0x1B1B1C));
            topic.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));


            int loc = (consult.size() - 1);

            JLabel details = new JLabel();
            details.setText("<html><br>" +
                    "01.Name                : " + consult.get(loc).getName() + "<br>" +
                    "02.Surname             : " + consult.get(loc).getSurname() + "<br>" +
                    "03.Date-of-Birth       : " + consult.get(loc).getDateOfBirth() + "<br>" +
                    "04.Mobile-No           : " + consult.get(loc).getMobileNo() + "<br>" +
                    "05.Patient-ID          : " + consult.get(loc).getPatientId() + "<br>" +
                    "06.Consultation-Date   : " + consult.get(loc).getConsul_Date() + "<br>" +
                    "07.Start-Time          : " + consult.get(loc).getConsul_StartTime() + "<br>" +
                    "08.End-Time            : " + consult.get(loc).getConsul_EndTime() + "<br>" +
                    "09.Consulted-Doctor    : " + consult.get(loc).getConsulted_DocId() + "<br>" +
                    "10.Cost-for-Consulation: " + consult.get(loc).getCost() + "<br></html>");

            details.setBounds(39, 10, 400, 400);
            details.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 15));


            this.add(topic);
            this.add(details);
        }else{
            name ="Additional Details";

            JLabel topic = new JLabel();
            topic.setText("ADDITIONAL NOTE : ");
            topic.setBounds(150, 20, 550, 20);
            topic.setForeground(new Color(0x0A0A0A));
            topic.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));

            JTextArea set_AdditionalNote = new JTextArea();
            set_AdditionalNote.setBounds(30,50,420,150);
            set_AdditionalNote.setFont(new Font("console",Font.ITALIC,15));
            set_AdditionalNote.setEditable(false);
            set_AdditionalNote.setLineWrap(true);

            JLabel topic1 = new JLabel();
            topic1.setText("PICTURE : ");
            topic1.setBounds(50, 290, 550, 20);
            topic1.setForeground(new Color(0x131313));
            topic1.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));

            JLabel add_photo = new JLabel();
            add_photo.setBounds(230, 210, 220, 200);
            add_photo.setBackground(Color.white);
            add_photo.setOpaque(true);

            for(Consultation con : consult){
                if(con.getConsul_No()==how && con.getPatientId()==ID){

                    /////////////////////Decrypt /////////////////////////////////////////////

                    try {

                        // Creating object of Cipher
                        Cipher desCipher;
                        desCipher = Cipher.getInstance("DES");

                        // covert  string to security
                        byte[] encodedKey = Base64.getDecoder().decode(con.getSecurity_Key());
                        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "DES");

                        /// Decrypting text

                        byte[] output =Base64.getDecoder().decode(con.getAdditional_Note());
                        desCipher.init(Cipher.DECRYPT_MODE, key);
                        byte[] textDecrypted1 = desCipher.doFinal(output);

                        // Converting decrypted byte array to string
                        String u = new String(textDecrypted1);
                        set_AdditionalNote.setText(u);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        CipherInputStream encrypted_filePath=new CipherInputStream(new FileInputStream(con.getPatientId()+con.getName()+con.getConsul_No()+"-encrypt.jpg"), desCipher);

                        filename = con.getPatientId()+con.getName()+con.getConsul_No()+"-decrypt.jpg";

                        FileOutputStream decrypt_file=new FileOutputStream(filename);

                        int j;
                        while((j=encrypted_filePath.read())!=-1)
                        {
                            decrypt_file.write(j);
                        }
                        decrypt_file.close();

                        ImageIcon MyImage = new ImageIcon(con.getPatientId()+con.getName()+con.getConsul_No()+"-decrypt.jpg");
                        Image img = MyImage.getImage();
                        Image newImg = img.getScaledInstance(add_photo.getWidth(), add_photo.getHeight(), Image.SCALE_SMOOTH);
                        add_photo.setIcon(new ImageIcon(newImg));

                    }catch (Exception ignored){
                        topic1.setOpaque(false);
                        add_photo.setOpaque(false);

                    }

                }
            }
            this.add(set_AdditionalNote);
            this.add(topic);
            this.add(topic1);
            this.add(add_photo);

        }

        button();
        this.add(image);
        makeFrame(name,500,530);
    }


    public void button_set(JButton but,String name) {
        but.setBounds(210,420,70,30);
        but.setText(name);
        but.setFocusable(false);
        but.addActionListener(this);
        this.add(but);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()== ok) {
            if(start) {
                this.dispose();
                new Gui_main();
            }else{
                try{
                    File file= new File(filename);
                    file.delete();
                }catch (Exception ignored){}
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
