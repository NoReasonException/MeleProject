package FrontentSystem.UIClasses.ActionHandlers;

import BackEndSystem.Entities.OrganizationEntities.Lesson;
import FrontentSystem.UIClasses.ContextPanes.SimpleDataViewer;
import FrontentSystem.UIClasses.ContextPanes.SimpleLessonDataViewer;
import FrontentSystem.UIClasses.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by StefStef on 6/11/2017.
 */
public class ViewAllLessonsHandler implements ActionListener {
    private MainWindow parentCaller=null;
    public void actionPerformed(ActionEvent e){
        java.lang.String []b=new java.lang.String[2];
        b[0]="Κωδικός";
        b[1]="Όνομα";
        this.parentCaller.setContentPane(new SimpleLessonDataViewer(Lesson.to2DRowRepresentationArray(Lesson.getAllLessons()),b,"Προβολή όλων των μαθημάτων"){
            @Override
            protected javax.swing.ImageIcon getImageIcon(){
                return new javax.swing.ImageIcon("Res/Img/AddNewLesson.png");
            }

        }.getContentPane());
        this.parentCaller.PseudoUpdate();
    }
    public ViewAllLessonsHandler(MainWindow parentCaller){
        this.parentCaller=parentCaller;
    }

}
