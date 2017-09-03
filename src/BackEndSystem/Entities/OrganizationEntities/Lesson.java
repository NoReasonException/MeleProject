package BackEndSystem.Entities.OrganizationEntities;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by stefstef on 4/6/2017.
 */
public class Lesson implements Serializable{
    private static java.util.Collection<Lesson> Lessons =new java.util.LinkedList<Lesson>();
    private int LessonID;
    private java.lang.String LessonName;
    public java.lang.String getLessonName(){
        return this.LessonName;
    }
    public int getLessonID(){
        return this.LessonID;
    }
    public Lesson(int LessonID,java.lang.String LessonName){
        this.LessonID=LessonID;
        this.LessonName=LessonName;
        Lesson.Lessons.add(this);
    }
    public static int createLessonToDB(Connection conn,java.lang.String Name){
        try{
            conn.createStatement().execute("INSERT INTO Lessons (LesName) VALUE ('"+Name+"');");
            ResultSet res= conn.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
            res.next();
            new Lesson(res.getInt("LAST_INSERT_ID()"),Name);
            return res.getInt("LAST_INSERT_ID()");

        }catch (SQLException e){
            System.out.print(e.getMessage());
            return -1;
        }

    }
    public static java.util.Iterator<Lesson> getLessons(){
        return Lesson.Lessons.iterator();
    }
    public static Object[] getAllLessons(){
        return Lesson.Lessons.toArray();
    }
    public static Object[] searchLessonsByName(java.lang.String Name){
        ArrayList<Object> returnArray = new ArrayList<>();
        Iterator<Lesson> itr = Lesson.getLessons();
        Lesson tmp;
        while(itr.hasNext()){
            if((tmp=itr.next()).getLessonName().equals(Name) || tmp.getLessonName().contains(Name)){
                returnArray.add(tmp);
            }
        }
        return returnArray.toArray();
    }
    public static Lesson getLessonByID(int  ID){
        Iterator<Lesson> itr = Lesson.getLessons();
        Lesson tmp;
        while(itr.hasNext()){
            if((tmp=itr.next()).getLessonID()==ID){
                return tmp;
            }
        }
        throw new IllegalArgumentException();

    }
    public static Object[][] to2DRowRepresentationArray(Object []Lessons){
        Object [][] returnArray = new Object[Lessons.length][2];
        for (int i = 0; i <Lessons.length; i++) {
            returnArray[i][0]=((Lesson)Lessons[i]).getLessonID();
            returnArray[i][1]=((Lesson)Lessons[i]).getLessonName();
        }
        return returnArray;
    }

}
