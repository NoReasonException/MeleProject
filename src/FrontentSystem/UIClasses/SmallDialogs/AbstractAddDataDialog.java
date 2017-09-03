package FrontentSystem.UIClasses.SmallDialogs;

import BackEndSystem.Entities.Person;
import CommonSrc.FontLoaderBase;
import jdk.nashorn.internal.scripts.JD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by stefstef on 7/6/2017.
 */
abstract public class AbstractAddDataDialog extends JDialog {
    private java.awt.BorderLayout mainLayout = new java.awt.BorderLayout();
    private java.awt.Container centerContainer = new java.awt.Container();
    private java.awt.GridBagLayout gridBagLayout = new java.awt.GridBagLayout();
    private java.awt.GridBagConstraints centerContainerLayoutConfig = new java.awt.GridBagConstraints();
    protected javax.swing.JTextField addDataField = new javax.swing.JTextField(15);
    private java.awt.Container buttonsContainer = new java.awt.Container();
    private java.awt.BorderLayout buttonsContainerLayout = new java.awt.BorderLayout();
    private javax.swing.JButton OkButton=new JButton("Εντάξει");
    private javax.swing.JButton ReturnButton=new JButton("Επιστροφή");
    private javax.swing.JLabel titleLabel ;
    protected ViewAdditionalDataDialog parentFrame;
    AbstractAddDataDialog(ViewAdditionalDataDialog parentFrame){
        this.titleLabel= new JLabel(this.getCentralTitle());
        this.setLocationRelativeTo(this.parentFrame=parentFrame);
        this.setSize(400,300);
        this.setTitle(this.MygetTitle());
        this.setLayout(this.mainLayout);
        this.add(new JLabel(this.getNorthImageIcon()), BorderLayout.WEST);
        this.createCenterContainer();
        this.add(this.centerContainer);
        this.createButtonsContainer();
        this.add(this.buttonsContainer,BorderLayout.SOUTH);
        this.setVisible(true);

    }
    private void createCenterContainer(){
        this.centerContainer.setLayout(this.gridBagLayout);
        this.centerContainerLayoutConfig.gridwidth = GridBagConstraints.REMAINDER;
        this.gridBagLayout.setConstraints(this.titleLabel,this.centerContainerLayoutConfig);
        this.centerContainer.add(this.titleLabel);
        this.centerContainerLayoutConfig.insets=new java.awt.Insets(10,1,1,5);
        this.gridBagLayout.setConstraints(this.addDataField,this.centerContainerLayoutConfig);
        this.centerContainer.add(this.addDataField);

        this.titleLabel.setFont(FontLoaderBase.getFont());
    }
    private void createButtonsContainer(){
        this.buttonsContainer.setLayout(this.buttonsContainerLayout);
        this.buttonsContainer.add(this.ReturnButton,BorderLayout.WEST);
        this.buttonsContainer.add(this.OkButton,BorderLayout.EAST);
        this.ReturnButton.addActionListener(new ActionListener() {
            private JDialog parentCaller=null;
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parentCaller.setVisible(false);
                parentCaller.dispose();
            }
            public ActionListener getInstance(JDialog parentCaller){
                this.parentCaller=parentCaller;
                return this;
            }
        }.getInstance(this));
        this.OkButton.addActionListener(this.getOkayButtonActionListener());
    }
    abstract protected java.lang.String MygetTitle();
    abstract protected java.lang.String getCentralTitle();
    abstract protected javax.swing.ImageIcon getNorthImageIcon();
    abstract protected java.awt.event.ActionListener getOkayButtonActionListener();
}
