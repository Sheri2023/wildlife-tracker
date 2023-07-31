package com.igacheri.Dao;
import com.igacheri.config.DatabaseConfig;
import com.igacheri.dto.Option;
import com.igacheri.model.Animal;
import com.igacheri.model.Ranger;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

public class RangerDao {
    private static final Sql2o sql2o = DatabaseConfig.getDatabase();

    public static void create(Ranger ranger) {
        try (Connection connection = sql2o.open()) {
            String query = "INSERT INTO rangers (name) VALUES (:name);";
            connection.createQuery( query )
                    .addParameter( "name", ranger.getName() )
                    .executeUpdate();
        } catch (Exception exception) {
            System.out.println( exception.getMessage() );
        }
    }

    public static List<Option> getRangerOption() {
        try (Connection connection = sql2o.open()) {
            String query = "SELECT id,name as text from rangers";
            List<Option> listOption = connection.createQuery( query )
                    .executeAndFetch( Option.class );
            System.out.println( listOption );
            return listOption;
        } catch (Exception exception) {
            System.out.println( exception.getMessage() );
            return null;
        }
    }
}