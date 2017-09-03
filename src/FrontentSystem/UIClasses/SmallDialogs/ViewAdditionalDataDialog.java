package FrontentSystem.UIClasses.SmallDialogs;

import BackEndSystem.Entities.Person;
import CommonSrc.FontLoaderBase;
import FrontentSystem.UIClasses.ActionHandlers.UnderDevelepomentAction;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

/**
 * Created by stefstef on 7/6/2017.
 */
public class ViewAdditionalDataDialog extends JFrame {
    private static  javax.swing.ImageIcon NoImageFound = new javax.swing.ImageIcon("Res/Img/NoImage.png");

    public Person person = null;
    private java.awt.Container            PhoneTableContainer= new java.awt.Container();
    private java.awt.GridBagLayout        PhoneTableContainerLay= new java.awt.GridBagLayout();
    private java.awt.GridBagConstraints   PhoneTableContainerLayConfig= new java.awt.GridBagConstraints();
    ///------------------------------------------------------------------------------------------------\\\
    private java.awt.Container            EmailTableContainer= new java.awt.Container();
    private java.awt.GridBagLayout        EmailTableContainerLay= new java.awt.GridBagLayout();
    private java.awt.GridBagConstraints   EmailTableContainerLayConfig= new java.awt.GridBagConstraints();
    ///------------------------------------------------------------------------------------------------\\\
    private java.awt.GridBagLayout        mainLay = new java.awt.GridBagLayout();
    private java.awt.GridBagConstraints   mainLayConfigs = new java.awt.GridBagConstraints();

    private javax.swing.ImageIcon         personsIcon=null;
    private javax.swing.JLabel            personIconLabel=null;
    private javax.swing.JTable            PhonesTable =null;
    private javax.swing.JTable            EmailsTable =null;
    private javax.swing.JLabel            TitleLabel =null;

    private javax.swing.JMenuBar          mainMenuBar = new javax.swing.JMenuBar();
    private javax.swing.JMenu             OptionsMenu = new javax.swing.JMenu("Επιλογές...");
    private javax.swing.JMenuItem         AddEmail = new javax.swing.JMenuItem("Προσθήκη Email");
    private javax.swing.JMenuItem         AddPhones = new javax.swing.JMenuItem("Προσθήκη Τηλεφώνου");
    private javax.swing.JMenuItem         PhotoChange = new javax.swing.JMenuItem("Αλλαγή φωτογραφίας");
    private javax.swing.JMenuItem         ExcelOut = new javax.swing.JMenuItem("Έξοδος Σε Excel");
    private javax.swing.JMenuItem         Exit = new javax.swing.JMenuItem("Έξοδος");
    public java.lang.String               type;
    public ViewAdditionalDataDialog(java.lang.String Title,java.lang.String Type,int PersonID){
        super(Title);
        this.person=Person.getPersonByID(PersonID);
        this.loadMenus();
        this.loadHandlers();
        this.LoadJTables();

        this.setLocationRelativeTo(null);
        this.setSize(400,400);
        this.setLayout(mainLay);
        this.type =Type;
        this.loadUI();
        this.setResizable(false);
        this.setVisible(true);

    }
    public void PseudoUpdate(){
        this.setVisible(false);
        new ViewAdditionalDataDialog(this.getTitle(),this.type,this.person.getID()).setLocation(this.getLocation());
        this.dispose();
    }
    private void loadUI(){
        this.mainLayConfigs.gridwidth=GridBagConstraints.REMAINDER;
        this.TitleLabel =new JLabel(this.type +" "+this.person.getName()+" "+this.person.getSurname(),JLabel.CENTER);
        this.mainLay.setConstraints(TitleLabel,this.mainLayConfigs);
        this.add(TitleLabel);
        this.TitleLabel.setFont(FontLoaderBase.getFont().deriveFont(Font.PLAIN,18));

        if(this.person.getSelfPicture()==null){
            this.personsIcon=ViewAdditionalDataDialog.NoImageFound;
        }
        else{
            this.personsIcon=this.person.getSelfPicture();
        }
        //Προσθήκη φωτογραφίας <3 !
        this.mainLayConfigs.gridwidth=GridBagConstraints.REMAINDER;
        this.mainLay.setConstraints(this.personIconLabel=new JLabel(this.personsIcon),this.mainLayConfigs);
        this.add(this.personIconLabel);
        this.PhoneTableCreate();
        this.EmailTableCreate();
        this.mainLayConfigs.gridwidth=GridBagConstraints.RELATIVE;
        this.mainLay.setConstraints(this.PhoneTableContainer,this.mainLayConfigs);
        this.add(this.PhoneTableContainer);
        this.mainLayConfigs.gridwidth=GridBagConstraints.REMAINDER;
        this.mainLay.setConstraints(this.EmailTableContainer,this.mainLayConfigs);
        this.add(this.EmailTableContainer);
    }
    private void PhoneTableCreate(){
        this.PhoneTableContainer.setLayout(this.PhoneTableContainerLay);
        this.PhoneTableContainerLayConfig.gridwidth=GridBagConstraints.REMAINDER;
        this.PhoneTableContainerLay.setConstraints(this.PhonesTable.getTableHeader(),this.PhoneTableContainerLayConfig);
        this.PhoneTableContainerLay.setConstraints(this.PhonesTable,this.PhoneTableContainerLayConfig);
        this.PhoneTableContainer.add(this.PhonesTable.getTableHeader());
        this.PhoneTableContainer.add(this.PhonesTable);
        this.PhoneTableContainer.setVisible(true);
    }
    private void EmailTableCreate(){
        this.EmailTableContainer.setLayout(this.EmailTableContainerLay);
        this.EmailTableContainerLayConfig.gridwidth=GridBagConstraints.REMAINDER;
        this.EmailTableContainerLay.setConstraints(this.EmailsTable.getTableHeader(),this.EmailTableContainerLayConfig);
        this.EmailTableContainerLay.setConstraints(this.EmailsTable,this.EmailTableContainerLayConfig);
        this.EmailTableContainer.add(this.EmailsTable.getTableHeader());
        this.EmailTableContainer.add(this.EmailsTable);
        this.EmailTableContainer.setVisible(true);
    }
    private void loadMenus(){
        this.mainMenuBar.add(this.OptionsMenu);
        this.OptionsMenu.add(this.AddEmail);
        this.OptionsMenu.add(this.AddPhones);
        this.OptionsMenu.add(this.PhotoChange);
        this.OptionsMenu.add(this.ExcelOut);
        this.OptionsMenu.add(this.Exit);
        this.setJMenuBar(this.mainMenuBar);
        for (int i = 0; i < this.mainMenuBar.getMenu(0).getMenuComponentCount(); i++) {
            this.mainMenuBar.getMenu(0).getMenuComponent(i).setFont(FontLoaderBase.getFont());
            ((JMenuItem)this.mainMenuBar.getMenu(0).getMenuComponent(i)).addActionListener(UnderDevelepomentAction.getInstance(this));
        }
        this.mainMenuBar.getMenu(0).setFont(FontLoaderBase.getFont());
    }
    private void loadChangePictureHandler(){
        this.PhotoChange.removeActionListener(this.PhotoChange.getActionListeners()[0]);
        this.PhotoChange.addActionListener((e)->{
            final JFileChooser NewPic = new JFileChooser();
            if(NewPic.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
                try{
                    this.person.setSelfPicture(new ImageIcon(new javax.swing.ImageIcon(NewPic.getSelectedFile().getAbsolutePath()).getImage().getScaledInstance(200,200,Image.SCALE_REPLICATE)),true);
                    this.personIconLabel.setIcon(this.person.getSelfPicture());
                    this.updateViewAround();

                }catch (Exception err){
                    JOptionPane.showMessageDialog(null,"Φαίνεται πως συνέβη κάποιο σφάλμα κατα την διαδικάσία αλλαγής της φωτογραφίας!...\n" +
                            "Προσπαθήστε ξανα ή επικοινωνήστε με την υποστήριξη , Κωδικός λάθους (ERR_IOSTREAMS)","Σφαλμα",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    private void loadAddEMailHandler(){
        this.AddEmail.removeActionListener(this.AddEmail.getActionListeners()[0]);
        this.AddEmail.addActionListener((e)->{
            new AddEmailDialog(this);
        });
    }
    private void loadAddPhoneHandler(){
        this.AddPhones.removeActionListener(this.AddPhones.getActionListeners()[0]);
        this.AddPhones.addActionListener((e)->{
            new AddPhoneDialog(this);
        });
    }
    
    private void loadHandlers(){
        this.loadChangePictureHandler();
        this.loadAddEMailHandler();
        this.loadAddPhoneHandler();
        this.Exit.removeActionListener(this.Exit.getActionListeners()[0]);
        this.Exit.addActionListener((e)->{
            this.setVisible(false);
            this.dispose();
        });

    }
    private void updateViewAround(){
        this.setContentPane(this.getContentPane());
    }
    private void LoadJTables(){
        this.PhonesTable = new javax.swing.JTable(new AbstractTableModel() {
            ViewAdditionalDataDialog parent=null;
            @Override
            public java.lang.String getColumnName(int i){
                return "Τηλέφωνα Επικοινωνίας";
            }
            @Override
            public int getRowCount() {
                return this.parent.person.getPhoneCount()==0?1:this.parent.person.getPhoneCount();
            }

            @Override
            public int getColumnCount() {
                return 1;
            }

            @Override
            public Object getValueAt(int i, int i1) {
                try{
                    return this.parent.person.getPhoneAt(i);
                }catch (IndexOutOfBoundsException e){
                    return "Κανένα διαθέσιμο Τηλέφωνο";
                }
            }
            @Override
            public void setValueAt(Object p , int i,int j){
                if(j>0){System.out.println("[ERR]More than one collumn Asked....");}
                else super.setValueAt(p,i,j);
            }
            public AbstractTableModel getInstanceByGivenParent(ViewAdditionalDataDialog parent){
                this.parent=parent;
                return this;
            }
        }.getInstanceByGivenParent(this));
        this.EmailsTable = new javax.swing.JTable(new AbstractTableModel() {
            ViewAdditionalDataDialog parent=null;
            @Override
            public java.lang.String getColumnName(int i){
                return "Ηλεκρονικό Ταχυδρομέιο";
            }
            @Override
            public int getRowCount() {
                return this.parent.person.getEmailCount()==0?1:this.parent.person.getEmailCount();
            }

            @Override
            public int getColumnCount() {
                return 1;
            }

            @Override
            public Object getValueAt(int i, int i1) {

                try{
                    return this.parent.person.getEmailAt(i);
                }catch (IndexOutOfBoundsException e){
                    return "Κανένα διαθέσιμο Email";
                }

            }
            @Override
            public void setValueAt(Object p , int i,int j){
                if(j>0){System.out.println("[ERR]More than one collumn Asked....");}
                else super.setValueAt(p,i,j);
            }
            public AbstractTableModel getInstanceByGivenParent(ViewAdditionalDataDialog parent){
                this.parent=parent;
                return this;
            }
        }.getInstanceByGivenParent(this));
        this.PhonesTable.getColumnModel().getColumn(0).setMinWidth(150);
        this.EmailsTable.getColumnModel().getColumn(0).setMinWidth(150);
        this.PhonesTable.getSelectionModel().addListSelectionListener((ListSelectionModeInfo)->{
            if(this.person.getPhoneCount()>0){
                if(JOptionPane.showConfirmDialog(null,"Είστε Σίγουρος οτι θέλετε να διαγράψετε αυτό το τηλέφωνο?","Διαγραφή δεδομένων", JOptionPane.YES_NO_OPTION)==0){
                    if(!this.person.deletePhoneAt(this.PhonesTable.getSelectedRow(),true)){
                        JOptionPane.showMessageDialog(this,"Η διαγραφή του τηλεφώνου απέτυχε , λόγω προβλήματος επικοινωνίας με την βάση\nΕπικοινωνήστε με την υποστήριξη(Κωδικός CONN_COLPSD)");
                        return;
                    }
                    this.PseudoUpdate();
                }
            }

        });
        this.EmailsTable.getSelectionModel().addListSelectionListener((ListSelectionModeInfo)->{
            if(this.person.getEmailCount()>0){
                Object []Opt={"Διαγραφή Email","Αποστολή Email","Άκυρο"};
                int Ans;
                System.out.println("[INFO]Email Choosen , Ask User for further instructions...");
                if((Ans=(JOptionPane.showOptionDialog(
                        null,
                        "Επιλέξτε μια ενέργεια για συνέχεια...","Ερώτηση",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        Opt,Opt[0])))==0){
                    System.out.println("\t[INFO_ON_PREVIOUS_INFO]Delete Choosen...");
                    if(!this.person.deleteEmailAt(this.EmailsTable.getSelectedRow(),true)){
                        JOptionPane.showMessageDialog(this,"Η διαγραφή του Email απέτυχε , λόγω προβλήματος επικοινωνίας με την βάση\nΕπικοινωνήστε με την υποστήριξη(Κωδικός CONN_COLPSD)");
                        return;
                    }
                    this.PseudoUpdate();
                }
                else if(Ans==1){
                    System.out.println("\t[INFO_ON_PREVIOUS_INFO]Send Email  Choosen...");
                    new EmailSenderDialog(this.person.getEmailAt(this.EmailsTable.getSelectedRow()),this).setVisible(true);
                    

                }
            }

        });

    }

}
