package com.igacheri.Dao;
import com.igacheri.config.DatabaseConfig;
import com.igacheri.model.Ranger;
import com.igacheri.model.Sighting;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
public class SightingDao {
    private static final Sql2o sql2o = DatabaseConfig.getDatabase();

    public static void create(Sighting sighting) {
        try (Connection connection = sql2o.open()) {
            String query = "INSERT INTO sightings (location,health,animal_id,ranger_id,timestamp)VALUES (:location,:health,:animal_id,:ranger_id,:timestamp);";

            connection.createQuery( query )
                    .addParameter( "location", sighting.getLocation() )
                    .addParameter( "health", sighting.getHealth() )
                    .addParameter("animal_id", sighting.getAnimal_id())
                    .addParameter("ranger_id", sighting.getRanger_id())
                    .addParameter("timestamp", sighting.getTimestamp())
                    .executeUpdate();
        } catch (Exception exception) {
            System.out.println( exception.getMessage() );
        }
    }
}

