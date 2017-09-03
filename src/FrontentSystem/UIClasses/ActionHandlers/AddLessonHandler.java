package FrontentSystem.UIClasses.ActionHandlers;

import FrontentSystem.UIClasses.ContextPanes.AddLessonContextPane;
import FrontentSystem.UIClasses.ContextPanes.AddStudentContextPane;
import FrontentSystem.UIClasses.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by StefStef on 6/11/2017.
 */
public class AddLessonHandler implements ActionListener {
    private MainWindow parentCaller=null;
    public void actionPerformed(ActionEvent e){
        this.parentCaller.setContentPane(new AddLessonContextPane(this.parentCaller).getContentPane());

        this.parentCaller.PseudoUpdate();
    }
    public AddLessonHandler(MainWindow parentCaller){
        this.parentCaller = parentCaller;
    }
}
