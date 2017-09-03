package FrontentSystem.UIClasses.ActionHandlers;

import FrontentSystem.UIClasses.ContextPanes.SearchLessonByIDContextPane;
import FrontentSystem.UIClasses.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by StefStef on 6/12/2017.
 */
public class SearchLessonByIDHandler implements ActionListener {
    private MainWindow parentCaller = null;
    @Override
    public void actionPerformed(ActionEvent e){
        this.parentCaller.setContentPane(new SearchLessonByIDContextPane(this.parentCaller).getContentPane());
        this.parentCaller.PseudoUpdate();
    }
    public SearchLessonByIDHandler(MainWindow parentCaller){
        this.parentCaller=parentCaller;

    }
}
