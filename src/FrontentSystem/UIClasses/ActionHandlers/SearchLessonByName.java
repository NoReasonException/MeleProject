package FrontentSystem.UIClasses.ActionHandlers;

import FrontentSystem.UIClasses.ContextPanes.AbstractSimpleFormFrame;
import FrontentSystem.UIClasses.ContextPanes.SearchLessonByNameContextPane;
import FrontentSystem.UIClasses.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by StefStef on 6/11/2017.
 */
public class SearchLessonByName implements ActionListener {
    private MainWindow parentFrame=null;
    public SearchLessonByName(MainWindow parentFrame){
        this.parentFrame=parentFrame;

    }
    public void actionPerformed(ActionEvent e){
        this.parentFrame.setContentPane(new SearchLessonByNameContextPane(this.parentFrame).getContentPane());
        this.parentFrame.PseudoUpdate();
    }

}
