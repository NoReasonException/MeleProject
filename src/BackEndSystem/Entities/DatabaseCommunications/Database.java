package BackEndSystem.Entities.DatabaseCommunications;

import javax.swing.*;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by stefstef on 5/6/2017.
 */
public class Database {
    private static Database siglenton=null;
    private java.sql.Connection conn;
    private boolean isOkay=false;
    public static Database getInstance(){
        if(Database.siglenton==null){
            Database.siglenton=new Database();
        }
       return Database.siglenton;
    }

    /**
     *
     * @param DateToConvert
     * @return An SQL Type Date
     * @Brief This Function returns a SQL Date object from an string in form Date/Month/Year
     */
    public static java.sql.Date StringToDate(String DateToConvert){
        String args[]=DateToConvert.split("/");
        return Date.valueOf(LocalDate.of(Integer.valueOf(args[2]),Integer.valueOf(args[1]),Integer.valueOf(args[0])));
    }
    private Database(){
        try{
            this.conn= DriverManager.getConnection("jdbc:mysql://192.168.1.9/MeleProjectDB","root","12312312345");
            this.isOkay=true;
        }catch (Exception e){

            System.out.println("[ERR]Database Connection Collapsed"+e.getMessage());
        }
    }
    public static java.sql.Date fromUtillDate(java.util.Date convert){
        String [] spl=String.format("%td/%tm/20%ty", convert, convert, convert).split("/");
        return java.sql.Date.valueOf(LocalDate.of(Integer.valueOf(spl[2]),Integer.valueOf(spl[1]),Integer.valueOf(spl[0])));
    }
    public boolean isOkay(){return this.isOkay;}
    public java.sql.Connection getConnection(){return this.conn;}
    public void killConnection(){
        try{
            this.conn.close();
            System.out.println("[INFO]Connection with DB Terminated(Termination Procedure..)");
        }catch (SQLException e){
            System.out.println("[ERR]Connection with DB Broken Already.(Termination Procedure..)");

        }
    }

}
