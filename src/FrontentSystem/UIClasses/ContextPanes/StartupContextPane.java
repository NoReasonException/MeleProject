package FrontentSystem.UIClasses.ContextPanes;

import CommonSrc.FontLoaderBase;

import javax.swing.*;
import java.awt.*;

/**
 * Created by stefstef on 4/6/2017.
 */
public class StartupContextPane {
    private javax.swing.JFrame mainFrame;
    private java.awt.GridBagLayout mainGridBag=new java.awt.GridBagLayout();
    private java.awt.GridBagConstraints tempConfigs = new java.awt.GridBagConstraints();
    private java.awt.Container CenterSubContainer=new java.awt.Container();
    private javax.swing.JLabel MainMessage = new javax.swing.JLabel("Ώρα να πιάσουμε δουλεία! :) ");
    private javax.swing.JLabel SecondaryMessage = new javax.swing.JLabel("Επιλέξτε μια απο τις διαθέσιμες επιλογές για να ξεκινήσετε\n");


    public StartupContextPane(){
        this.mainFrame=new javax.swing.JFrame();
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.add(new JLabel(new ImageIcon("Res/Img/Startup.png")),BorderLayout.WEST);
        this.mainFrame.add(this.CenterSubContainer,BorderLayout.CENTER);
        this.CenterSubContainer.setLayout(this.mainGridBag);
        this.MainMessage.setFont(FontLoaderBase.getFont());
        this.SecondaryMessage.setFont(FontLoaderBase.getFont());
        this.tempConfigs.gridwidth=GridBagConstraints.REMAINDER;
        this.mainGridBag.setConstraints(this.MainMessage,this.tempConfigs);

        this.CenterSubContainer.add(this.MainMessage);
        this.tempConfigs.gridwidth=GridBagConstraints.REMAINDER;
        this.tempConfigs.insets=new java.awt.Insets(0,0,0,20);
        this.mainGridBag.setConstraints(this.SecondaryMessage,this.tempConfigs);
        this.CenterSubContainer.add(this.SecondaryMessage);

    }
    public java.awt.Container getContextPane(){return this.mainFrame.getContentPane();}
}
