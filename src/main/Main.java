package main;

import main.modeles.Modele;
import main.vues.Vue;

public class Main {

    public static void main(String[] args) {
        Modele modele = new Modele();
        Vue vue = new Vue(modele);
    }

}
