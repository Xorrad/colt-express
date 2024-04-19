package main;

import main.modeles.Modele;
import main.vues.Assets;
import main.vues.Vue;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Assets.chargeAssets();
        // Il est recommendé de faire ça pour éviter les modifications concurrentes (voir Thread safety).
        EventQueue.invokeLater(() -> {
            Modele modele = new Modele();
            Vue vue = new Vue(modele);
        });
    }

}
