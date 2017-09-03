package FrontentSystem.UIClasses.ActionHandlers;

import BackEndSystem.Entities.Person;
import BackEndSystem.Entities.Student;
import FrontentSystem.UIClasses.ContextPanes.SimpleDataViewer;
import FrontentSystem.UIClasses.ContextPanes.SimpleStudentDataViewer;
import FrontentSystem.UIClasses.EditHandlers.StudentsViewEditHandler;
import FrontentSystem.UIClasses.ListSelectionListeners.AdditionalInfoActivateBoxListener;
import FrontentSystem.UIClasses.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by stefstef on 11/6/2017.
 */
public class ViewAllStudentsHandler implements ActionListener {
    private MainWindow parentCaller = null;

    public void actionPerformed(ActionEvent e){
        java.lang.String []b=new java.lang.String[5];
        b[0]="Κωδικός";
        b[1]="Όνομα";
        b[2]="Επίθετο";
        b[3]="Ημερομηνία Γέννησης";
        b[4]="Λοιπές Πληροφορίες";
        java.lang.Object [][] Data = Student.to2DRowRepresentationArray(Student.searchStudentByName(""));
        this.parentCaller.setContentPane(new SimpleStudentDataViewer(Data,b,"Προβολή όλων των μαθητών").getContentPane());
        this.parentCaller.PseudoUpdate();

    }
    public ViewAllStudentsHandler(MainWindow parentCaller){
        this.parentCaller= parentCaller;

    }
}
