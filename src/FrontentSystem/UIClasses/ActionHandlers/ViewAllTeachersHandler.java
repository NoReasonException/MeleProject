package FrontentSystem.UIClasses.ActionHandlers;

import BackEndSystem.Entities.Teacher;
import FrontentSystem.UIClasses.ContextPanes.SimpleDataViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

/**
 * Created by stefstef on 4/6/2017.
 */
public class ViewAllTeachersHandler implements ActionListener {
    JFrame mainFrame=null;
    @Override
    public void actionPerformed(ActionEvent e){
        java.lang.String []b=new java.lang.String[5];
        b[0]="Κωδικός";
        b[1]="Όνομα";
        b[2]="Επίθετο";
        b[3]="Ημερομηνία Γέννησης";
        b[4]="Λοιπές Πληροφορίες";
        java.lang.Object [][]n=new java.lang.Object[Teacher.TeacherCount()][5];
        Iterator<Teacher> iter = Teacher.getTeachers();
        Teacher tmp;
        int ind=0;
        while(iter.hasNext()){
            tmp=iter.next();
            n[ind][0]=String.valueOf(tmp.getID());
            n[ind][1]=tmp.getName();
            n[ind][2]=tmp.getSurname();
            n[ind][3]=tmp.getDateOfBirth().toString();
            n[ind][4]=new JButton("Λοιπές Πληροφορίες");
            ind+=1;
        }
        SimpleDataViewer f=new SimpleDataViewer(n,b,"Αποτελέσματα Αναζήτησης Καθηγητών");
        this.mainFrame.setContentPane(f.getContentPane());
    }
    public ViewAllTeachersHandler(JFrame parent){
        this.mainFrame=parent;
    }
}
