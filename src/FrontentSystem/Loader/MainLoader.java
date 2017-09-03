package FrontentSystem.Loader;

import FrontentSystem.UIClasses.MainWindow;

/**
 * Created by stefstef on 4/6/2017.
 */
public class MainLoader {
    public static void Start(boolean debug){
        if(debug)System.out.println("[INFO]Frontend Loader Takes control at "+new java.util.Date().toString());
        new MainWindow().setVisible(true);

    }
}
