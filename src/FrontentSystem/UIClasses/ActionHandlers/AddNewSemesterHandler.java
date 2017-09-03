package FrontentSystem.UIClasses.ActionHandlers;

import FrontentSystem.UIClasses.ContextPanes.AddNewSemesterContextPane;
import FrontentSystem.UIClasses.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by StefStef on 6/12/2017.
 */
public class AddNewSemesterHandler implements ActionListener {
    private MainWindow parentCaller=null;
    public void actionPerformed(ActionEvent e){
        this.parentCaller.setContentPane(new AddNewSemesterContextPane().getContentPane());
        this.parentCaller.PseudoUpdate();
    }
    public AddNewSemesterHandler(MainWindow parentCaller){
        this.parentCaller=parentCaller;
    }
}
