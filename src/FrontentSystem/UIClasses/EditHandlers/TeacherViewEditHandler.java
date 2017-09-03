package FrontentSystem.UIClasses.EditHandlers;

import BackEndSystem.Entities.DatabaseCommunications.Database;
import BackEndSystem.Entities.Teacher;
import FrontentSystem.UIClasses.ActionHandlers.ViewAllTeachersHandler;

/**
 * Created by stefstef on 4/6/2017.
 */
public class TeacherViewEditHandler extends AbstractEditHandler {
    @Override
    public void handleData(){
        System.out.println("[INFO]Search Teacher by Selection...");
        Teacher tmp;
        try{
            tmp=Teacher.getTeacherAt(this.row);//δουλευει απλα κατα τυχη ,τσεκαρε το //TODO DOOO IT !!

        }catch (java.lang.IndexOutOfBoundsException e){
            System.out.println("\t[ERR]Teacher Not Found...break operation");
            return;
        }
        if(ColName.equals("Όνομα")){
            tmp.setName(this.model.getValueAt(row,col).toString(),true);
        }
        else if(ColName.equals("Επίθετο")){
            tmp.setSurname(this.model.getValueAt(row,col).toString(),true);
        }



    }
}
