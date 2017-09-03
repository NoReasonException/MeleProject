package FrontentSystem.UIClasses.ActionHandlers;

import FrontentSystem.UIClasses.ContextPanes.SearchTeacherByNameContextPane;
import FrontentSystem.UIClasses.MainWindow;

import java.awt.event.ActionEvent;

/**
 * Created by stefstef on 11/6/2017.
 */
public class SearchTeacherByNameHandler implements   java.awt.event.ActionListener {
    MainWindow parentCaller = null;
    @Override
    public void actionPerformed(ActionEvent e){
        this.parentCaller.setContentPane(new SearchTeacherByNameContextPane(parentCaller).getContentPane());
        this.parentCaller.PseudoUpdate();
    }
    public SearchTeacherByNameHandler(MainWindow ParentCaller){
        this.parentCaller=ParentCaller;
    }
}
