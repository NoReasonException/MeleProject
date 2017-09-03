package BackEndSystem.Entities;

import BackEndSystem.Entities.DatabaseCommunications.Database;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;


public class Teacher extends Person implements Serializable{
    private static java.util.Collection<Teacher> teachers = new java.util.ArrayList<Teacher>();
    public static int TeacherCount(){
        return Teacher.teachers.size();
    }
    public static Teacher getTeacherAt(int i){
        return ((ArrayList<Teacher>)Teacher.teachers).get(i);
    }
    public Teacher(String _Name,String _Surname,java.util.Date DateOfBirth) {
        super(_Name,_Surname, DateOfBirth);
        Teacher.teachers.add(this);
    }
    public static int CreateTeacherToDB(java.lang.String Name,java.lang.String Surname,java.lang.String DateOfBirth){
        int NewPersonID = Person.createPersonToDB(Name,Surname,DateOfBirth);
        try{
            Database.getInstance().getConnection().createStatement().execute("INSERT INTO Teacher (TeaPerID) VALUES ("+String.valueOf(NewPersonID)+")");
            new Teacher(Name,Surname,Database.StringToDate(DateOfBirth)).setID(NewPersonID);
            return NewPersonID;

        }catch (SQLException e){
            System.out.print("[ERR]Teacher with id"+String.valueOf(NewPersonID)+"Could Not Be Saved due to "+e.getMessage());
            return -1;
        }
    }
    public static Object[] searchTeacherByName(java.lang.String Name){
        ArrayList<Person> returnVector = new ArrayList<>();
        Iterator<Teacher> iter = Teacher.getTeachers();
        Teacher tmp;
        while(iter.hasNext()){
            if((tmp=iter.next()).getName().equals(Name) || tmp.getName().contains(Name)){
                System.out.println("\t[INFO_ON_PREVIOUS_INFO]Found "+tmp);
                returnVector.add(tmp);
            }
        }

        return returnVector.toArray();
    }
    public static Object[] searchTeacherBySurname(java.lang.String Surname){
        ArrayList<Teacher> returnVector = new ArrayList<>();
            Iterator<Teacher> iter = Teacher.getTeachers();
        Teacher tmp;
        while(iter.hasNext()){
            if((tmp=iter.next()).getSurname().equals(Surname) || tmp.getSurname().contains(Surname)){
                System.out.println("\t[INFO_ON_PREVIOUS_INFO]Found "+tmp);
                returnVector.add(tmp);
            }
        }

        return returnVector.toArray();
    }
    public static Object[] searchTeacherByNameAndSurname(java.lang.String Name,java.lang.String Surname){
        ArrayList<Teacher> returnVector = new ArrayList<>();
        Iterator<Teacher> iter = Teacher.getTeachers();
        Teacher tmp;
        while(iter.hasNext()){
            if(((tmp=iter.next()).getName().equals(Name) && tmp.getSurname().equals(Surname)||(tmp.getName().contains(Name) && tmp.getSurname().contains(Surname)))){
                returnVector.add(tmp);
            }
        }
        return returnVector.toArray();
    }
    public static Iterator<Teacher> getTeachers(){
        return teachers.iterator();
    }

}
