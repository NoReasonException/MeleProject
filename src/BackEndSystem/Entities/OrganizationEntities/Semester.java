package BackEndSystem.Entities.OrganizationEntities;

import BackEndSystem.Entities.DatabaseCommunications.Database;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;

/**
 * Created by stefstef on 4/6/2017.
 */
public class Semester implements Serializable,Iterable<Semester> {
    private static java.util.Collection<Semester> Semesters = new java.util.LinkedList<Semester>();
    private java.util.Date BeginDate;
    private java.util.Date EndDate;
    private int SemesterID;
    public java.util.Date getBeginDate(){
        return (java.util.Date)BeginDate.clone();
    }
    public java.util.Date getEndDate(){
        return (java.util.Date)this.EndDate.clone();
    }
    public int getSemesterID(){return this.SemesterID;}
    public Semester(int ID,java.util.Date Begin,java.util.Date End){
        this.BeginDate=(java.util.Date)Begin.clone();
        this.EndDate=(java.util.Date)End.clone();
        this.SemesterID=ID;
        Semester.Semesters.add(this);

    }
    public static int createSemesterToDB(java.util.Date beginDate,java.util.Date endDate){
        try{
            Connection conn = Database.getInstance().getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement("INSERT INTO Semester (SemBegin, SemEnd) VALUES (?,?)");
            preparedStatement.setDate(1,Database.fromUtillDate(beginDate));
            preparedStatement.setDate(2,Database.fromUtillDate(endDate));
            preparedStatement.execute();
            ResultSet set=conn.createStatement().executeQuery("SELECT LAST_INSERT_ID()");set.next();
            System.out.println("[ERR]Semester  saved to Database");

            return set.getInt("LAST_INSERT_ID()");
        }catch (SQLException err){
            System.out.println("[ERR]Semester Could not been saved due to unnown error "+err.getMessage());
            return -1;
        }
    }
    public static Object[][] to2DRowRepresentationArray(Object []Semesters){
        Object [][] returnArray = new Object[Semesters.length][3];
        for (int i = 0; i <Semesters.length; i++) {
            returnArray[i][0]=String.valueOf(((Semester)Semesters[i]).getSemesterID());
            returnArray[i][1]=String.valueOf(((Semester)Semesters[i]).getBeginDate());
            returnArray[i][2]=String.valueOf(((Semester)Semesters[i]).getEndDate());

        }
        return returnArray;
    }
    public static Object[] getAllSemesters(){
        return Semester.Semesters.toArray();
    }
    @Override
    public java.util.Iterator<Semester> iterator(){
        return Semester.Semesters.iterator();
    }
}
