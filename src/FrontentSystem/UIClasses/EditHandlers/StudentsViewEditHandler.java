package FrontentSystem.UIClasses.EditHandlers;

import BackEndSystem.Entities.Student;

/**
 * Created by StefStef on 6/11/2017.
 */
public class StudentsViewEditHandler extends AbstractEditHandler {

    @Override
    public void handleData(){
        Student st = Student.getStudentByID(Integer.valueOf((String) model.getValueAt(row,0)));
        if(this.ColName.equals("Όνομα")){
            System.out.println("[INFO_ON_PREVIOUS_INFO]Name Changed On Memory And Database for Student Selected");
            st.setName((String) this.model.getValueAt(row,col),true);
        }
        else{
            System.out.println("[INFO_ON_PREVIOUS_INFO]Surname Changed On Memory And Database for Student Selected");

            st.setSurname((String)this.model.getValueAt(row,col),true);
        }
    }

}
