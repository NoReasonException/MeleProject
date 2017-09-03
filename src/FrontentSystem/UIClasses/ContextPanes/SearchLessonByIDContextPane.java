package FrontentSystem.UIClasses.ContextPanes;

import BackEndSystem.Entities.OrganizationEntities.Lesson;
import FrontentSystem.UIClasses.ActionHandlers.SearchStudByID;
import FrontentSystem.UIClasses.MainWindow;

import javax.swing.*;

/**
 * Created by StefStef on 6/12/2017.
 */
public class SearchLessonByIDContextPane extends SearchStudentByIDContextPane {
    private MainWindow parentCaller=null;

    public SearchLessonByIDContextPane(MainWindow parentCaller){
        super(parentCaller);
        this.parentCaller=parentCaller;
        this.Title.setText("Αναζήτηση φοιτητή με βάση το ID");

    }
    @Override
    public java.lang.String getFirstLabelText(){
        return "ID Μαθήματος";
    }
    @Override
    public java.awt.event.ActionListener getButtonsActionListener(){
        java.lang.String b[]=new java.lang.String[2];
        b[0]="Κωδικός";
        b[1]="Όνομα";

        return (e)->{
            java.lang.Object[][]data=new java.lang.Object[1][2];
            Lesson tmp;

            try{
                tmp=Lesson.getLessonByID(Integer.valueOf(this.FirstLabelTextField.getText()));
                data[0][0]=String.valueOf(tmp.getLessonID());
                data[0][1]=String.valueOf(tmp.getLessonName());
                System.out.println("[INFO]Found "+tmp);
                this.parentCaller.setContentPane(new SimpleLessonDataViewer(data,b,"Αναζήτηση φοιτητή με ID").getContentPane());
                this.parentCaller.PseudoUpdate();
            }catch (IllegalArgumentException err){
                JOptionPane.showMessageDialog(parentCaller, "Ο κωδικός που εισήχθη δεν βρέθηκε...", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            }
        };
    }

}
