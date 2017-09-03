package FrontentSystem.UIClasses.ContextPanes;

import BackEndSystem.Entities.DatabaseCommunications.Database;
import BackEndSystem.Entities.OrganizationEntities.Lesson;
import FrontentSystem.UIClasses.MainWindow;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;

/**
 * Created by StefStef on 6/11/2017.
 */
public class AddLessonContextPane extends AbstractSimpleFormFrame {
    private MainWindow parentCaller = null;
    public AddLessonContextPane(MainWindow parentCaller){
        super("Προσθήκη Μαθήματος");
        this.formContainerDateComponent.setVisible(false);
        this.SecondLabel.setVisible(false);
        this.SecondLabelTextField.setVisible(false);
        this.ThirdLabel.setVisible(false);
        this.parentCaller=parentCaller;

    }

    @Override
    public javax.swing.ImageIcon getAddDataImage(){
        return new javax.swing.ImageIcon("Res/Img/AddLesson.png");
    }
    @Override
    public java.lang.String getFirstLabelText(){
        return "Όνομα μαθήματος ";
    }
    @Override
    public java.awt.event.ActionListener getButtonsActionListener(){
        return (e)->{
            if(Lesson.createLessonToDB(Database.getInstance().getConnection(),this.FirstLabelTextField.getText())>0){
                JOptionPane.showMessageDialog(this.parentCaller,"Το μάθημα εισήχθη με επιτυχία","Επιτυχία",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this.parentCaller,"Συνέβη κάποιο απροσδιόριστο σφάλμα! επικοινωνήστε με την υποστήριξη! \n Κωδικός λάθους(ERR_DTBS_COLPSED)","Σφάλμα",JOptionPane.INFORMATION_MESSAGE);

            }
        };
    }

}
