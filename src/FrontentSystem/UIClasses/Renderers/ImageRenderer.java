package FrontentSystem.UIClasses.Renderers;

import CommonSrc.FontLoaderBase;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by stefstef on 5/6/2017.
 */
public class ImageRenderer extends JButton implements TableCellRenderer {
    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
        this.setText("Εικόνα...");
        this.setFont(FontLoaderBase.getFont().deriveFont(Font.PLAIN,12));

        if(hasFocus){
            JFrame fr = new JFrame("Προβολέας εικόνων");
            fr.add(new JLabel((javax.swing.ImageIcon)(value)));
            fr.setLocationRelativeTo(null);
            fr.setSize(300,300);
            fr.setResizable(false);
            fr.setVisible(true);



        }
        return this;
    }
}
