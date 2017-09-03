package FrontentSystem.UIClasses.ContextPanes;

import BackEndSystem.Entities.Student;
import FrontentSystem.UIClasses.EditHandlers.StudentsViewEditHandler;
import FrontentSystem.UIClasses.ListSelectionListeners.AdditionalInfoActivateBoxListener;
import FrontentSystem.UIClasses.MainWindow;

import javax.swing.*;

/**
 * Created by StefStef on 6/11/2017.
 */
public class SearchStudentByIDContextPane extends SearchStudentByNameContextPane {
    private MainWindow parentCaller =null;

    public SearchStudentByIDContextPane(MainWindow parentCaller) {
        super(parentCaller);
        this.Title.setText("Αναζήτηση φοιτητή με βάση το ID");
        this.SecondLabel.setVisible(false);
        this.SecondLabelTextField.setVisible(false);
        this.parentCaller=parentCaller;
    }

    @Override
    public java.lang.String getFirstLabelText() {
        return "ID Μαθητή";
    }

    @Override
    public javax.swing.ImageIcon getAddDataImage() {
        return new javax.swing.ImageIcon("Res/Img/SearchStudentByID.png");
    }

    @Override
    public java.awt.event.ActionListener getButtonsActionListener() {
        java.lang.String b[]=new java.lang.String[5];
        b[0]="Κωδικός";
        b[1]="Όνομα";
        b[2]="Επίθετο";
        b[3]="Ημερομηνία Γέννησης";
        b[4]="Λοιπές Πληροφορίες";
        Object row[][]=new Object[1][5];
        return (e) -> {
            Student stu;
            try {
                stu = Student.getStudentByID(Integer.valueOf(this.FirstLabelTextField.getText()));
                row[0][0]=String.valueOf(stu.getID());
                row[0][1]=String.valueOf(stu.getName());
                row[0][2]=String.valueOf(stu.getSurname());
                row[0][3]=String.valueOf(stu.getDateOfBirth().toString());
                row[0][4]=new JButton(b[4]);
                this.parentCaller.setContentPane(new SimpleStudentDataViewer(row,b,"Αναζήτηση Φοιτητή με ID").getContentPane());



            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(parentCaller, "Τα δεδομένα που εισήχθησαν δεν είναι έγκυρα", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException err) {
                JOptionPane.showMessageDialog(parentCaller, "Ο κωδικός που εισήχθη δεν βρέθηκε...", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            }
        };
    }
}

