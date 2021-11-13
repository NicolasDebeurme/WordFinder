package com.example.androidprojet.ui.login;

import java.util.List;

//classe permettant de récupérer correctement les informations
public class DictionaryAPI {
    String word; // on prend le nom exact des variables qui sont données par l'api
    List<Phonetics> phonetics; // on définit une classe à chaque fois que l'on a un objet différent entouré par {}
    List<Meanings> meanings; // à chaque fois qu'on a une liste d'objets entouré par [] on met List

    public DictionaryAPI(){};

    @Override
    public String toString() { // fonction permettant d'afficher le résultat facilement dans les logs (inutile sinon)
        return "DictionaryAPI{" +
                "word='" + word + "'" +
                ", phonetics=" + phonetics +
                ", meanings=" + meanings +
                '}';
    }




    public class Phonetics {
        String text;
        String audio;

        @Override
        public String toString() { // fonction permettant d'afficher le résultat facilement dans les logs (inutile sinon)
            return "Phonetics{" +
                    "text='" + text + '\'' +
                    ", audio='" + audio + '\'' +
                    '}';
        }

        public Phonetics(){};
    }

    public class Meanings {
        String partOfSpeech;
        List<Definitions> definitions;

        @Override
        public String toString() { // fonction permettant d'afficher le résultat facilement dans les logs (inutile sinon)
            return "Meanings{" +
                    "partOfSpeech='" + partOfSpeech + '\'' +
                    ", definitions=" + definitions +
                    '}';
        }

        public Meanings(){};
    }

    public class Definitions {
        String definition;
        String example;
        List<String> synonyms;

        @Override
        public String toString() { // fonction permettant d'afficher le résultat facilement dans les logs (inutile sinon)
            return "Definitions{" +
                    "definition='" + definition + '\'' +
                    ", example='" + example + '\'' +
                    ", synonyms=" + synonyms +
                    '}';
        }

        public Definitions(){};
    }

}
