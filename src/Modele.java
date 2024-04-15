import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;


public class Modele {//la classe qui contient absolument tout le code
    int tailleTrain = 3; //sert de variable globale pour le nombre de wagon dans un train
    public Toigon[] train; //array (je crois) qui contient les toigons qui constituent le train, ya pas encore de locomotive

    //variables globales qui sont les éléments de Entity. Ils servent de référence pour les fonctions add et remove
    Floppa Flop;
    //fin variables globales
    Modele(){

        //On initalise ici les "variables globales" entités
        this.Flop = new Floppa ();
        //fin variables globales


        this.train = new Toigon[tailleTrain*2];
        this.initTrain();//La manière dont je visualise le train, c'est que dans un train de taille i, on met les toigons du bas aux indices 0 à i-1, et les toigons du bas aux indices i à 2*i-1, du coup pour passer au toigon du dessus/dessous on ajoute/retire i à l'indice du toigon
    }
    public void initTrain(){
        for (int i=0; i<tailleTrain*2; i++){
            this.train[i] = new Toigon(i);
        }

    }


    public class Toigon{
        public boolean estToit; //true si le Toigon est le toit, false sinon
        public int numWag; //numéro du wagon, j'imagine qu'on s'en servira pour trouver le suivant/précédent dans la liste?
        //Je pense que les deux attributs ci-dessus ne sont pas strictement nécessaires, mais je pense qu'ils simplifieront le code donc pour l'instant je les laisse
        public List<Entity> contenu; //contient tout ce qui est dans le wagon, peut être ce serait plus simple si on sépare les trésors et les personnages en vrai
        Toigon(boolean t, int n){// normalement, sauf pour éventuellement des tests on devrait pas avoir besoin de cette fonction
            this.estToit = t;
            this.numWag = n;
            this.contenu  = new ArrayList<Entity>();

        }
        Toigon( int i){//surcharge de la fonction précédente, cette version sera utilisée pour l'initialisation du train
            this.estToit = (i>=tailleTrain);
            this.numWag = i;
            this.contenu  = new ArrayList<Entity>();

        }
        boolean getToit(){return this.estToit;}

        int getNum(){return this.numWag;}

        List<Entity> getContenu(){return this.contenu;}

        void addEnt(Entity e){this.contenu.add(e);}//marche pour les entités dont on a déclaré une "variable globale" de référence dans modèle
        void delEnt(Entity e){this.contenu.remove(e);}// retire la première occurence de l'entité e. Pousse à gauche tout élément après e (donc ya pas de trou dans l'array
        void delEnt(int i){this.contenu.remove(i);}//surcharge de la fonction précédente. retire l'entité d'index i
    }


    public abstract class Entity {//inclue trésors et shérif et bandits


    }


    public abstract class Tresor extends Entity {
        private int valeur;
        abstract int getVal();

    }


    public class Floppa extends Tresor {//j'ai créé ça juste pour des tests
        private int valeur = 99999999;
        public int getVal(){return this.valeur;}

    }

    public abstract class Personnage extends Entity {


    }

    public class bandit extends Personnage {
        public int score;

        public bandit() {
            score = 0;
        }
    }


    public static void main(String[] args) {
        //tests for entity
         Modele m = new Modele();
         assertEquals(m.tailleTrain*2, m.train.length);//teste que la taille du train (chaque Toigons compte pour 1, donc un wagon et son toit compte pour 2) est bien égal à 2 fois tailleTrain
         for (int i = 0; i<m.train.length; i++){//teste que l'initialisation du train marche bien

             if (i< m.tailleTrain) {
                 assertFalse(m.train[i].getToit());
                 assertEquals(i,m.train[i].getNum());
             }
             else{
                 assertTrue(m.train[i].getToit());
                 assertEquals(i,m.train[i].getNum());
             }
         }
         //juste des tests pour que je vérifie comment marche les arrayList, je suprimerai plus tard
         m.train[0].addEnt(m.Flop);
        m.train[0].addEnt(m.Flop);
         System.out.println(m.train[0].getContenu());
         m.train[0].delEnt(1);
        System.out.println(m.train[0].getContenu());

    }

}