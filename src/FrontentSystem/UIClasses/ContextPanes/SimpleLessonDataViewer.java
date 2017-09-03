package FrontentSystem.UIClasses.ContextPanes;

import FrontentSystem.UIClasses.TableModels.SimpleLessonViewerDataModel;

/**
 * Created by StefStef on 6/12/2017.
 */
public class SimpleLessonDataViewer extends SimpleDataViewer {

    public SimpleLessonDataViewer(Object[][]data,String Titles[],String Title){
        super(data,Titles,Title);
    }
    @Override
    public javax.swing.table.AbstractTableModel getTableModel(Object [][]dat,String []row){
        return new SimpleLessonViewerDataModel(dat,row);
    }
}
