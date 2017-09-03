package FrontentSystem.UIClasses.ContextPanes;

import FrontentSystem.UIClasses.EditHandlers.StudentsViewEditHandler;
import FrontentSystem.UIClasses.ListSelectionListeners.AdditionalInfoActivateBoxListener;

/**
 * Created by StefStef on 6/12/2017.
 */
public class SimpleStudentDataViewer extends SimpleDataViewer {
    public SimpleStudentDataViewer(Object[][]data,String titles[],String Title){
        super(data,titles,Title);
    }
    @Override
    public javax.swing.event.ListSelectionListener getListSelectionListener() {
        return new AdditionalInfoActivateBoxListener(this.mainTable, "Πληροφορίες Μαθητή", "Μαθητής");
    }

    @Override
    public javax.swing.event.TableModelListener getTableModelListener() {
        return new StudentsViewEditHandler();
    }
}