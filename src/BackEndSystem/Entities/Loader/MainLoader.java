package BackEndSystem.Entities.Loader;

import BackEndSystem.Entities.DatabaseCommunications.Database;
import BackEndSystem.Entities.OrganizationEntities.Lesson;
import BackEndSystem.Entities.OrganizationEntities.Semester;
import BackEndSystem.Entities.Student;
import BackEndSystem.Entities.Teacher;
import sun.applet.Main;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by stefstef on 4/6/2017.
 */
public class MainLoader {
    private static java.sql.Connection MainConnection;
    private static javax.swing.ImageIcon toImageIcon(byte[]arr){
        ByteArrayInputStream ByteInp = new ByteArrayInputStream(arr);
        Object obj=null;
        try{
            ObjectInputStream inp = new ObjectInputStream(ByteInp);
            obj=inp.readObject();
            inp.close();
            return (javax.swing.ImageIcon)obj;
        }catch (Exception e){
            System.out.println("\t\t\t\t\t->[ERR]When De-Serialize the ImageIcon....");
            return null;
        }

    }
    public static java.util.Stack<String>AdditionalInfoLoader(int myID,java.lang.String query,java.lang.String fieldName){
        java.util.Stack<String> returnArray=new java.util.Stack<String>();
        try{
            java.sql.PreparedStatement phoneQuery =MainLoader.MainConnection.prepareStatement(query);
            phoneQuery.setInt(1,myID);
            java.sql.ResultSet resultSet=phoneQuery.executeQuery();
            while(resultSet.next()){
                returnArray.push(resultSet.getString(fieldName));
            }
            if(returnArray.empty())return null;
            return returnArray;



        }catch (SQLException e){
            return null;
        }

    }

    public static void loadTeachers(boolean debug)throws SQLException{
        if(debug)System.out.println("[INFO]Attemting to Load Teachers...");
        java.util.Stack<String> Phones=null;
        java.util.Stack<String> Emails=null;
        java.lang.String tempString;
        java.sql.ResultSet resultSet = MainLoader.MainConnection.createStatement().executeQuery("SELECT * FROM Teacher");

        while(resultSet.next()){
            java.sql.ResultSet resPerTeacher = MainLoader.MainConnection.createStatement().executeQuery("Select * FROM Person WHERE PerID="+resultSet.getInt("TeaPerID"));

            if(!resPerTeacher.next()){
                if(debug)System.out.println("[FAT_ERR]Error at loadTeachers,no Teacher Found");
                continue;
                //throw new SQLException();
            }
            byte[]arr = resPerTeacher.getBytes("ImageSrc");
            boolean hasIcon=false;
            Teacher tmp;
            if(resPerTeacher.wasNull()){
                tmp=new Teacher(resPerTeacher.getString("PerName"),resPerTeacher.getString("PerSurname"),resPerTeacher.getDate("PerBirth"));
            }
            else{
                hasIcon=true;
                (tmp=new Teacher(resPerTeacher.getString("PerName"),resPerTeacher.getString("PerSurname"),resPerTeacher.getDate("PerBirth"))).setSelfPicture(MainLoader.toImageIcon(arr));

            }
            tmp.setID(Integer.valueOf(resPerTeacher.getString("PerID")));
            if(debug){
                System.out.print("\t-->Teacher "+resPerTeacher.getString("PerName")+" "+resPerTeacher.getString("PerSurname")+" "+resPerTeacher.getDate("PerBirth").toString());
                if(!hasIcon){
                    System.out.print(" NO_ICON\n");
                }
                else{
                    System.out.print(" WITH_ICON\n");
                }
            }
            Phones = MainLoader.AdditionalInfoLoader(tmp.getID(),"SELECT PhoPhone from Phones WHERE PhoPerID=?","PhoPhone");
            Emails = MainLoader.AdditionalInfoLoader(tmp.getID(),"SELECT EmEmail from Emails WHERE EmPerID=?","EmEmail");
            if(Phones!=null){
                while(!Phones.empty()){
                    tmp.addPhone((tempString=Phones.pop()));
                    System.out.println("\t\t\tPhoneNumber -> "+tempString);
                }
            }
            if(Emails!=null){
                while(!Emails.empty()){
                    tmp.addEmail((tempString=Emails.pop()));
                    System.out.println("\t\t\tEmails -> "+tempString);
                }
            }


        }
    }
    public static void loadStudents(boolean debug)throws SQLException{
        if(debug)System.out.println("[INFO]Attemting to Load Students...");
        java.util.Stack<String> Phones=null;
        java.util.Stack<String> Emails=null;
        java.lang.String tempString;
        java.sql.ResultSet resultSet = MainLoader.MainConnection.createStatement().executeQuery("SELECT * FROM Students");

        while(resultSet.next()){
            java.sql.ResultSet resPerStudent = MainLoader.MainConnection.createStatement().executeQuery("Select * FROM Person WHERE PerID="+resultSet.getInt("StPerID"));

            if(!resPerStudent.next()){
                if(debug)System.out.println("[FAT_ERR]Error at loadStudents,no Student Found");
                continue;
                //throw new SQLException();
            }
            byte[]arr = resPerStudent.getBytes("ImageSrc");
            boolean hasIcon=false;
            Student tmp;
            if(resPerStudent.wasNull()){
                tmp=new Student(resPerStudent.getString("PerName"),resPerStudent.getString("PerSurname"),resPerStudent.getDate("PerBirth"));
            }
            else{
                hasIcon=true;
                (tmp=new Student(resPerStudent.getString("PerName"),resPerStudent.getString("PerSurname"),resPerStudent.getDate("PerBirth"))).setSelfPicture(MainLoader.toImageIcon(arr));

            }
            tmp.setID(Integer.valueOf(resPerStudent.getString("PerID")));
            if(debug){
                System.out.print("\t-->Student "+resPerStudent.getString("PerName")+" "+resPerStudent.getString("PerSurname")+" "+resPerStudent.getDate("PerBirth").toString());
                if(!hasIcon){
                    System.out.print(" NO_ICON\n");
                }
                else{
                    System.out.print(" WITH_ICON\n");
                }
            }
            Phones = MainLoader.AdditionalInfoLoader(tmp.getID(),"SELECT PhoPhone from Phones WHERE PhoPerID=?","PhoPhone");
            Emails = MainLoader.AdditionalInfoLoader(tmp.getID(),"SELECT EmEmail from Emails WHERE EmPerID=?","EmEmail");
            if(Phones!=null){
                while(!Phones.empty()){
                    tmp.addPhone((tempString=Phones.pop()));
                    System.out.println("\t\t\tPhoneNumber -> "+tempString);
                }
            }
            if(Emails!=null){
                while(!Emails.empty()){
                    tmp.addEmail((tempString=Emails.pop()));
                    System.out.println("\t\t\tEmails -> "+tempString);
                }
            }


        }
    }
    public static void loadLessons(boolean debug) throws SQLException{
        if(debug)System.out.println("[INFO]Attemting to load Lessons...");
        java.sql.ResultSet resultSet = MainLoader.MainConnection.createStatement().executeQuery("SELECT * FROM Lessons");
        while(resultSet.next()){
            new Lesson(resultSet.getInt("LesID"),resultSet.getString("LesName"));
            if(debug)System.out.println("\t-->Lesson "+resultSet.getInt("LesID")+" "+resultSet.getString("LesName"));


        }
    }
    public static void loadSemesters(boolean debug) throws SQLException{
        if(debug)System.out.println("[INFO]Attemting to load Semesters...");
        java.sql.ResultSet resultSet = MainLoader.MainConnection.createStatement().executeQuery("SELECT * FROM Semester");
        while(resultSet.next()){
            new Semester(resultSet.getInt("SemID"),resultSet.getDate("SemBegin"),resultSet.getDate("SemEnd"));
            if(debug)System.out.println("\t-->Semester "+resultSet.getDate("SemBegin").toString()+" "+resultSet.getDate("SemEnd").toString());


        }
    }
    public static boolean LoadObjectsToMemory(boolean debug){
        BackEndSystem.Entities.Loader.BackendUILoaderWindow.MainWindow mainWindow = new BackEndSystem.Entities.Loader.BackendUILoaderWindow.MainWindow(8);
        mainWindow.AddLog("Επικοινωνία με βάση δεδομένων...");
        if(debug)System.out.println("[INFO]Attemting to Connect With Database ");
        MainLoader.MainConnection=Database.getInstance().getConnection();
        if(!Database.getInstance().isOkay()){
            JOptionPane.showMessageDialog(null,"Η Σύνδεση στην βάση δεδομένων ήταν αδύνατη,Επικοινωνήστε με την υποστήριξη για περαιτέρω πληροφορίες\nΚωδικός λάθους (ERR_CONN_COLPSD)","Κρίσιμο Σφάλμα",JOptionPane.ERROR_MESSAGE);

            mainWindow.setVisible(false);
            mainWindow=null;
            System.exit(0);
            return false;
        }
        if(debug)System.out.println("[INFO]Server Responded in "+new java.util.Date().toString());
        mainWindow.AddLog("Επικοινωνία Επιτυχής....");

        try {
            mainWindow.AddLog("Φόρτωση Καθηγητών...");
            MainLoader.loadTeachers(debug);
            //TimeUnit.SECONDS.sleep(1);
            mainWindow.AddLog("Φόρτωση Μαθητών...");
            MainLoader.loadStudents(debug);
           // TimeUnit.SECONDS.sleep(1);

            mainWindow.AddLog("Φόρτωση Μαθημάτων...");

            MainLoader.loadLessons(debug);
          //  TimeUnit.SECONDS.sleep(1);
            mainWindow.AddLog("Φόρτωση Λοιπών Δεδομένων...");
            MainLoader.loadSemesters(debug);
            mainWindow.AddLog("Εκκίνηση Προγράμματος...");
           // TimeUnit.SECONDS.sleep(1);
            mainWindow.setVisible(false);
            mainWindow=null;
            if(debug)System.out.println("[INFO]Load Complete,Activating UI System...");
            //MainLoader.MainConnection.close();


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Συνέβη κάποιο Απροσδιόριστο σφάλμα,Επικοινωνήστε με την υποστήριξη για περαιτέρω πληροφορίες\nΚωδικός λάθους(ERR_QRY_CLPSD)","Κρίσιμο Σφάλμα",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;

    }


}
