package FrontentSystem.UIClasses.ListSelectionListeners;

import FrontentSystem.UIClasses.SmallDialogs.ViewAdditionalDataDialog;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by stefstef on 6/6/2017.
 */
public class AdditionalInfoActivateBoxListener implements ListSelectionListener {
    private static final int BUTTON_COLLUMN = 4;
    private javax.swing.JTable refToTable =null;
    private java.lang.String Title=null;
    private java.lang.String Type=null;
    @Override
    public void valueChanged(ListSelectionEvent e){
        javax.swing.ListSelectionModel source =(javax.swing.ListSelectionModel)e.getSource();
        if(this.refToTable.getSelectedColumn()==AdditionalInfoActivateBoxListener.BUTTON_COLLUMN){
            System.out.print("Object With ID"+this.refToTable.getValueAt(this.refToTable.getSelectedRow(),0)+" selected to view additionalData\n");
            new ViewAdditionalDataDialog(this.Title,this.Type,Integer.valueOf((String)this.refToTable.getValueAt(this.refToTable.getSelectedRow(),0)));
        }
    }
    public AdditionalInfoActivateBoxListener(JTable refToTable,java.lang.String Title,java.lang.String Type){
        this.Title=Title;
        this.Type=Type;
        this.refToTable=refToTable;
    }
}
