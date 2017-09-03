package BackEndSystem.Entities;

import BackEndSystem.Entities.DatabaseCommunications.Database;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class Student extends Person implements Serializable {
    private static java.util.Collection<Student> students = new java.util.ArrayList<Student>();

    public static int StudentCount(){
        return Student.students.size();
    }
    public static Student getStudentAt(int i){
        return ((ArrayList<Student>)Student.students).get(i);
    }
    public Student(String _Name,String _Surname,java.util.Date DateOfBirth) {
        super(_Name,_Surname, DateOfBirth);
        Student.students.add(this);
    }
    public static int CreateStudentToDB(java.lang.String Name,java.lang.String Surname,java.lang.String DateOfBirth){
        Student n;
        int NewPersonID = Person.createPersonToDB(Name,Surname,DateOfBirth);
        try{
            Database.getInstance().getConnection().createStatement().execute("INSERT INTO Students (StPerID) VALUES ("+String.valueOf(NewPersonID)+")");
            n=new Student(Name,Surname,Database.StringToDate(DateOfBirth));
            n.setID(NewPersonID);
            return NewPersonID;

        }catch (SQLException e){
            System.out.print("[ERR]Student with id"+String.valueOf(NewPersonID)+"Could Not Be Saved due to "+e.getMessage());
            return -1;
        }
    }
    public static Object[] searchStudentByName(java.lang.String Name){
        ArrayList<Student> returnVector = new ArrayList<>();
        Iterator<Student> iter = Student.getStudents();
        Student tmp;
        while(iter.hasNext()){
            if((tmp=iter.next()).getName().equals(Name) || tmp.getName().contains(Name)){
                System.out.println("\t[INFO_ON_PREVIOUS_INFO]Found "+tmp);
                returnVector.add(tmp);
            }
        }

        return returnVector.toArray();
    }
    public static Object[] searchStudentBySurname(java.lang.String Surname){
        ArrayList<Student> returnVector = new ArrayList<>();
        Iterator<Student> iter = Student.getStudents();
        Student tmp;
        while(iter.hasNext()){
            if((tmp=iter.next()).getSurname().equals(Surname) || tmp.getSurname().contains(Surname)){
                System.out.println("\t[INFO_ON_PREVIOUS_INFO]Found "+tmp);
                returnVector.add(tmp);
            }
        }

        return returnVector.toArray();
    }
    public static Object[] searchStudentByNameAndSurname(java.lang.String Name,java.lang.String Surname){
        ArrayList<Student> returnVector = new ArrayList<>();
        Iterator<Student> iter = Student.getStudents();
        Student tmp;
        while(iter.hasNext()){
            if(((tmp=iter.next()).getName().equals(Name) && tmp.getSurname().equals(Surname)||(tmp.getName().contains(Name) && tmp.getSurname().contains(Surname)))){
                System.out.println("\t[INFO_ON_PREVIOUS_INFO]Found "+tmp);
                returnVector.add(tmp);
            }
        }
        return returnVector.toArray();
    }
    public static Iterator<Student>getStudents(){
        return students.iterator();

    }
    public static Student getStudentByID(int ID){
        java.util.Iterator<Student> iter=Student.getStudents();
        while(iter.hasNext()){
            Student returnPersonTempObject = iter.next();
            if(returnPersonTempObject.getID()==ID)return returnPersonTempObject;
        }
        throw new IllegalArgumentException();
    }


}

