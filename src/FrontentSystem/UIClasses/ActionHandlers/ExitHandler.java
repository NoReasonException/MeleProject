package FrontentSystem.UIClasses.ActionHandlers;

import BackEndSystem.Entities.DatabaseCommunications.Database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by stefstef on 5/6/2017.
 */
public class ExitHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        Database.getInstance().killConnection();
        System.exit(0);
    }
}
