package FrontentSystem.UIClasses.SmallDialogs;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;

/**
 * Created by stefstef on 7/6/2017.
 */
public class AddPhoneDialog extends AbstractAddDataDialog {
    public AddPhoneDialog(ViewAdditionalDataDialog parent){
        super(parent);

    }
    @Override
    protected java.lang.String MygetTitle(){
        return "Προσθήκη Τηλεφώνου";
    }
    @Override
    protected java.lang.String getCentralTitle(){
        return "Εισαγωγή νέου τηλεφώνου";
    }
    @Override
    protected javax.swing.ImageIcon getNorthImageIcon(){
        return new javax.swing.ImageIcon("Res/Img/mobile-phone.png");
    }
    @Override
    protected java.awt.event.ActionListener getOkayButtonActionListener(){

        return (new java.awt.event.ActionListener(){
            AbstractAddDataDialog toCallerDialog=null;
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("[INFO]Add Phone For Person (ID="+this.toCallerDialog.parentFrame.person.getID());

                if(Pattern.compile("^[0-9]{10,15}$").matcher(this.toCallerDialog.addDataField.getText()).matches()){
                    this.toCallerDialog.parentFrame.person.addPhone(this.toCallerDialog.addDataField.getText(),true);
                    this.toCallerDialog.parentFrame.PseudoUpdate();

                    this.toCallerDialog.setVisible(false);
                    this.toCallerDialog.dispose();
                }else{
                    System.out.println("[ERR]Error user inserted invalid phone ,throw Error Message");
                    JOptionPane.showMessageDialog(this.toCallerDialog.parentFrame,"Το τηλέφωνο αυτό δεν είναι σωστό,εισάγεται ένα σωστό νούμερο(πρότυπο Ε.164)","Λάθος εισαγωγή στοιχείων",JOptionPane.ERROR_MESSAGE);
                }


            }
            public java.awt.event.ActionListener getInstance(AddPhoneDialog caller){
                this.toCallerDialog=caller;
                return this;
            }
        }.getInstance(this));


    }
}
