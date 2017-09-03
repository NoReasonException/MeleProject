package FrontentSystem.UIClasses.EditHandlers;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 * Created by stefstef on 4/6/2017.
 */
public abstract class AbstractEditHandler implements TableModelListener {
    int row,col;
    TableModel model;
    String ColName;
    Object data;

    @Override
    public void tableChanged(TableModelEvent e){
        this.row=e.getFirstRow();
        this.col=e.getColumn();
        this.model = (TableModel)e.getSource();//get the TableModel, the whole table without the tableHead
        this.ColName=model.getColumnName(col);//get the col name who edit happened
        this.data = model.getValueAt(row,col); //get the data (String as Object)
        System.out.println("[INFO]Data Change Requested at collumn "+this.ColName +"("+row+"-"+col+") ");
        this.handleData();

    }
    public abstract void handleData();
}
