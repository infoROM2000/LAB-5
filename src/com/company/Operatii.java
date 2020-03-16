package com.company;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class Operatii {
    public static void save(Catalog catalog) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    public static class InvalidCatalogException extends Exception {
        public InvalidCatalogException(Exception ex) {
            super("Invalid catalog file", ex);
        }
    }

    public static Catalog load(String path) throws InvalidCatalogException {
        FileInputStream fis = null; //initializam streamul pentru al putea inchide in finally
        Catalog catalog = null; //initializam catalogul pentru al putea returna la final
        try {
            fis = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fis);
            catalog = (Catalog) in.readObject(); //fisierul nostru contine un obiect catalog in forma binara pe care il castam inapoi la obiect
        } catch (FileNotFoundException e) {
            System.out.println("Fisier inexistent!");
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului");
        } catch (ClassNotFoundException e) {
            System.out.println("Clasa de obiect inexistenta");
        } finally {
            if (fis != null) {
                try {
                    fis.close(); // inchidem fisierul
                } catch (IOException e) {
                    System.out.println("Eroare la inchiderea fisierului");
                }
            }
        }
        return catalog;
    }

    public static void view(Document doc) {
        Desktop desktop = Desktop.getDesktop(); //initializare desktop
        // daca calea documentului incepe tipic unui website, vom incerca ramura cu url; altfel cale locala
        if (doc.getLocation().startsWith("https://") || doc.getLocation().startsWith("http://")) {
            try { //deschidere URI
                desktop.browse(new URI(doc.getLocation()));
            } catch (IOException | URISyntaxException e) {
                System.out.println("URI invalid!");
            }
        } else {
            try { //deschidere cale locala
                desktop.open(new File(doc.getLocation()));
            } catch (IOException e) {
                System.out.println("Cale locala invalida!");
            }
        }
    }
}


