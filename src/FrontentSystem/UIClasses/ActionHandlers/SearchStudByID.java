package FrontentSystem.UIClasses.ActionHandlers;

import BackEndSystem.Entities.Person;
import BackEndSystem.Entities.Student;
import FrontentSystem.UIClasses.ContextPanes.SearchLessonByNameContextPane;
import FrontentSystem.UIClasses.ContextPanes.SearchStudentByIDContextPane;
import FrontentSystem.UIClasses.ContextPanes.SearchStudentByNameContextPane;
import FrontentSystem.UIClasses.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by StefStef on 6/11/2017.
 */
public class SearchStudByID implements ActionListener {
    public MainWindow parentCaller=null;
    @Override
    public void actionPerformed(ActionEvent e){
        this.parentCaller.setContentPane(new SearchStudentByIDContextPane(this.parentCaller).getContentPane());
        this.parentCaller.PseudoUpdate();
    }
    public SearchStudByID(MainWindow parentCaller){
        this.parentCaller=parentCaller;
    }
}
