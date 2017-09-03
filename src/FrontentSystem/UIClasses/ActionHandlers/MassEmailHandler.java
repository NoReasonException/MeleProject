/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontentSystem.UIClasses.ActionHandlers;

import FrontentSystem.UIClasses.MainWindow;
import FrontentSystem.UIClasses.SmallDialogs.MassStudentEmailSender;
import FrontentSystem.UIClasses.SmallDialogs.MassTeacherEmailSender;

/**
 *
 * @author StefStef
 */
public class MassEmailHandler implements java.awt.event.ActionListener{
    public static final int STUDENT_MASS=1;
    public static final int TEACHERS_MASS=2;
    public boolean isStudent=false;
    MainWindow parentCaller =null;
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e){
        System.out.println("[]Mass Send Activated for all "+(this.isStudent?"Students":"Teachers"));
        if(isStudent){
            new MassStudentEmailSender(parentCaller).setVisible(true);
           
        }
        else{
            new MassTeacherEmailSender(parentCaller).setVisible(true);
            
        }
    }

    public MassEmailHandler(int mode , MainWindow parentCaller) {
        this.isStudent=(mode==MassEmailHandler.STUDENT_MASS);
        this.parentCaller=parentCaller;
    }
    
    
    
}
