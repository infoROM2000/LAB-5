package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Main app = new Main();
        app.testCreateSave(); //testare salvare
        app.testLoadView(); //testare incarcare
    }

    private void testCreateSave() {
        Catalog catalog = new Catalog("Java Resources", "c:/Java/catalog.ser");
        Document doc = new Document("Java Course 1", "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        Document doc1 = new Document("J", "C:/test.txt");
        doc.addTag("type", "Slides");
        catalog.add(doc);
        catalog.add(doc1);
        try {
            Operatii.save(catalog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testLoadView() {
        try {
            Catalog catalog = Operatii.load("c:/Java/catalog.ser");
            Document doc = catalog.findById(0);
            Document doc1 = catalog.findById(1);
            Operatii.view(doc);
            Operatii.view(doc1);
        } catch (Operatii.InvalidCatalogException ex) {
        }
    }
}
