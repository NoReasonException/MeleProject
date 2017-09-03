package FrontentSystem.UIClasses.ActionHandlers;

import FrontentSystem.UIClasses.ContextPanes.AddStudentContextPane;
import FrontentSystem.UIClasses.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by stefstef on 11/6/2017.
 */
public class AddStudentHandler implements ActionListener {
    private MainWindow parentCaller=null;
    public void actionPerformed(ActionEvent e){
        this.parentCaller.setContentPane(new AddStudentContextPane().getContentPane());
        this.parentCaller.PseudoUpdate();
    }
    public AddStudentHandler(MainWindow parentCaller){
        this.parentCaller = parentCaller;
    }
}
