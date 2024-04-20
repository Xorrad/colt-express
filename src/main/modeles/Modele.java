package main.modeles;

import main.modeles.entites.Bandit;
import main.modeles.entites.Sheriff;
import main.modeles.entites.Tresor;
import main.vues.Assets;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.util.*;

// La classe du modèle qui contient tout le code relatif à la logique et système.
public class Modele extends Observable {

    // Constantes globales.
    public static final int NB_WAGONS = 6; // Nombre de "wagon" dans un train (donc la moitié des toigons).
    public static final int NB_BANDITS = 4;
    public static final int NB_SHERIFFS = 1;
    public static final int NB_TRESORS_WAGON = 5;
    public static final int NB_ACTIONS = 3;

    public static final double NERVOSITE_SHERIFF = 1.0;

    private Toigon[] train; // Tableau qui contient les toigons qui constituent le train, ya pas encore de locomotive.
    private HashMap<Integer, Bandit> bandits;
    private HashMap<Integer, Sheriff> sheriffs;

    private int tourNumBandit;

    public Modele(){
        this.initTrain();
        this.initBandits();
        this.initSheriffs();
        this.initTresors();
        this.joueMusique();
    }

    private void initTrain(){
        this.train = new Toigon[2*NB_WAGONS];

        // Tristan:
        // La manière dont je visualise le train, c'est que dans un train de taille i,
        // on met les toigons du bas aux indices 0 à i-1, et les toigons du bas aux indices i à 2*i-1,
        // du coup pour passer au toigon du dessus/dessous on ajoute/retire i à l'indice du toigon.

        // Aymeric:
        // Je pense qu'il est mieux de mettre le toigon du bas en i et celui du haut en i+1.
        // Donc mettre les wagons par pair de deux.

        for(int i = 0; i < 2*NB_WAGONS; i++) {
            this.train[i] = new Toigon(i, i%2 == 1);

            // Ajoute les voisins à chaque toigons en suivant le schéma d'indices:
            // 1 3 5 ... (haut)
            // 0 2 4 ... (bas)
            if(i%2 == 1) {
                this.train[i].setVoisin(Direction.BAS, this.train[i-1]);
                this.train[i-1].setVoisin(Direction.HAUT, this.train[i]);
            }
            if(i > 1) {
                this.train[i].setVoisin(Direction.ARRIERE, this.train[i-2]);
                this.train[i-2].setVoisin(Direction.AVANT, this.train[i]);
            }
        }

    }

    private void initBandits() {
        this.bandits = new HashMap<>();
        this.tourNumBandit = 0;

        // Charge la liste des noms personalisés des bandits (dans resources/images/bandits)
        ArrayList<String> noms = new ArrayList<>();
        noms.addAll(Assets.BANDITS.keySet());
        Collections.shuffle(noms);

        for(int i = 0; i < NB_BANDITS; i++) {
            int indiceToigon = i % this.train.length;
            if(i == this.train.length-2) // Pas de bandits sur la locomotive.
                indiceToigon = (indiceToigon+1) % this.train.length;

            // Creer le bandit avec un nom (ou numéro si aucun disponible) et prend l'image associée
            String nom = (i >= noms.size()) ? "Bandit " + i : noms.get(i % noms.size());
            BufferedImage image = (i >= noms.size()) ? Assets.IMG_BANDIT : Assets.BANDITS.get(nom);
            Bandit bandit = new Bandit(i, this.train[indiceToigon], nom, image);

            this.train[indiceToigon].ajouteEntite(bandit);
            this.bandits.put(i, bandit);
        }
    }

    private void initSheriffs() {
        this.sheriffs = new HashMap<>();

        Toigon locomotive = this.getLocomotive();
        for(int i = 0; i < NB_SHERIFFS; i++) {
            Sheriff sheriff = new Sheriff(i, locomotive, "Sheriff " + i);
            locomotive.ajouteEntite(sheriff);
            this.sheriffs.put(i, sheriff);
        }
    }

    private void initTresors() {
        // On garde un numero unique à chaque trésor.
        int numTresor = 0;

        // Il n'y a pas de passagers dans la locomotive, donc on s'arrête un wagon avant.
        for(int j = 0; j < NB_WAGONS-1; j++) {
            // On determine l'indice du toigon du bas du wagon j.
            int indiceToigon = 2*j;
            int nombreTresors = 1 + new Random().nextInt(NB_TRESORS_WAGON - 1);

            for (int i = 0; i < nombreTresors; i++) {
                this.train[indiceToigon].ajouteEntite(Tresor.genereTresor(numTresor, this.train[indiceToigon]));
                numTresor++;
            }
        }

        // Ajoute le magot de $1000 dans la locomotive.
        Toigon locomotive = this.getLocomotive();
        Tresor tresor = new Tresor(numTresor, locomotive, "Magot", 1000);
        locomotive.ajouteEntite(tresor);
    }

    private void joueMusique() {
        try {
            Clip clip2 = AudioSystem.getClip();
            clip2.open(Assets.AUDIO_TRAIN);
            clip2.loop(Clip.LOOP_CONTINUOUSLY);

            Clip clip = AudioSystem.getClip();
            clip.open(Assets.AUDIO_MUSIQUES);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Toigon[] getTrain() {
        return this.train;
    }

    public Toigon getLocomotive() {
        return this.train[this.train.length-2];
    }

    public int getNombreToigons() {
        return this.train.length;
    }

    public Toigon getToigon(int indice) {
        if(indice >= this.train.length)
            throw new IllegalArgumentException("l'indice de toigon " + indice + " n'existe pas");
        return this.train[indice];
    }

    public HashMap<Integer, Sheriff> getSheriffs() {
        return sheriffs;
    }

    public HashMap<Integer, Bandit> getBandits() {
        return bandits;
    }

    public Bandit getTourBandit() {
        return this.bandits.get(this.tourNumBandit);
    }

    public Phase getPhaseJeu() {
        if(this.tourNumBandit == this.bandits.size())
            return Phase.ACTIONS;
        return Phase.CHOIX;
    }

    // Passe au bandit suivant (pour la selection des actions).
    public void avanceTour() {
        this.tourNumBandit++;
        this.notifyObservers();
    }

    public boolean resteActionAJouee() {
        for(Bandit bandit : this.bandits.values()) {
            if(bandit.getNombreActions() > 0)
                return true;
        }
        return false;
    }

    // Joue la premier action du bandit donné et déplace (potentiellement) les sheriffs.
    public void joueAction(int numBandit) {
        Bandit bandit = this.bandits.get(numBandit);
        if(bandit.getNombreActions() > 0) {
            System.out.println("Applique l'action de " + bandit.getNom() + ".");
            bandit.joueAction();
            this.sheriffs.forEach((num, sheriff) -> sheriff.joue());
            this.notifyObservers();
        }
    }

    public void changePhase() {
        System.out.println("Une nouvelle phase de choix débute.");
        this.tourNumBandit = 0;
        this.notifyObservers();
    }
}