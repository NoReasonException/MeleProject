package BackEndSystem.Entities.Interfaces;

/**
 * Created by stefstef on 5/6/2017.
 */
@FunctionalInterface
public interface UpdateAble {
    boolean UpdateToDatabase(java.sql.Connection conn,java.lang.String query);
}
