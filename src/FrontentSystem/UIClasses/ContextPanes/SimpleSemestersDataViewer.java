package FrontentSystem.UIClasses.ContextPanes;

import FrontentSystem.UIClasses.MainWindow;

/**
 * Created by StefStef on 6/12/2017.
 */
public class SimpleSemestersDataViewer extends SimpleDataViewer {
    public SimpleSemestersDataViewer(Object [][]dat,String []row,String Title){
        super(dat, row, Title);
    }
    @Override
    public javax.swing.ImageIcon getImageIcon(){
        return new javax.swing.ImageIcon("Res/Img/ViewAllSemesters.png");
    }

}
