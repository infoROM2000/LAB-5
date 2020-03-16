package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents = new ArrayList<>();

    public Catalog(String nume, String cale) {
        if (nume == null || nume.trim().equals("")) { //verificam ca parametrul nume sa nu fie gol
            throw new IllegalArgumentException("Name should not be empty.");
        }
        name = nume;
        path = cale;
    }

    public void add(Document doc) {
        documents.add(doc);
    }

    public Document findById(int id) {
        for (Document doc : documents) { //foreach de cautare clasic
            if (doc.getId() == id) {
                return doc;
            }
        }
        return null;
    }

    public String getPath() {
        return path;
    }

}
