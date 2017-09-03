
import ApachePoi.ToXML;
import BackEndSystem.Entities.Email.SendEmailMain;
import BackEndSystem.Entities.Loader.MainLoader;
import FrontentSystem.UIClasses.SmallDialogs.EmailSenderDialog;
import java.io.*;
import java.sql.SQLException;
import javax.mail.*;
import javax.activation.*;
import org.apache.poi.*;
public class Main {
    public static void main(String args[])throws IOException,SQLException,ClassNotFoundException{
        if(MainLoader.LoadObjectsToMemory(true)) {
            FrontentSystem.Loader.MainLoader.Start(true);
            //System.out.print(Lesson.createLessonToDB(Database.getInstance().getConnection(),"TestLesson"));
        }
        

    }
}

