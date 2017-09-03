package FrontentSystem.UIClasses.ActionHandlers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by stefstef on 4/6/2017.
 */
public class UnderDevelepomentAction implements ActionListener {
    private Component parent=null;
    private static UnderDevelepomentAction toMe=null;
    private static java.awt.Component toFather=null;
    @Override
    public void actionPerformed(ActionEvent e){
        int ans=JOptionPane.showConfirmDialog(this.parent,"Η επιλογή αυτή ακομά δεν υποστηρίζεται στην παρούσα έκδοση\nΘέλετε να ελένξετε για ενημερώσεις!?","Μη υποστηριζόμενη λειτουργία",JOptionPane.YES_NO_OPTION);

    }
    private UnderDevelepomentAction(Component parent){
        this.parent=parent;
    }
    public static UnderDevelepomentAction getInstance(Component parent){
        if(UnderDevelepomentAction.toMe==null || UnderDevelepomentAction.toFather!=parent){
            UnderDevelepomentAction.toMe=new UnderDevelepomentAction(parent);
            UnderDevelepomentAction.toFather=parent;
        }
        return UnderDevelepomentAction.toMe;
    }
}
