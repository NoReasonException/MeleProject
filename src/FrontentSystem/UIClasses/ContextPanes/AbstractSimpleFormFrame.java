package FrontentSystem.UIClasses.ContextPanes;

import BackEndSystem.Entities.OrganizationEntities.Semester;
import CommonSrc.FontLoaderBase;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by stefstef on 9/6/2017.
 */
public class AbstractSimpleFormFrame extends JFrame {
    java.awt.BorderLayout                 mainLay = new java.awt.BorderLayout();
    java.awt.Container                    formContainer = new java.awt.Container();
    java.awt.GridBagLayout                formLayout    = new java.awt.GridBagLayout();
    java.awt.GridBagConstraints           formLayoutConfig    = new java.awt.GridBagConstraints();
    java.awt.Container                    formContainerDateComponent = new java.awt.Container();
    java.awt.FlowLayout                   formContainerDateComponentLayout = new java.awt.FlowLayout();
    java.awt.Container                    ButtonsContainer    =new java.awt.Container();
    java.awt.BorderLayout                 ButtonsContainerLayout = new java.awt.BorderLayout();
    javax.swing.JButton                   InsertDataButton;
    protected javax.swing.JLabel          Title, FirstLabel, SecondLabel,ThirdLabel;
    protected javax.swing.JTextField      FirstLabelTextField, SecondLabelTextField;
    javax.swing.JSpinner                  DateJSpinner;
    java.awt.Container                    DateTimePicker;
    public AbstractSimpleFormFrame(java.lang.String Title){
        this.add(this.Title=new JLabel(Title,JLabel.CENTER),BorderLayout.NORTH);
        this.Title.setFont(FontLoaderBase.getFont().deriveFont(Font.PLAIN,18));
        this.add(new JLabel(this.getAddDataImage()), BorderLayout.WEST);
        this.CreateForm();
        this.CreateButtonsContainer();
        this.CreateDateComponent();
        this.add(this.formContainer,BorderLayout.CENTER);
        this.add(this.ButtonsContainer,BorderLayout.SOUTH);
        this.InsertDataButton.addActionListener(this.getButtonsActionListener());
        this.AdditionalCustomization();
    }
    protected void AdditionalCustomization(){}
    protected javax.swing.ImageIcon getAddDataImage(){
        return new javax.swing.ImageIcon("Res/Img/AddPerson.png");
    }
    protected java.lang.String getFirstLabelText(){
        return "Όνομα";
    }
    protected java.lang.String getSecondLabelText(){
        return "Επίθετο";
    }
    protected java.lang.String getThirdLabelText(){
        return "Ημ/νια Γέννησης";
    }
    protected java.lang.String getButtonText(){
        return "Εισαγωγή";
    }
    protected java.awt.event.ActionListener getButtonsActionListener(){
        return (e)->{
            System.out.println("[INFO]User Activated Default Action Listener -> Please Override getButtonsActionListener()");
        };
    }
    protected void CreateForm(){
        this.formContainer.setLayout(this.formLayout);
        this.formLayout.setConstraints(FirstLabel =new JLabel(this.getFirstLabelText()),this.formLayoutConfig);
        this.formContainer.add(this.FirstLabel);
        this.formLayoutConfig.gridwidth=GridBagConstraints.REMAINDER;
        this.formLayoutConfig.insets=new java.awt.Insets(10,1,10,1);
        this.formLayout.setConstraints(FirstLabelTextField =new JTextField(18),this.formLayoutConfig);
        this.formContainer.add(this.FirstLabelTextField);

        this.formLayoutConfig.gridwidth=GridBagConstraints.RELATIVE;
        this.formLayout.setConstraints(SecondLabel =new JLabel(this.getSecondLabelText()),this.formLayoutConfig);
        this.formContainer.add(this.SecondLabel);
        this.formLayoutConfig.gridwidth=GridBagConstraints.REMAINDER;
        this.formLayout.setConstraints(SecondLabelTextField =new JTextField(18),this.formLayoutConfig);
        this.formContainer.add(this.SecondLabelTextField);

        this.formLayoutConfig.gridwidth=GridBagConstraints.RELATIVE;
        this.formLayout.setConstraints(this.ThirdLabel=new JLabel(this.getThirdLabelText()),this.formLayoutConfig);
        this.formContainer.add(this.ThirdLabel);
        this.formLayoutConfig.gridwidth=GridBagConstraints.REMAINDER;
        this.formLayout.setConstraints(this.formContainerDateComponent,this.formLayoutConfig);
        this.formContainer.add(this.formContainerDateComponent);

        this.FirstLabel.setFont(FontLoaderBase.getFont());
        this.SecondLabel.setFont(FontLoaderBase.getFont());
        this.ThirdLabel.setFont(FontLoaderBase.getFont());

        this.FirstLabelTextField.setFont(FontLoaderBase.getFont());
        this.SecondLabelTextField.setFont(FontLoaderBase.getFont());

        //this.formLayout.setConstraints(SecondLabel =new JLabel(this.getSecondLabelText()),this.formLayoutConfig);

    }
    public void CreateDateComponent(){
        this.formContainerDateComponent.setLayout(this.formContainerDateComponentLayout);
        this.formContainerDateComponent.add(this.DateJSpinner=new JSpinner(new SpinnerDateModel(new Date(),null,null, Calendar.ERA)));
        this.DateJSpinner.setEditor(new JSpinner.DateEditor(this.DateJSpinner,"d-MM-yyyy"));
        this.DateJSpinner.setFont(FontLoaderBase.getFont());
    }
    protected void CreateButtonsContainer(){
        this.ButtonsContainer.setLayout(this.ButtonsContainerLayout);
        this.ButtonsContainer.add(this.InsertDataButton=new JButton(this.getButtonText()),BorderLayout.EAST);
    }
}
