package BackEndSystem.Entities.Loader.BackendUILoaderWindow;

import CommonSrc.FontLoaderBase;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;

/**
 * Created by stefstef on 4/6/2017.
 */
public class MainWindow extends JFrame {
    private javax.swing.Icon img = new javax.swing.ImageIcon("src/professor.png");
    private java.awt.Container LogContainer = new java.awt.Container();
    private java.awt.Font mainFont = new Font(Font.SANS_SERIF,Font.PLAIN,15);
    private GridBagConstraints constraints = new GridBagConstraints();
    private GridBagLayout LogAreaLayout = new GridBagLayout();
    private JProgressBar ProgressBar ;
    private int Steps;
    private JLabel tmp;
    public MainWindow(int Steps){
        super("Εκκίνηση Συστήματος...");
        this.Steps=Steps;
        this.mainFont= FontLoaderBase.getFont();
        this.setLayout(new BorderLayout());
        this.setSize(600,350);
        this.setLocationRelativeTo(null);
        this.add(this.tmp=new JLabel(this.img,JLabel.LEFT),BorderLayout.WEST);
        this.tmp.setFont(this.mainFont);
        this.add(LogContainer,BorderLayout.CENTER);
        this.add(this.tmp=new JLabel("Εκκίνηση προγράμματος,Παρακαλώ αναμείνατε...",JLabel.CENTER),BorderLayout.NORTH);
        this.tmp.setFont(this.mainFont.deriveFont(Font.PLAIN,18));
        this.add(this.ProgressBar=new JProgressBar(1,this.Steps),BorderLayout.SOUTH);
        LogContainer.setLayout(this.LogAreaLayout);
        this.constraints.gridwidth=GridBagConstraints.REMAINDER;
        this.constraints.insets=new java.awt.Insets(2,1,20,10);
        this.setFont(this.mainFont);
        this.setResizable(false);
        this.setVisible(true);



    }
    public void AddLog(String log){
        JLabel lbl = new JLabel(log);
        lbl.setFont(this.mainFont);
        this.LogAreaLayout.setConstraints(lbl,this.constraints);
        this.LogContainer.add(lbl);
        this.ProgressBar.setValue(this.ProgressBar.getValue()+1);
        this.setContentPane(this.getContentPane());


    }

}
