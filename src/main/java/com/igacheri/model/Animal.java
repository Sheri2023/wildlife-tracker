package com.igacheri.model;

import com.igacheri.Configuration.Age;
import com.igacheri.Configuration.Health;

public class Animal {
    private Age age;
    private Health health;
    private int id;
    private String name;
    private boolean endangered;

    public Animal() {
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEndangered() {
        return endangered;
    }

    public void setEndangered(boolean endangered) {
        this.endangered = endangered;
    }
}
