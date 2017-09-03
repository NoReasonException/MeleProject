package FrontentSystem.UIClasses.ActionHandlers;

import FrontentSystem.UIClasses.ContextPanes.AddTeacherContextPane;
import FrontentSystem.UIClasses.MainWindow;
import FrontentSystem.UIClasses.SmallDialogs.AbstractAddDataDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by stefstef on 10/6/2017.
 */
public class AddTeacherHandler implements ActionListener {
    MainWindow callerFrame =null;
    @Override
    public void actionPerformed(ActionEvent e) {
        AddTeacherContextPane tmp=new AddTeacherContextPane();
        this.callerFrame.setContentPane(tmp.getContentPane());
        this.callerFrame.PseudoUpdate();

    }
    public AddTeacherHandler(MainWindow callerFrame){
        this.callerFrame=callerFrame;
    }

}
