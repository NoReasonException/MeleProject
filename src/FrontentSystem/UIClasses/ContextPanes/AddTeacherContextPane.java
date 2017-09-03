package FrontentSystem.UIClasses.ContextPanes;

import BackEndSystem.Entities.Teacher;
import FrontentSystem.UIClasses.SmallDialogs.ViewAdditionalDataDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by stefstef on 10/6/2017.
 */
public class AddTeacherContextPane extends AbstractSimpleFormFrame {
    public AddTeacherContextPane(){
        super("Προσθήκη Καθηγητή");
    }

    @Override
    protected ActionListener getButtonsActionListener() {
        return new ActionListener() {
            public AbstractSimpleFormFrame parentCaller;
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int newID;
                java.util.Date dt = (java.util.Date)this.parentCaller.DateJSpinner.getValue();
                if((newID=Teacher.CreateTeacherToDB(this.parentCaller.FirstLabelTextField.getText(),this.parentCaller.SecondLabelTextField.getText(),String.format("%td/%tm/20%ty",dt,dt,dt)))>0){//fix it TODO
                    if(JOptionPane.showConfirmDialog(this.parentCaller,"Ο καθηγητής εισήχθη με επιτυχία!\nΘα θέλατε να εισάγετε περαιτέρω πληροφορίες?","Ερώτηση",JOptionPane.YES_NO_OPTION)==0){
                        new ViewAdditionalDataDialog("Στοιχεία Καθηγητή","Καθηγητής",newID);
                    }

                }
                else{
                    JOptionPane.showMessageDialog(this.parentCaller,"Προέκυψε σφάλμα , και η καταχώρηση δεν εισήθχη!\nΕπικοινωνήστε με την υποστήριξη(Κωδικός Λάθους QUERY_COLPSD)","Σφάλμα",JOptionPane.ERROR_MESSAGE);

                }

            }
            public ActionListener getInstance(AbstractSimpleFormFrame parentCaller){
                this.parentCaller=parentCaller;
                return this;
            }
        }.getInstance(this);
    }
}
