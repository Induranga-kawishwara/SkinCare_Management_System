package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

//OUR MAIN CLASS
public class ButtonClumn extends GUI_table  {

    private  JButton ok;

    public ButtonClumn(){

        ImageIcon img = new ImageIcon("src/GUI/frame2.jpg");
        JLabel topic = new JLabel();
        topic.setText("ALL FUCKERS");
        topic.setBounds(300,20,550,20);
        topic.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));

        Object[][] data=
                {
                        {"1","Man Utd",2013,"21"},
                        {"2","Man City",2014,"3"},
                        {"3","Chelsea",2015,"7"},
                        {"4","Arsenal",1999,"10"},
                        {"5","Liverpool",1990,"19"},
                        {"6","Everton",1974,"1"},
                };

        //COLUMN HEADERS
        String[] columnHeaders ={"Position","Team","Last Year Won","Trophies"};
        //FORM TITLE

        DefaultTableModel tableModel = new DefaultTableModel(data,columnHeaders);
        JTable table = new JTable(tableModel);
//        table.setBounds(35,100,800,300);

        table.getColumnModel().getColumn(1).setCellRenderer(new ButtonRenderer());;

        //SET CUSTOM EDITOR TO TEAMS COLUMN
        table.getColumnModel().getColumn(1).setCellEditor(new ButtonEditor(new JTextField()));

        JScrollPane pane=new JScrollPane(table);
        pane.setSize(800,300);

//        ButtonClumn bc=new ButtonClumn();
//        bc.setVisible(true);

        //DATA FOR OUR TABLE
        //CREATE OUR TABLE AND SET HEADER
//        JTable table=new JTable(data,columnHeaders);

        //SET CUSTOM RENDERER TO TEAMS COLUMN


        //SCROLLPANE,SET SZE,SET CLOSE OPERATION
        this.add(topic);
        this.add(pane);


//        this.add(colum);
//        this.add(table);



        System.out.println("GUI file eka wada hutto");
        button();
//        this.add(rat);
        window("All Doctors Exist",900,500);
    }
    public void button_set(JButton but,String name) {
        but.setBounds(210,400,70,30);
        but.setText(name);
        System.out.println("button set eka asse");
        but.setFocusable(false);
        but.addActionListener(e -> {
            dispose();
            new Gui_main();
        });
        this.add(but);
    }

    @Override
    public void button() {

        ok = new JButton();
        button_set(ok,"OK");

    }
}

//BUTTON RENDERER CLASS
class ButtonRenderer extends JButton implements  TableCellRenderer
{

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
class ButtonEditor extends DefaultCellEditor
{
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
            //SHOW US SOME MESSAGE
            JOptionPane.showMessageDialog(btn, lbl+" Clicked");
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
