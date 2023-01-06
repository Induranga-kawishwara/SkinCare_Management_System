package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import static Console.WestminsterSkinConsultationManager.consult;

//OUR MAIN CLASS
public class ViewHistory_frame extends GUI_table implements ActionListener {
    private final boolean rat;
    protected static int selectID ;
    private JTextField PaID;
    private  JButton ok;
    private JButton submit;

    public ViewHistory_frame(boolean start, int patientId){

        this.rat = start;
        selectID=patientId;

        ImageIcon ima = new ImageIcon("src/GUI/ViewHistory_frame.jpg");

        JLabel topic = new JLabel();
        topic.setText("Your Previous Consultations");
        topic.setFont (new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 20));
        topic.setForeground(Color.white);


        JLabel image = new JLabel();
        image.setIcon(ima);


//        topic.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));


        int width;
        int height;
        if(start) {
            width =500;
            height =500;
            topic.setBounds(110, 50, 550, 20);
            image.setBounds(0,0,500,500);

            JLabel pationIdlable = new JLabel();
            pationIdlable.setText("Enter Your Patient-Id :");
            pationIdlable.setBounds(115, 170, 550, 20);
            pationIdlable.setForeground(Color.white);
            pationIdlable.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 18));

            PaID = new JTextField();
            PaID.setBounds(90,230,300,50);
            PaID.setFont(new Font("console",Font.ITALIC,15));
            this.add(pationIdlable);
            this.add(PaID);




        }else{
            width =910;
            height =550;

            topic.setBounds(310, 20, 550, 20);
            image.setBounds(0,0,910,550);

            JLabel message = new JLabel();
            message.setText("(Click On No In This Table To See Your Added Note And Picture)");
            message.setBounds(180, 380, 800, 20);
            message.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
            message.setForeground(Color.red);

            //COLUMN HEADERS
            String[] columnHeaders = {"No","Patient-Id", "Name", "SurName", "Birthday", " Phone-N0", "Doctor-Id", "Date","Start-Time","End-Time","Cost"};
            //FORM TITLE

            DefaultTableModel tableModel = new DefaultTableModel(columnHeaders, 0);
            JTable table = new JTable(tableModel);


            for (Console.Consultation Con : consult) {
                if(selectID == Con.getPatientId()) {
                    String[] details = {String.valueOf(Con.getConsul_No()), String.valueOf(Con.getPatientId()),Con.getName(), Con.getSurname(), String.valueOf(Con.getDateOfBirth()), Con.getMobileNo(), Con.getConsulted_DocId(), String.valueOf(Con.getConsul_Date()), String.valueOf(Con.getConsul_StartTime()), String.valueOf(Con.getConsul_EndTime()), String.valueOf(Con.getCost())};
                    tableModel.addRow(details);
                }

            }

            table.getColumnModel().getColumn(0).setCellRenderer(new ButtonMake());

            //SET CUSTOM EDITOR TO TEAMS COLUMN
            table.getColumnModel().getColumn(0).setCellEditor(new ButtonEditor(new JTextField()));

            JScrollPane pane = new JScrollPane(table);
//        pane.setSize(800,300);
            pane.setBounds(20, 70, 850, 300);


            //DATA FOR OUR TABLE

            //CREATE OUR TABLE AND SET HEADER


            //SET CUSTOM RENDERER TO TEAMS COLUMN


            //SCROLLPANE,SET SZE,SET CLOSE OPERATION

            this.add(pane);
            this.add(message);
        }

        this.add(topic);
        button();
        this.add(image);

        makeFrame("Your All Consultations", width, height);
    }
    public void button_set(JButton but,String name,int x , int y) {
        but.setBounds(x,y,80,30);
        but.setText(name);
        but.setFocusable(false);
        but.addActionListener(this);
        this.add(but);
    }

    @Override
    public void button() {

        if(rat){
            submit = new JButton();
            button_set(submit,"SUBMIT",280,400);

            JButton back = new JButton();
            button_set(back,"BACK",140,400);

        }else{
            ok = new JButton();
            button_set(ok,"OK",400,450);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== ok) {
            this.dispose();
            new Gui_main();

        }else if (e.getSource()== submit){
            try {
                int patientID = Integer.parseInt(PaID.getText().trim());
                for(int i=0; i<consult.size();i++) {
                    if(patientID==consult.get(i).getPatientId()) {
                        this.dispose();
                        new ViewHistory_frame(false, patientID);
                        break;
                    }else{
                        if(i==consult.size()-1){
                            JOptionPane.showMessageDialog(null,"Couldn't Find Your ID", "Error",JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }
            }catch (Exception ignored){
                JOptionPane.showMessageDialog(null,"Enter Your Patient ID Correctly", "Error",JOptionPane.ERROR_MESSAGE);
            }

        }else{
            this.dispose();
            new Gui_main();
        }
    }
}

//BUTTON RENDERER CLASS
class ButtonMake extends JButton implements  TableCellRenderer {

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
            new Message_Viewer(false, ViewHistory_frame.selectID,Integer.parseInt(lbl));
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
