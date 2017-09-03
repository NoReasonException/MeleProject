package FrontentSystem.UIClasses.ContextPanes;

import CommonSrc.FontLoaderBase;
import FrontentSystem.UIClasses.EditHandlers.TeacherViewEditHandler;
import FrontentSystem.UIClasses.ListSelectionListeners.AdditionalInfoActivateBoxListener;
import FrontentSystem.UIClasses.Renderers.ButtonExtraInfoRenderer;
import FrontentSystem.UIClasses.Renderers.ImageRenderer;
import FrontentSystem.UIClasses.TableModels.SimpleDataViewerDataModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by stefstef on 4/6/2017.
 */
public class SimpleDataViewer extends JFrame {

    protected JTable mainTable;
    private javax.swing.BoxLayout mainLay;
    private javax.swing.JLabel Title;
    private java.lang.String[] col_names;
    private java.lang.Object[][] dat;
    private javax.swing.JButton outToXML;

    public SimpleDataViewer(Object[][] dat, String[] col_names, String Title) {
        this.col_names = col_names;
        this.dat = dat;
        this.mainLay = new javax.swing.BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.setLayout(this.mainLay);
        this.mainTable = new javax.swing.JTable(this.getTableModel(dat, col_names));
        this.mainTable.setAutoCreateColumnsFromModel(false);
        this.mainTable.setDefaultRenderer(javax.swing.ImageIcon.class, new ImageRenderer());
        this.mainTable.setDefaultRenderer(JButton.class, new ButtonExtraInfoRenderer());
        this.mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.mainTable.setCellSelectionEnabled(true);
        this.mainTable.getSelectionModel().addListSelectionListener(this.getListSelectionListener());
        this.add(new JLabel(" "));//Spacer
        this.add(this.Title = new JLabel(Title, this.getImageIcon(), JLabel.CENTER));
        this.Title.setFont(FontLoaderBase.getFont().deriveFont(Font.PLAIN, 17));
        this.add(new JLabel(" "));
        this.add(this.mainTable.getTableHeader());
        this.add(this.mainTable);
        this.mainTable.getModel().addTableModelListener(this.getTableModelListener());
        this.mainTable.getColumnModel().getColumn(0).setMaxWidth(120);
        this.add(new JLabel(" "));
        this.add(this.outToXML = new JButton("Εξαγωγή Σε XML"));
        this.outToXML.addActionListener(this.getToXMListener());
        //this.mainTable.setDefaultRenderer(String.class,new BasicRenderer());
    }

    protected javax.swing.ImageIcon getImageIcon() {
        return new javax.swing.ImageIcon("Res/Img/SearchCompleted.png");
    }

    protected javax.swing.event.ListSelectionListener getListSelectionListener() {
        return new AdditionalInfoActivateBoxListener(this.mainTable, "Πληροφορίες Καθηγητή", "Καθηγητής");
    }

    protected javax.swing.event.TableModelListener getTableModelListener() {
        return new TeacherViewEditHandler();
    }

    protected javax.swing.table.AbstractTableModel getTableModel(Object[][] dat, String[] row) {
        return new SimpleDataViewerDataModel(dat, row);
    }

    protected java.awt.event.ActionListener getToXMListener() {
        return (e) -> {
            try {
                ApachePoi.ToXML n = new ApachePoi.ToXML(this.dat, this.col_names, null);

            }catch(Exception enn){
                System.out.println(enn.getMessage());
            }
        };
    }
}
