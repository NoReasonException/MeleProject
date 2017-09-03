package FrontentSystem.UIClasses.SmallDialogs;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by stefstef on 7/6/2017.
 */
public class AddEmailDialog extends AbstractAddDataDialog {
    private ViewAdditionalDataDialog toCaller=null;
    public AddEmailDialog(ViewAdditionalDataDialog parent){
        super(parent);
        this.toCaller=parent;

    }
    @Override
    protected java.lang.String MygetTitle(){
        return "Προσθήκη Email";
    }
    @Override
    protected java.lang.String getCentralTitle(){
        return "Εισαγωγή νέου Email";
    }
    @Override
    protected javax.swing.ImageIcon getNorthImageIcon(){
        return new javax.swing.ImageIcon("Res/Img/email.png");
    }
    @Override
    protected java.awt.event.ActionListener getOkayButtonActionListener(){
        return (new java.awt.event.ActionListener(){
            AbstractAddDataDialog toCallerDialog=null;
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("[INFO]Add Email For Person (ID="+this.toCallerDialog.parentFrame.person.getID()+")");
                try{
                    this.toCallerDialog.parentFrame.person.addEmail(this.toCallerDialog.addDataField.getText(),true);
                    this.toCallerDialog.parentFrame.PseudoUpdate();

                    this.toCallerDialog.setVisible(false);
                    this.toCallerDialog.dispose();

                }catch(SQLException err){
                    System.out.println("[ERR]User Inserted Invalid Type of Email,Throw Warning");
                    JOptionPane.showMessageDialog(this.toCallerDialog.parentFrame,"Το Email αυτό δεν είναι σωστό!","Λάθος εισαγωγή στοιχείων",JOptionPane.ERROR_MESSAGE);

                }


            }
            public java.awt.event.ActionListener getInstance(AddEmailDialog caller){
                this.toCallerDialog=caller;
                return this;
            }
        }.getInstance(this));
    }
}
