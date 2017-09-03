package FrontentSystem.UIClasses.ActionHandlers;

import BackEndSystem.Entities.OrganizationEntities.Semester;
import FrontentSystem.UIClasses.ContextPanes.SimpleSemestersDataViewer;
import FrontentSystem.UIClasses.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by StefStef on 6/12/2017.
 */
public class ViewAllSemestersHandler implements ActionListener {
    private MainWindow parentCaller=null;
    public void actionPerformed(ActionEvent e){
        java.lang.String []b=new java.lang.String[3];
        b[0]="Κωδικός";
        b[1]="Ημ/νια Αρχής";
        b[2]="Ημ/νια Τέλους";
        this.parentCaller.setContentPane(new SimpleSemestersDataViewer(Semester.to2DRowRepresentationArray(Semester.getAllSemesters()),b,"Προβολή Εξαμήνων").getContentPane());
        this.parentCaller.PseudoUpdate();
    }
    public ViewAllSemestersHandler(MainWindow parentCaller){
        this.parentCaller=parentCaller;
    }
}
