package FrontentSystem.UIClasses.ActionHandlers;

import FrontentSystem.UIClasses.ContextPanes.SearchStudentByNameContextPane;
import FrontentSystem.UIClasses.ContextPanes.SearchTeacherByNameContextPane;
import FrontentSystem.UIClasses.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by stefstef on 11/6/2017.
 */
public class SearchStudentByNameHandler implements ActionListener {
    public MainWindow parentCaller=null;
    @Override
    public void actionPerformed(ActionEvent e){
        this.parentCaller.setContentPane(new SearchStudentByNameContextPane(parentCaller).getContentPane());
        this.parentCaller.PseudoUpdate();
    }
    public SearchStudentByNameHandler(MainWindow parentCaller){
        this.parentCaller=parentCaller;
    }
}
