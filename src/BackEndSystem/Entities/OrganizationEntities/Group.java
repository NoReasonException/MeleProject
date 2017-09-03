package BackEndSystem.Entities.OrganizationEntities;

import BackEndSystem.Entities.Student;
import BackEndSystem.Entities.Teacher;

/**
 * Created by stefstef on 4/6/2017.
 */
public class Group {

    private Lesson lesson ;
    private Semester semester;
    private java.util.Collection<Teacher> teachers;
    private java.util.Collection<Student> student;

    public Group(Lesson lesson,Semester semester){
        this.lesson=lesson;
        this.semester=semester;
    }
    public Student addStudentEnrolled(Student Stud){
        this.student.add(Stud);
        return Stud;
    }
    public Teacher addTeacher(Teacher teacher){
        this.teachers.add(teacher);
        return teacher;
    }
    public java.util.Iterator<Teacher>getTeachers(){return this.teachers.iterator();}
    public java.util.Iterator<Student>getStudents(){return this.student.iterator();}
    public Lesson getLesson(){return this.lesson;}
    public Semester getSemester(){return this.semester;}
}
