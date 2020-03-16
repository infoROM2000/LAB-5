package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {
    private int id; //unic
    private String name;
    private String location; // url sau path local
    private static int count = 0; //se incrementeaza la crearea unui obiect.. generam cu el id-uri unice

    private Map<String, Object> tags = new HashMap<>();

    public Document(String nume, String locatie) {
        id = count++;
        if (nume == null || nume.trim().equals("")) { //verificam ca parametrul nume sa nu fie gol
            throw new IllegalArgumentException("Name should not be empty.");
        }
        name = nume;
        location = locatie;
    }

    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }
}
