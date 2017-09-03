package FrontentSystem.UIClasses.ContextPanes;

import BackEndSystem.Entities.OrganizationEntities.Semester;
import CommonSrc.FontLoaderBase;
import javafx.scene.control.Spinner;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by StefStef on 6/12/2017.
 */
public class AddNewSemesterContextPane extends AbstractSimpleFormFrame {
    protected SpinnerDateModel Fourth_SpinnerModel;
    protected JSpinner Fourth_SpinnerDate;
    protected JLabel Fourth_SpinnerDate_Label = new JLabel("Ημ/νια Τελους");

    public AddNewSemesterContextPane() {
        super("Προσθήκη νέου εξαμήνου");
        this.FirstLabel.setVisible(false);
        this.FirstLabelTextField.setVisible(false);
        this.SecondLabel.setVisible(false);
        this.SecondLabelTextField.setVisible(false);
        this.ThirdLabel.setText("Ημ/νια Αρχής ");
        this.CreateSecondDateComponent();
    }

    @Override
    public javax.swing.ImageIcon getAddDataImage() {
        return new javax.swing.ImageIcon("Res/Img/AddNewSemester.png");
    }

    public void CreateSecondDateComponent() {
        this.Fourth_SpinnerModel = new SpinnerDateModel(new java.util.Date(), null, null, Calendar.ERA);
        this.Fourth_SpinnerDate = new JSpinner(this.Fourth_SpinnerModel);
        this.Fourth_SpinnerDate.setFont(FontLoaderBase.getFont());
        this.Fourth_SpinnerDate.setEditor(new JSpinner.DateEditor(this.Fourth_SpinnerDate, "d-MM-yyyy"));
        this.formLayoutConfig.gridwidth = GridBagConstraints.RELATIVE;
        this.formLayout.setConstraints(this.Fourth_SpinnerDate_Label, this.formLayoutConfig);
        this.formContainer.add(this.Fourth_SpinnerDate_Label);
        this.formLayout.setConstraints(this.Fourth_SpinnerDate, this.formLayoutConfig);
        this.formContainer.add(this.Fourth_SpinnerDate);
        this.Fourth_SpinnerDate_Label.setFont(FontLoaderBase.getFont());
    }

    @Override
    public java.awt.event.ActionListener getButtonsActionListener() {
        return new ActionListener() {
            private AddNewSemesterContextPane parentCaller=null;
            @Override
            public void actionPerformed(ActionEvent e) {
                Date beginDate, endDate;
                beginDate = (java.util.Date) this.parentCaller.DateJSpinner.getValue();
                endDate = (java.util.Date) this.parentCaller.Fourth_SpinnerDate.getValue();
                if (beginDate.after(endDate)) {
                    JOptionPane.showMessageDialog(this.parentCaller,"Η Ημερομηνία αρχής δεν μπορεί να προηγείται της ημερομηνίας τέλους!","Σφάλμα", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    Semester.createSemesterToDB(beginDate, endDate);
                    JOptionPane.showMessageDialog(this.parentCaller,"Το νέο εξάμηνο δημιουργήθηκε!","Επιτυχία", JOptionPane.INFORMATION_MESSAGE);


                }
            }
            public java.awt.event.ActionListener getInstance(AddNewSemesterContextPane parentCaller){
                this.parentCaller=parentCaller;
                return this;
            }
        }.getInstance(this);
    }


}
