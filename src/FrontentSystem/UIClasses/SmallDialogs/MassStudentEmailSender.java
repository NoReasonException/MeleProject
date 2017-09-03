/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontentSystem.UIClasses.SmallDialogs;

import BackEndSystem.Entities.Email.SendEmailMain;
import BackEndSystem.Entities.Student;
import FrontentSystem.UIClasses.MainWindow;
import java.util.Iterator;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author StefStef
 */
public class MassStudentEmailSender extends EmailSenderDialog {

    public MassStudentEmailSender(MainWindow parentCaller) {
        super("{Όλοι οι φοιτητές...}", parentCaller);
        this.tst3.setEditable(false);

    }

    @Override
    protected boolean mustCleanReceiversData() {
        return false;
    }

    @Override
    protected java.awt.event.ActionListener getSendButtonListener() {
        return (e) -> {
            Iterator<BackEndSystem.Entities.Student> itr = BackEndSystem.Entities.Student.getStudents();
            Student tmp;
            boolean fail = false;
            boolean no_defined_email = false;
            while (itr.hasNext()) {

                tmp = itr.next();
                if (tmp.getEmailCount() > 0) {
                    if (!SendEmailMain.SendEmail(tmp.getEmailAt(0), this.tst4.getText(), this.getEmail())) {
                        fail = true;
                    }
                } else {
                    no_defined_email = true;
                }
            }
            if (fail && no_defined_email) {
                JOptionPane.showMessageDialog(this, "Υπήρξε πρόβλημα κατα την αποστολή μερικών Email\nλόγω ή μη διαθέσιμου email ή λόγω υπερφόρτωσης του Server", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            } else if (fail) {
                JOptionPane.showMessageDialog(this, "Υπήρξε πρόβλημα κατα την αποστολή μερικών Email  λόγω υπερφόρτωσης του Server\nΠροσπαθήστε αργότερα", "Σφάλμα", JOptionPane.ERROR_MESSAGE);

            } else if (no_defined_email) {
                JOptionPane.showMessageDialog(this, "Κάποια παραλήπτες δεν ενημερώθηκαν , λόγω οτι το σύστημα δεν εντόπισε διευθύνσεις e-mail για αυτούς", "Πληροφορία", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Τα Email Στάλθηκανε με επιτυχία", "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);

            }

        };

    }

}
