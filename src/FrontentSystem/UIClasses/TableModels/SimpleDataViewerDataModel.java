package FrontentSystem.UIClasses.TableModels;

import javax.swing.table.AbstractTableModel;

/**
 * Created by stefstef on 5/6/2017.
 */
public class SimpleDataViewerDataModel extends AbstractTableModel{
    protected String[] Titles;
    protected Object[][] Data;

    public int getColumnCount() {
        return Titles.length;
    }

    public int getRowCount() {
        return Data.length;
    }

    public String getColumnName(int col) {
        return Titles[col];
    }

    public Object getValueAt(int row, int col) {
        return Data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if(col==1 || col==2){return true;}
        return false;
    }
    public void setValueAt(Object value, int row, int col) {
        Data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    public SimpleDataViewerDataModel(Object [][]Data,String[]Titles){
        this.Data=Data;
        this.Titles=Titles;
        for (int i = 0; i < Data.length; i++) {
            for (int j = 0; j < Titles.length; j++) {
                this.setValueAt(Data[i][j],i,j);
            }
        }
    }
}
