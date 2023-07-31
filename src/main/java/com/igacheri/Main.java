package com.igacheri;

import com.igacheri.Dao.AnimalDao;
import com.igacheri.Dao.RangerDao;
import com.igacheri.Dao.RangerDao;
import com.igacheri.Dao.SightingDao;
import com.igacheri.Configuration.Age;
import com.igacheri.Configuration.Health;
import com.igacheri.model.Animal;
import com.igacheri.model.Ranger;
import com.igacheri.model.Sighting;
import com.igacheri.model.Sighting;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.post;

public class Main {
    private static SightingDao SightingDao;

    public static void main(String[] args) {
        port(8085);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);
        staticFiles.registerMimeType("html", "text/html");
        staticFiles.registerMimeType("hbs", "text/html");
        staticFiles.externalLocation("src/main/resources");

        get("/ranger/add", (request, response) -> {
            return new ModelAndView(new HashMap<>(), "ranger.hbs");
        }, new HandlebarsTemplateEngine());


        post("/create-ranger", (request, response) -> {
            String name = request.queryParams("ranger");
            Ranger ranger = new Ranger();

            ranger.setName(name);
            RangerDao.create(ranger);
            System.out.println(name);
            String alertScript = "<script>alert('successful added ranger " + name + "');</script>";
            return alertScript;
        });

        get("/animal/add", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            // model.put("options", animalDao.());
            return new ModelAndView(model, "animal.hbs");
        }, new HandlebarsTemplateEngine());


        post("/create-animal", (request, response) -> {
            String name = request.queryParams("animal");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String endangered = request.queryParams("endangered");
            Animal animal = new Animal();
            animal.setAge(Age.valueOf(age));
            animal.setHealth(Health.valueOf(health));
            animal.setName(name);
            animal.setEndangered(endangered.equalsIgnoreCase("yes"));
            AnimalDao.create(animal);
            System.out.println(name + " " + age + health);
            String alertScript = "<script>alert('successful added animal " + name + "' );</script>";
            return alertScript;
        });
        get("/sighting/add", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rangers", RangerDao.getRangerOption());
            model.put("animals", AnimalDao.getAnimalOption());
            return new ModelAndView(model, "sighting.hbs");
        }, new HandlebarsTemplateEngine());


        post("/create-sighting", (request, response) -> {
            String location = request.queryParams("location");
            String health = request.queryParams("health");
            String animalId = request.queryParams("animalId");
            String rangerId = request.queryParams("rangerId");
            Sighting sighting = new Sighting();
            sighting.setLocation(location);
            sighting.setHealth(health);
            sighting.setTimestamp(LocalDateTime.now());
            sighting.setAnimal_id(Integer.parseInt(animalId));
            sighting.setRanger_id(Integer.parseInt(rangerId));

            SightingDao.create(sighting);

            System.out.println(location + " " + health);
            String alertScript = "<script>alert('successful added sighting " + sighting + "' );</script>";
            return alertScript;
        });


    }

}