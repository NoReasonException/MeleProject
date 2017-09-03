package FrontentSystem.UIClasses.ContextPanes;

import BackEndSystem.Entities.OrganizationEntities.Lesson;
import BackEndSystem.Entities.Person;
import BackEndSystem.Entities.Student;
import FrontentSystem.UIClasses.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * Created by StefStef on 6/11/2017.
 */
public class SearchLessonByNameContextPane extends AbstractSimpleFormFrame {
    private MainWindow parentCaller = null;

    public SearchLessonByNameContextPane(MainWindow parentCaller) {
        super("Αναζήτηση Μαθήματος με βάση το όνομα!");
        this.parentCaller = parentCaller;
        this.formContainerDateComponent.setVisible(false);
        this.SecondLabel.setVisible(false);
        this.SecondLabelTextField.setVisible(false);
        this.ThirdLabel.setVisible(false);
    }

    @Override
    public javax.swing.ImageIcon getAddDataImage() {
        return new javax.swing.ImageIcon("Res/Img/SearchLessonByName.png");
    }

    @Override
    public java.lang.String getFirstLabelText() {
        return "Όνομα Μαθήματος";
    }

    @Override
    public java.lang.String getButtonText() {
        return "Αναζήτηση";
    }

    @Override
    protected java.awt.event.ActionListener getButtonsActionListener() {
        return (e) -> {
            boolean nameAvailable, surnameAvailable;
            java.lang.String[] b = new java.lang.String[2];
            b[0] = "Κωδικός";
            b[1] = "Όνομα";
            nameAvailable = !this.FirstLabelTextField.getText().equals("");
            SimpleDataViewer ViewwerObject;
            if (!nameAvailable) {
                JOptionPane.showMessageDialog(this.parentCaller, "Το όνομα πρέπει να συμπληρωθεί", "Λάθος", JOptionPane.ERROR_MESSAGE);//και τα δυο
            }
            ViewwerObject = new SimpleLessonDataViewer(Lesson.to2DRowRepresentationArray(Lesson.searchLessonsByName(this.FirstLabelTextField.getText())), b, "Αναζήτηση Μαθημάτων Με Όνομα ");
            this.parentCaller.setContentPane(ViewwerObject.getContentPane());
            this.parentCaller.PseudoUpdate();

            System.out.println("[INFO]Search Lesson ....");
        };
    }
}
