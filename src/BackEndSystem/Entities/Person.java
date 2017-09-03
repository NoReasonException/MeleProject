package BackEndSystem.Entities;

import BackEndSystem.Entities.DatabaseCommunications.Database;
import BackEndSystem.Entities.Interfaces.ReloadAble;
import BackEndSystem.Entities.Interfaces.SaveAble;
import BackEndSystem.Entities.Interfaces.UpdateAble;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public abstract class Person implements Serializable  ,SaveAble,ReloadAble,UpdateAble{
    private int ID;
    private String Name=null;
    private String Surname=null;
    private javax.swing.ImageIcon SelfPicture=null;
    private java.util.Date DateOfBirth=null;
    private static java.util.Collection<Person> person = new java.util.ArrayList<Person>();
    private java.util.Collection<String>Phones = new java.util.ArrayList<String>();
    private java.util.Collection<String>Emails = new java.util.ArrayList<String>();

    public static int createPersonToDB(java.lang.String Name,java.lang.String Surname,java.lang.String DateOfBirth){//returns the ID of newly Created Person!
        Connection conn = Database.getInstance().getConnection();
        String [] spl = DateOfBirth.split("/");
        java.sql.Date dt = java.sql.Date.valueOf(LocalDate.of(Integer.valueOf(spl[2]),Integer.valueOf(spl[1]),Integer.valueOf(spl[0])));
        try{
            PreparedStatement stat = conn.prepareStatement("INSERT INTO Person (PerName, PerSurname, PerBirth) VALUE (?,?,?)");
            stat.setString(1,Name);
            stat.setString(2,Surname);
            stat.setDate(3,dt);
            stat.execute();
            ResultSet ReturnID = conn.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
            ReturnID.next();
            return ReturnID.getInt("LAST_INSERT_ID()");



        }catch (SQLException e){
            System.out.println(e.getMessage());
            return -1;
        }


    }



    public static Object[] searchPersonByName(java.lang.String Name){
        ArrayList<Person> returnVector = new ArrayList<>();
        Iterator<Person> iter = Person.getPersons();
        Person tmp;
        while(iter.hasNext()){
            if((tmp=iter.next()).getName().equals(Name) || tmp.getName().contains(Name)){
                System.out.println("\t[INFO_ON_PREVIOUS_INFO]Found "+tmp);
                returnVector.add(tmp);
            }
        }

        return returnVector.toArray();
    }
    public static Object[] searchPersonBySurname(java.lang.String Surname){
        ArrayList<Person> returnVector = new ArrayList<>();
        Iterator<Person> iter = Person.getPersons();
        Person tmp;
        while(iter.hasNext()){
            if((tmp=iter.next()).getSurname().equals(Surname) || tmp.getSurname().contains(Surname)){
                System.out.println("\t[INFO_ON_PREVIOUS_INFO]Found "+tmp);
                returnVector.add(tmp);
            }
        }

        return returnVector.toArray();
    }
    public static Object[][]to2DRowRepresentationArray(Object[] Data){
        Object[][]DataReturned = new Object[Data.length][5];
        for (int i = 0; i < Data.length; i++) {
            DataReturned[i][0]=String.valueOf(((Person)Data[i]).getID());
            DataReturned[i][1]=String.valueOf(((Person)Data[i]).getName());
            DataReturned[i][2]=String.valueOf(((Person)Data[i]).getSurname());
            DataReturned[i][3]=String.valueOf(((Person)Data[i]).getDateOfBirth().toString());
            DataReturned[i][4]=new JButton("Λοιπές Πληροφορίες");
        }
        return DataReturned;
    }
    public static Object[] searchPersonByNameAndSurname(java.lang.String Name,java.lang.String Surname){
        ArrayList<Object> returnVector = new ArrayList<>();
        Iterator<Person> iter = Person.getPersons();
        Person tmp;
        while(iter.hasNext()){
            if(((tmp=iter.next()).getName().equals(Name) && tmp.getSurname().equals(Surname)||(tmp.getName().contains(Name) && tmp.getSurname().contains(Surname)))){
                returnVector.add(tmp);
            }
        }
        return returnVector.toArray();
    }

    public static Iterator<Person> getPersons(){
        return Person.person.iterator();
    }
    public static Person getPersonByID(int ID){
        java.util.Iterator<Person> iter=Person.getPersons();
        while(iter.hasNext()){
            Person returnPersonTempObject = iter.next();
            if(returnPersonTempObject.getID()==ID)return returnPersonTempObject;
        }
        throw new IllegalArgumentException();
    }
    public static int PersonCount(){
        return Person.person.size();
    }

    @Override
    public boolean UpdateToDatabase(java.sql.Connection conn,java.lang.String query){
        try{
            java.sql.Statement updateStatement = conn.createStatement();
            updateStatement.executeUpdate(query);
            return true;

        }catch (SQLException e){
            return false;
        }


    }
    @Override
    public void toFile(String FileName) throws java.io.FileNotFoundException,java.io.IOException{
        FileOutputStream BinaryFileStream = new FileOutputStream(new java.io.File(FileName));
        ObjectOutputStream ObjOutStream = new ObjectOutputStream(BinaryFileStream);
        ObjOutStream.writeObject(this);
        ObjOutStream.close();
    }

    protected Person(String _Name,String _Surname,java.util.Date _DateOfBirth){
        this.Name=_Name;
        this.Surname=_Surname;
        this.DateOfBirth=_DateOfBirth;
        this.ID=0;
        Person.person.add(this);
    }
    public void setID(int ID){this.ID=ID;}
    public int getID(){return this.ID;}

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setName(String name,boolean databaseSupportedUpdateOperation){
        this.setName(name);
        System.out.println("[INFO]Person Name Object Changed Into Memory..");
        if(!this.UpdateToDatabase(Database.getInstance().getConnection(),"UPDATE Person SET PerName='"+this.getName()+"' WHERE PerID='"+this.getID()+"'")){
            System.out.println("[ERR]Query To Database Fail at "+new java.util.Date());
        }
        else {
            System.out.println("[INFO]Person Name Object Changed Into Database..");

        }
    }
    public String getSurname() {
        return Surname;
    }
    public void setSurname(String surname) {
        Surname = surname;
    }
    public void setSurname(String surname,boolean databaseSupportedUpdateOperation){
        this.setSurname(surname);
        System.out.println("[INFO]Person Surname Object Changed Into Memory..");
        if(!this.UpdateToDatabase(Database.getInstance().getConnection(),"UPDATE Person SET PerSurname='"+this.getSurname()+"' WHERE PerID='"+this.getID()+"'")){
            System.out.println("[ERR]Query To Database Fail at "+new java.util.Date());
        }
        else {
            System.out.println("[INFO]Person Surname Object Changed Into Database..");

        }
    }
    public java.util.Iterator<String>getPhones(){return this.Phones.iterator();}
    public java.lang.String getPhoneAt(int i){return ((java.util.ArrayList<String>)this.Phones).get(i);}
    public int getPhoneCount(){return this.Phones.size();}
    public void addPhone(String newPhone){this.Phones.add(newPhone);}
    public void addPhone(String newPhone,boolean databaseSupportedUpdateOperation){
        this.Phones.add(newPhone);
        this.UpdateToDatabase(Database.getInstance().getConnection(),"INSERT INTO Phones VALUES ("+this.getID()+",'"+newPhone+"');\n");
    }
    public void deletePhoneAt(int i){
        ((ArrayList)this.Phones).remove(i);
    }
    public boolean deletePhoneAt(int i,boolean databaseSupportedUpdateOperation){

        if(this.UpdateToDatabase(Database.getInstance().getConnection(),"DELETE FROM Phones WHERE PhoPhone='"+this.getPhoneAt(i)+"'")){
            System.out.println("[INFO]Phone "+this.getPhoneAt(i)+" deleted successfully from Person Object "+this.getID()+" from database ");
        }
        else{
            System.out.println("[ERR]Phone "+this.getPhoneAt(i)+" could not delete successfully from Person Object "+this.getID());
            return false;

        }
        this.deletePhoneAt(i);
        return true;
    }
    public Object[]PhonesToArray(){return this.Phones.toArray();}
    public java.util.Iterator<String>getEmails(){return this.Emails.iterator();}
    public java.lang.String getEmailAt(int i){return ((java.util.ArrayList<String>)this.Emails).get(i);}
    public int getEmailCount(){return this.Emails.size();}
    public void addEmail(String newEmail){this.Emails.add(newEmail);}
    public void addEmail(String newEmail,boolean databaseSupportedUpdateOperation) throws SQLException{
        if(!this.UpdateToDatabase(Database.getInstance().getConnection(),"INSERT INTO Emails VALUES("+this.getID()+",'"+newEmail+"')")){
            throw new SQLException();
        }
        this.Emails.add(newEmail);

    }
    public void deleteEmailAt(int i){
        ((ArrayList)this.Emails).remove(i);//update base
    }
    public boolean deleteEmailAt(int i, boolean databaseSupportedUpdateOperation ){
        if(this.UpdateToDatabase(Database.getInstance().getConnection(),"DELETE FROM Emails WHERE EmEmail='"+this.getEmailAt(i)+"'")){
            System.out.println("[INFO]Email "+this.getEmailAt(i)+" deleted successfully from Person Object "+this.getID()+" from database ");
        }
        else{
            System.out.println("[ERR]Email "+this.getEmailAt(i)+" could not delete successfully from Person Object "+this.getID());
            return false;

        }
        this.deleteEmailAt(i);
        return true;
    }
    public Object[]EmailsToArray(){return this.Emails.toArray();}



    public ImageIcon getSelfPicture() {
        return SelfPicture;
    }
    public void setSelfPicture(ImageIcon selfPicture) {
        SelfPicture = selfPicture;
    }
    public void setSelfPicture(javax.swing.ImageIcon selfPicture,boolean databaseSupportedUpdateOperation)throws IOException ,java.sql.SQLException {
        this.setSelfPicture(selfPicture);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(selfPicture);
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement("UPDATE Person SET ImageSrc=? where PerID=?");
        byte[]arr=outputStream.toByteArray();
        statement.setBlob(1,new ByteInputStream(arr,arr.length));
        statement.setInt(2,this.getID());
        statement.execute();

    }
    public Date getDateOfBirth() {
        return DateOfBirth;
    }
    @Override
    public java.lang.String toString(){
        return this.getID()+"-"+this.getName()+"-"+this.getSurname();
    }
}
