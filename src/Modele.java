import java.util.ArrayList;
import java.util.List;



public class Modele {//la classe qui contient absolument tout le code
    int tailleTrain = 3; //sert de variable globale pour le nombre de wagon dans un train
    public Toigon[] train; //array (je crois) qui contient les toigons qui constituent le train, ya pas encore de locomotive
    Modele(){
        this.train = new Toigon[tailleTrain*2]; //La manière dont je visualise le train, c'est que dans un train de taille i, on met les toigons du bas aux indices 0 à i-1, et les toigons du bas aux indices i à 2*i-1, du coup pour passer au toigon du dessus/dessous on ajoute/retire i à l'indice du toigon
    }
    public void initTrain(){
        for (int i=0; i<tailleTrain; i++){
            this.train[i] = new Toigon();
        }

    }


    public class Toigon{
        public boolean estToit; //true si le Toigon est le toit, false sinon
        public int numWag; //numéro du wagon, j'imagine qu'on s'en servira pour trouver le suivant/précédent dans la liste?
        public List<Entity> contenu = new ArrayList<Entity>(); //contient tout ce qui est dans le wagon, peut être ce serait plus simple si on sépare les trésors et les personnages en vrai

    }


    public abstract class Entity {//inclue trésors et shérif et bandits


    }


    public abstract class Trésor extends Entity {
        public int valeur; //should be private probably but it's convenient for now

    }

    public abstract class Personnage extends Entity {


    }

    public class bandit extends Personnage {
        public int score;//should be private probably but it's convenient for

        public bandit() {
            score = 0;
        }
    }


    public static void main(String[] args) {
        //tests for entity
         Modele m = new Modele();
         System.out.println(m.train.length);
    }

}