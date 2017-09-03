/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontentSystem.UIClasses.SmallDialogs;

import BackEndSystem.Entities.Email.SendEmailMain;
import CommonSrc.FontLoaderBase;
import com.sun.javafx.tk.FontLoader;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author StefStef
 */
public class EmailSenderDialog extends JFrame {

    private java.awt.GridBagLayout mainLay = new java.awt.GridBagLayout();
    private java.awt.GridBagConstraints mainLayConfigs = new java.awt.GridBagConstraints();
    private java.awt.Container MetaDataContainer = new java.awt.Container();
    private java.awt.GridBagLayout MetaDataContainerLayout = new java.awt.GridBagLayout();
    private java.awt.GridBagConstraints MetaDataContainerLayoutConfig = new java.awt.GridBagConstraints();

    private javax.swing.JMenuBar WindowJMenuBar = new javax.swing.JMenuBar();
    private javax.swing.JMenu ChoicesBar = new javax.swing.JMenu("Επιλογές");
    private javax.swing.JMenuItem SendEmailBar = new javax.swing.JMenuItem("Αποστολή");
    private javax.swing.JMenuItem ClearFormBar = new javax.swing.JMenuItem("Καθαρισμός Στοιχείων");
    private javax.swing.JMenuItem ExitBar = new javax.swing.JMenuItem("Έξοδος");

    private javax.swing.JTextArea EmailMainTextArea = new javax.swing.JTextArea(10, 60);
    JTextField tst2 = new JTextField(20);
    JTextField tst3 = new JTextField(20);
    JTextField tst4 = new JTextField(20);
    private static javax.swing.ImageIcon PrimaryImageIcon = new javax.swing.ImageIcon("Res/Img/SendEmailIcon.png");
    private  javax.swing.JLabel PrimaryImageIconLabel = new javax.swing.JLabel(EmailSenderDialog.PrimaryImageIcon);

    public EmailSenderDialog(java.lang.String preferedEmailToSend,JFrame parentCaller) {
        this.setLocationRelativeTo(parentCaller);
        this.setLayout(this.mainLay);
        this.setTitle("Αποστολή E-mail ");
        this.CreateMetaDataContainer(preferedEmailToSend);
        this.setSize(690, 370);
        this.setMinimumSize(new Dimension(690, 370));
        this.mainLayConfigs.gridwidth = GridBagConstraints.REMAINDER;
        this.mainLay.setConstraints(this.MetaDataContainer, mainLayConfigs);
        this.add(this.MetaDataContainer);
        this.mainLay.setConstraints(this.EmailMainTextArea, mainLayConfigs);
        this.add(this.EmailMainTextArea);
        this.setupMenus();
        this.setJMenuBar(this.WindowJMenuBar);
        this.loadMenuHandlers();
    }

    private void CreateMetaDataContainer(java.lang.String preferedEmailToSend) {
        JButton tst = new JButton("Αποστολή");

        JLabel tstLab2 = new JLabel("Απόστολέας ");
        JLabel tstLab3 = new JLabel("Παραλήπτης ");
        JLabel tstLab4 = new JLabel("Θέμα Email ");
        tst2.setEditable(false);
        tst2.setText("University@gmail.com");
        tst4.setText("Μύνημα απο την Γραμματεία ");
        tst3.setText(preferedEmailToSend);

        this.MetaDataContainer.setLayout(this.MetaDataContainerLayout);
        this.MetaDataContainerLayoutConfig.insets = new java.awt.Insets(5, 5, 5, 5);
        
        this.MetaDataContainerLayoutConfig.gridwidth = GridBagConstraints.RELATIVE;
        this.MetaDataContainerLayout.setConstraints(this.PrimaryImageIconLabel, mainLayConfigs);
        this.add(this.PrimaryImageIconLabel);
        this.MetaDataContainerLayout.setConstraints(tstLab2, MetaDataContainerLayoutConfig);//HERE
        this.MetaDataContainerLayoutConfig.gridwidth = GridBagConstraints.REMAINDER;
        this.MetaDataContainerLayout.setConstraints(tst2, MetaDataContainerLayoutConfig);

        this.MetaDataContainerLayoutConfig.gridwidth = GridBagConstraints.RELATIVE;
        this.MetaDataContainerLayout.setConstraints(tstLab3, MetaDataContainerLayoutConfig);//HERE
        this.MetaDataContainerLayoutConfig.gridwidth = GridBagConstraints.REMAINDER;
        this.MetaDataContainerLayout.setConstraints(tst3, MetaDataContainerLayoutConfig);

        this.MetaDataContainerLayoutConfig.gridwidth = GridBagConstraints.RELATIVE;
        this.MetaDataContainerLayout.setConstraints(tstLab4, MetaDataContainerLayoutConfig);//HERE
        this.MetaDataContainerLayoutConfig.gridwidth = GridBagConstraints.REMAINDER;
        this.MetaDataContainerLayout.setConstraints(tst4, MetaDataContainerLayoutConfig);

        this.MetaDataContainer.add(tstLab2);
        this.MetaDataContainer.add(tst2);
        this.MetaDataContainer.add(tstLab3);
        this.MetaDataContainer.add(tst3);
        this.MetaDataContainer.add(tstLab4);
        this.MetaDataContainer.add(tst4);

    }

    private void setFonts() {
        for (int i = 0; i < this.MetaDataContainer.getComponentCount(); i++) {
            this.MetaDataContainer.getComponent(i).setFont(FontLoaderBase.getFont());
        }
    }

    private void setupMenus() {
        this.ChoicesBar.add(this.SendEmailBar);
        this.ChoicesBar.add(this.ClearFormBar);
        this.ChoicesBar.add(this.ExitBar);

        this.WindowJMenuBar.add(this.ChoicesBar);

    }
    protected java.awt.event.ActionListener getSendButtonListener(){
        return (e) -> {
            if (Pattern.compile("^[a-z]{1}[a-zA-Z1-9]{1,30}@((gmail)|(hotmail)|(teiath))\\.((gr)|(com))$").matcher(this.tst3.getText()).matches()) {
                if (SendEmailMain.SendEmail(this.tst3.getText(),this.tst4.getText(), this.EmailMainTextArea.getText())) {
                    JOptionPane.showMessageDialog(this, "Το Email Στάλθηκε με επιτυχία", "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Υπήρξε πρόβλημα κατα την αποστολή του Email\nΕπικοινωνήστε με την υποστηριξη(Κωδικός λάθους SRV_DEFUSES)", "Σφάλμα", JOptionPane.ERROR_MESSAGE);

                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Το πεδίο Email είναι λανθασμένο ή μη συμβατό", "Σφάλμα", JOptionPane.ERROR_MESSAGE);

            }

        };
    }
    protected boolean mustCleanReceiversData(){
        return true;
    }
    private void loadMenuHandlers() {
        this.ExitBar.addActionListener((e) -> {
            this.setVisible(false);
            this.dispose();
        });
        this.ClearFormBar.addActionListener((e) -> {
            //this.tst2.setText("");
            if(this.mustCleanReceiversData()){
                 this.tst3.setText("");
            }
           
            this.tst4.setText("");
            this.EmailMainTextArea.setText("");
        });
        this.SendEmailBar.addActionListener(this.getSendButtonListener());
    }
    public java.lang.String getEmail(){
        return this.EmailMainTextArea.getText();
    }

}
