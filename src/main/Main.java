package main;

import main.modeles.Modele;
import main.vues.Assets;
import main.vues.Vue;

import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Assets.chargeAssets();
        Modele modele = new Modele();
        Vue vue = new Vue(modele);
    }

}
