package com.igacheri.Config;
import org.sql2o.Sql2o;
public class Databaseconfig {
    public static Sql2o getDatabase(){
        return new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "postgres", "Wamwea2012!");
    }
}
