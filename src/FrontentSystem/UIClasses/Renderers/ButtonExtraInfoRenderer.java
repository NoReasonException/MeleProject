package FrontentSystem.UIClasses.Renderers;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by stefstef on 6/6/2017.
 */
public class ButtonExtraInfoRenderer extends JButton implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table,Object Button,boolean isSelected,boolean hasFocus,int Row,int Column){
        return ((JButton)Button);
    }
}
