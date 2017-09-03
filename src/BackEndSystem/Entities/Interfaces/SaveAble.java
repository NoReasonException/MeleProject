package BackEndSystem.Entities.Interfaces;

import java.io.Serializable;

/**
 * Created by stefstef on 29/5/2017.
 */
public interface SaveAble extends Serializable {
    public void toFile(String FileName) throws java.io.FileNotFoundException,java.io.IOException;
}
