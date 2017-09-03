package FrontentSystem.UIClasses.ContextPanes;

import BackEndSystem.Entities.Person;
import BackEndSystem.Entities.Teacher;
import FrontentSystem.UIClasses.MainWindow;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;

/**
 * Created by stefstef on 11/6/2017.
 */
public class SearchTeacherByNameContextPane extends  AbstractSimpleFormFrame {
    MainWindow parentCaller = null;
    public SearchTeacherByNameContextPane(MainWindow ParentCaller){
        super("Αναζήτηση Καθηγητή με ονοματεπώνυμο");
        this.parentCaller=ParentCaller;
        this.formContainerDateComponent.setVisible(false);
        this.ThirdLabel.setVisible(false);

    }
    @Override
    protected ImageIcon getAddDataImage(){
        return new ImageIcon("Res/Img/SearchTeacherByName.png");
    }
    @Override
    protected java.lang.String getButtonText(){
        return "Αναζήτηση";
    }
    @Override
    protected java.awt.event.ActionListener getButtonsActionListener(){
        return (e)->{
            boolean nameAvailable,surnameAvailable;
            java.lang.String []b=new java.lang.String[5];
            b[0]="Κωδικός";
            b[1]="Όνομα";
            b[2]="Επίθετο";
            b[3]="Ημερομηνία Γέννησης";
            b[4]="Λοιπές Πληροφορίες";
            SimpleDataViewer ViewwerObject;
            nameAvailable=!this.FirstLabelTextField.getText().equals("");
            surnameAvailable=!this.SecondLabelTextField.getText().equals("");
            if(!nameAvailable){
                if(!surnameAvailable){
                    JOptionPane.showMessageDialog(this.parentCaller, "Πρέπει τουλάχιστον ένα απο τα δύο στοιχεία να συμπληρωθούν!", "Λάθος", JOptionPane.ERROR_MESSAGE);//και τα δυο
                }
                else{
                    if(JOptionPane.showConfirmDialog(this.parentCaller, "Το όνομα δεν είναι συμπληρωμένο! θέλετε να αναζητήσετε μόνο με βάση το επίθετο?!", "Ερώτηση", JOptionPane.YES_NO_OPTION)==0){
                        ViewwerObject=new SimpleDataViewer(Person.to2DRowRepresentationArray(Teacher.searchTeacherBySurname(this.SecondLabelTextField.getText())),b,"Αναζήτηση Καθηγητών Με Επίθετο");
                        this.parentCaller.setContentPane(ViewwerObject.getContentPane());
                        this.parentCaller.PseudoUpdate();
                    }

                }
            }
            else if(!surnameAvailable){
                if(JOptionPane.showConfirmDialog(this.parentCaller, "Το Επίθετο  δεν είναι συμπληρωμένο! θέλετε να αναζητήσετε μόνο με βάση το όνομα?!", "Ερώτηση", JOptionPane.YES_NO_OPTION)==0){
                    ViewwerObject=new SimpleDataViewer(Person.to2DRowRepresentationArray(Teacher.searchTeacherByName(this.FirstLabelTextField.getText())),b,"Αναζήτηση Καθηγητών Με Όνομα ");
                    this.parentCaller.setContentPane(ViewwerObject.getContentPane());
                    this.parentCaller.PseudoUpdate();
                }

            }
            else{
                ViewwerObject=new SimpleDataViewer(Person.to2DRowRepresentationArray(Teacher.searchTeacherByNameAndSurname(this.FirstLabelTextField.getText(),this.SecondLabelTextField.getText())),b,"Αναζήτηση Καθηγητών Με Όνομα και Επίθετο");
                this.parentCaller.setContentPane(ViewwerObject.getContentPane());
                this.parentCaller.PseudoUpdate();
            }
            System.out.println("[INFO]Search Teacher ....");
        };
    }

}
