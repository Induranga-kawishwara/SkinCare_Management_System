package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import static Console.WestminsterSkinConsultationManager.consult;

//OUR MAIN CLASS
public class ButtonClumn extends GUI_table implements ActionListener {
    private boolean rat;
    protected static int selectID ;
    private JTextField PaID;
    private  JButton ok,submit,back;

    public ButtonClumn(boolean rat,int pati){

        this.rat = rat;
        selectID=pati;

        JLabel topic = new JLabel();
        topic.setText("ALL FUCKERS");
        topic.setBounds(370, 20, 550, 20);
        topic.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));

        if(rat) {
            PaID = new JTextField();
            PaID.setBounds(280,200,300,60);
            PaID.setFont(new Font("console",Font.ITALIC,15));
            this.add(PaID);



        }else{

            //COLUMN HEADERS
            String[] columnHeaders = {"Consultation-No","Patient-Id", "Name", "SurName", "Birthday", " Phone-N0", "Doctor-Id", "Date","Start-Time","End-Time","Cost"};
            //FORM TITLE

            DefaultTableModel tableModel = new DefaultTableModel(columnHeaders, 0);
            JTable table = new JTable(tableModel);


            for (Console.Consultation Con : consult) {
                if(selectID == Con.getPatientId()) {
                    String[] details = {String.valueOf(Con.getWhichco()), String.valueOf(Con.getPatientId()),Con.getName(), Con.getSurname(), String.valueOf(Con.getDateOfBirth()), Con.getMobileNo(), Con.getDocconsulId(), String.valueOf(Con.getConDate()), String.valueOf(Con.getConsulStart()), String.valueOf(Con.getConsulEnd()), String.valueOf(Con.getCost())};
                    tableModel.addRow(details);
                }

            }

            table.getColumnModel().getColumn(0).setCellRenderer(new ButtonRenderer());

            //SET CUSTOM EDITOR TO TEAMS COLUMN
            table.getColumnModel().getColumn(0).setCellEditor(new ButtonEditor(new JTextField()));

            JScrollPane pane = new JScrollPane(table);
//        pane.setSize(800,300);
            pane.setBounds(35, 70, 800, 300);


            //DATA FOR OUR TABLE

            //CREATE OUR TABLE AND SET HEADER


            //SET CUSTOM RENDERER TO TEAMS COLUMN


            //SCROLLPANE,SET SZE,SET CLOSE OPERATION

            this.add(pane);
        }

        this.add(topic);
        button();

        window("Your All Consultations",900,500);
    }
    public void button_set(JButton but,String name,int x , int y) {
        but.setBounds(x,y,80,30);
        but.setText(name);
        System.out.println("button set eka asse");
        but.setFocusable(false);
        but.addActionListener(this);
        this.add(but);
    }

    @Override
    public void button() {

        if(rat){
            submit = new JButton();
            button_set(submit,"SUBMIT",400,400);

            back = new JButton();
            button_set(back,"BACK",30,20);

        }else{
            ok = new JButton();
            button_set(ok,"OK",400,400);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== ok) {
            this.dispose();
            new Gui_main();

        }else if (e.getSource()== submit){
            int patientID = Integer.parseInt(PaID.getText().trim());
//            int patientID = 0;
            this.dispose();
            new ButtonClumn(false,patientID);

        }else{
            this.dispose();
            new Gui_main();
        }
    }
}

//BUTTON RENDERER CLASS
class ButtonRenderer extends JButton implements  TableCellRenderer {

    //CONSTRUCTOR
    public ButtonRenderer() {
        //SET BUTTON PROPERTIES
        setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object obj,
                                                   boolean selected, boolean focused, int row, int col) {

        //SET PASSED OBJECT AS BUTTON TEXT
        setText((obj==null) ? "":obj.toString());

        return this;
    }

}

//BUTTON EDITOR CLASS
class ButtonEditor extends DefaultCellEditor {
    protected JButton btn;
    private String lbl;
    private Boolean clicked;

    public ButtonEditor(JTextField txt) {
        super(txt);

        btn=new JButton();
        btn.setOpaque(true);

        //WHEN BUTTON IS CLICKED
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                fireEditingStopped();
            }
        });
    }

    //OVERRIDE A COUPLE OF METHODS
    @Override
    public Component getTableCellEditorComponent(JTable table, Object obj,
                                                 boolean selected, int row, int col) {

        //SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
        lbl=(obj==null) ? "":obj.toString();
        btn.setText(lbl);
        clicked=true;
        return btn;
    }

    //IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
    @Override
    public Object getCellEditorValue() {

        if(clicked)
        {
            new Frame1pop(false,ButtonClumn.selectID,Integer.parseInt(lbl));
            System.out.println(lbl);
            //SHOW US SOME MESSAGE
//            JOptionPane.showMessageDialog(btn, lbl+" Clicked");

        }
        //SET IT TO FALSE NOW THAT ITS CLICKED
        clicked=false;
        return lbl;
    }

    @Override
    public boolean stopCellEditing() {

        //SET CLICKED TO FALSE FIRST
        clicked=false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        // TODO Auto-generated method stub
        super.fireEditingStopped();
    }
}
