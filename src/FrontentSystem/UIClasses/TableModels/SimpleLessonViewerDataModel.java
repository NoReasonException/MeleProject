package FrontentSystem.UIClasses.TableModels;

import FrontentSystem.UIClasses.ContextPanes.SimpleDataViewer;
import com.sun.org.apache.regexp.internal.RE;

import javax.swing.table.AbstractTableModel;

/**
 * Created by StefStef on 6/12/2017.
 */
public class SimpleLessonViewerDataModel extends SimpleDataViewerDataModel {
    public SimpleLessonViewerDataModel(Object [][]Data,String[]Titles){
        super(Data,Titles);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
