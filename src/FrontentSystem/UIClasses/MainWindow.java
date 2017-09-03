package FrontentSystem.UIClasses;

import CommonSrc.FontLoaderBase;
import FrontentSystem.UIClasses.ActionHandlers.*;
import FrontentSystem.UIClasses.ContextPanes.StartupContextPane;
import FrontentSystem.UIClasses.SmallDialogs.EmailSenderDialog;

import javax.swing.*;

/**
 * Created by stefstef on 4/6/2017.
 */
public class MainWindow extends JFrame {
    private java.awt.Font mainFont;
    private JMenuBar MainMenuBar = new JMenuBar();
    private JMenu SystemMenu = new JMenu("Σύστημα");
    private JMenuItem Disconnect = new JMenuItem("Αποσύνδεση");
    private JMenuItem Restart = new JMenuItem("Επανεκκίνηση");
    private JMenuItem Exit = new JMenuItem("Έξοδος");

    private JMenu Personel = new JMenu("Προσωπικό");
    private JMenuItem AdministrativeStaff = new JMenuItem("Διοικητικό Προσωπικό");
    private JMenu Teachers = new JMenu("Καθηγητές");
    private JMenuItem ViewAllTeachers=new JMenuItem("Προβολή καθηγητών");
    private JMenuItem AddTeacher=new JMenuItem("Προσθήκη Καθηγητή");
    private JMenuItem SearchByName=new JMenuItem("Αναζήτηση με ονοματεπώνυμο");
    private JMenuItem SearchByLesson=new JMenuItem("Αναζήτηση με Μάθημα");
    private JMenuItem MassTeaEmail = new JMenuItem("Αποστολή Email Σε όλους τους καθητητές");

    private JMenuItem EtcStuff = new JMenuItem("Υπόλοιπο Προσωπικό");
    private JMenu Students = new JMenu("Φοιτητες");
    private JMenuItem ViewAllStudents=new JMenuItem("Προβολή φοιτητών");

    private JMenuItem AddStudent = new JMenuItem("Προσθήκη φοιτητή");
    private JMenuItem SearchStudByName = new JMenuItem("Αναζήτηση με ονοματεπώνυμο");
    private JMenuItem SearchStudByID = new JMenuItem("Αναζήτηση με ID");
    private JMenuItem MassStudEmail = new JMenuItem("Αποστολή Email Σε όλους τους φοιτητές");
    private JMenu Lessons = new JMenu("Μάθημα");
    private JMenuItem AddLesson = new JMenuItem("Προσθήκη Μαθήματος");
    private JMenuItem ViewAllLessons = new JMenuItem("Προβολή Μαθημάτων");
    private JMenuItem SearchLesByName = new JMenuItem("Αναζήτηση με Όνομα");
    private JMenuItem SearchLesByID = new JMenuItem("Αναζήτηση με ID");

    private JMenu Semester = new JMenu("Εξάμηνα");
    private JMenuItem AddNewSemester = new JMenuItem("Προσθήκη νεου εξαμήνου");
    private JMenuItem ViewAllSemesters = new JMenuItem("Προβολή εξαμήνων");
    private JMenuItem SearchSemByDate = new JMenuItem("Αναζήτηση με ημερομηνία");
    private JMenuItem SearchSemByID = new JMenuItem("Αναζήτηση με ID");
    
    private JMenu Etc = new JMenu("Επιπλέον");
    private JMenuItem SendEmail = new JMenuItem("Αποστολή Email");
    private JMenuItem Configs = new JMenuItem("Ρυθμίσεις");

    private JMenu HelpMenu = new JMenu("Βοήθεια");
    private JMenuItem GoOnHelpPage = new JMenuItem("Βοήθεια Online");
    private JMenuItem ContactWithSupport = new JMenuItem("Επικοινωνία με την υποστήριξη");
    private JMenuItem About = new JMenuItem("Περί...");

    public MainWindow(){
        super("Διαχείριση Πανεπιστημίου..");
        this.setSize(700,500);

        this.setLocationRelativeTo(null);
        this.setupMenu();
        this.setupFontAndDefaultActions();
        this.setupListeners();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        this.setContentPane((new StartupContextPane()).getContextPane());
    }
    private void setupMenu(){
        this.SystemMenu.add(Disconnect);
        this.SystemMenu.add(Restart);
        this.SystemMenu.add(Exit);

        this.Personel.add(this.AdministrativeStaff);
        this.Teachers.add(this.AddTeacher);
        this.Teachers.add(this.ViewAllTeachers);
        this.Teachers.add(this.SearchByName);
        this.Teachers.add(this.SearchByLesson);
        this.Teachers.add(this.MassTeaEmail);
        this.Personel.add(this.Teachers);
        this.Personel.add(this.EtcStuff);
        this.Students.add(this.ViewAllStudents);
        this.Students.add(this.AddStudent);
        this.Students.add(this.SearchStudByName);
        this.Students.add(this.SearchStudByID);
        this.Students.add(this.MassStudEmail);

        this.Lessons.add(this.AddLesson);
        this.Lessons.add(this.ViewAllLessons);
        this.Lessons.add(this.SearchLesByName);
        this.Lessons.add(this.SearchLesByID);
        this.Semester.add(this.AddNewSemester);
        this.Semester.add(this.ViewAllSemesters);
        this.Semester.add(this.SearchSemByDate);
        this.Semester.add(this.SearchSemByID);
        this.HelpMenu.add(this.GoOnHelpPage);
        this.HelpMenu.add(this.ContactWithSupport);
        this.HelpMenu.add(this.About);
        this.Etc.add(this.SendEmail);
        this.Etc.add(this.Configs);
        this.MainMenuBar.add(SystemMenu);
        this.MainMenuBar.add(Personel);
        this.MainMenuBar.add(Students);
        this.MainMenuBar.add(Lessons);
        this.MainMenuBar.add(Semester);
        this.MainMenuBar.add(this.Etc);
        this.MainMenuBar.add(HelpMenu);

        this.setJMenuBar(this.MainMenuBar);
    }
    public void PseudoUpdate(){
        this.setSize(this.getWidth()-1,this.getHeight());//TODO ρωτα μελε γιατι συμβαινει αυτο!
        this.setSize(this.getWidth()+1,this.getHeight());
        this.setContentPane(this.getContentPane());
    }
    private void setupListeners(){
        this.ViewAllTeachers.removeActionListener(this.ViewAllTeachers.getActionListeners()[0]);
        this.ViewAllTeachers.addActionListener(new ViewAllTeachersHandler(this));
        this.AddTeacher.removeActionListener(this.AddTeacher.getActionListeners()[0]);
        this.AddTeacher.addActionListener(new AddTeacherHandler(this));
        this.SearchByName.removeActionListener(this.SearchByLesson.getActionListeners()[0]);
        this.SearchByName.addActionListener(new SearchTeacherByNameHandler(this)); //HERE
        this.ViewAllStudents.removeActionListener(this.ViewAllStudents.getActionListeners()[0]);
        this.ViewAllStudents.addActionListener(new ViewAllStudentsHandler(this));
        this.AddStudent.removeActionListener(this.AddStudent.getActionListeners()[0]);
        this.AddStudent.addActionListener(new AddStudentHandler(this));
        this.SearchStudByName.removeActionListener(this.SearchStudByName.getActionListeners()[0]);
        this.SearchStudByName.addActionListener(new SearchStudentByNameHandler(this));
        this.ViewAllLessons.removeActionListener(this.ViewAllLessons.getActionListeners()[0]);
        this.ViewAllLessons.addActionListener(new ViewAllLessonsHandler(this));
        this.SearchLesByName.removeActionListener(this.SearchLesByName.getActionListeners()[0]);
        this.SearchLesByName.addActionListener(new SearchLessonByName(this));
        this.AddLesson.removeActionListener(this.AddLesson.getActionListeners()[0]);
        this.AddLesson.addActionListener(new AddLessonHandler(this));
        this.SearchStudByID.removeActionListener(this.SearchStudByID.getActionListeners()[0]);
        this.SearchStudByID.addActionListener(new SearchStudByID(this));
        this.SearchLesByID.removeActionListener(this.SearchLesByID.getActionListeners()[0]);
        this.SearchLesByID.addActionListener(new SearchLessonByIDHandler(this));
        this.ViewAllSemesters.removeActionListener(this.ViewAllSemesters.getActionListeners()[0]);
        this.ViewAllSemesters.addActionListener(new ViewAllSemestersHandler(this));
        this.AddNewSemester.removeActionListener(this.AddNewSemester.getActionListeners()[0]);
        this.AddNewSemester.addActionListener(new AddNewSemesterHandler(this));
        this.MassStudEmail.removeActionListener(this.MassStudEmail.getActionListeners()[0]);
        this.MassStudEmail.addActionListener(new MassEmailHandler(MassEmailHandler.STUDENT_MASS,this));
        this.MassTeaEmail.removeActionListener(this.MassTeaEmail.getActionListeners()[0]);
        this.MassTeaEmail.addActionListener(new MassEmailHandler(MassEmailHandler.TEACHERS_MASS,this));
        this.SendEmail.removeActionListener(this.SendEmail.getActionListeners()[0]);
        this.SendEmail.addActionListener((e)->{
            new EmailSenderDialog("", this).setVisible(true);
        });
        this.About.removeActionListener(this.About.getActionListeners()[0]);
        this.About.addActionListener((e)->{
            JOptionPane.showMessageDialog(this, "Μια απλή εφαρμογή διαχείρισης πανεπιστημιών γραμμένη σε Java (Με Apache poi και Swing)\nΑπο τον Στέφανο Στεφάνου (Έκδοση 0.9)","Περί του δημιουργού",JOptionPane.INFORMATION_MESSAGE);
        });
        
        
        this.Exit.addActionListener(new ExitHandler());
    }
    private void setupFontAndDefaultActions() {
        this.mainFont= FontLoaderBase.getFont();
        for (int i = 0; i < this.getComponentCount(); i++) {
            this.getComponent(i).setFont(this.mainFont);
        }
        JMenu tmp;
        for (int i = 0; i < this.getJMenuBar().getMenuCount(); i++) {
            this.getJMenuBar().getMenu(i).setFont(this.mainFont);
            for (int j = 0; j <this.getJMenuBar().getMenu(i).getMenuComponentCount() ; j++) {

                this.getJMenuBar().getMenu(i).getMenuComponent(j).setFont(this.mainFont);
                if((this.getJMenuBar().getMenu(i).getMenuComponent(j)instanceof JMenu)){
                    for (int k = 0; k < ((tmp =(JMenu) this.getJMenuBar().getMenu(i).getMenuComponent(j))).getMenuComponentCount(); k++) {
                        tmp.getMenuComponent(k).setFont(this.mainFont);
                        ((JMenuItem)tmp.getMenuComponent(k)).addActionListener(UnderDevelepomentAction.getInstance(this));
                    }
                }
                else{
                    ((JMenuItem)this.getJMenuBar().getMenu(i).getMenuComponent(j)).addActionListener(UnderDevelepomentAction.getInstance(this));
                }
            }

        }
    }
}
