package BackEndSystem.Entities.Interfaces;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Created by stefstef on 29/5/2017.
 */
public interface ReloadAble extends Serializable {
    public static Object fromFile(String FileName) throws java.io.IOException,java.io.FileNotFoundException,java.lang.ClassNotFoundException{
        FileInputStream inStr = new FileInputStream(new java.io.File(FileName));
        ObjectInputStream inObjStr = new ObjectInputStream(inStr);
        java.lang.Object returnObj = inObjStr.readObject();
        inObjStr.close();
        return returnObj;
    }
}
