package main.modeles;

// La classe du modèle qui contient tout le code relatif à la logique et système.
public class Modele {
    private int tailleTrain = 3; // Nombre de "wagon" dans un train (donc la moitié des toigons).
    private Toigon[] train; // Tableau qui contient les toigons qui constituent le train, ya pas encore de locomotive.

    public Modele(){
        this.initTrain();
    }
    public void initTrain(){
        this.train = new Toigon[2*this.tailleTrain];

        // Tristan:
        // La manière dont je visualise le train, c'est que dans un train de taille i,
        // on met les toigons du bas aux indices 0 à i-1, et les toigons du bas aux indices i à 2*i-1,
        // du coup pour passer au toigon du dessus/dessous on ajoute/retire i à l'indice du toigon.

        // Aymeric:
        // Je pense qu'il est mieux de mettre le toigon du bas en i et celui du haut en i+1.
        // Donc mettre les wagons par pair de deux.

        for (int i = 0; i < 2*this.tailleTrain; i++){
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

    public int getTailleTrain() {
        return this.tailleTrain;
    }

    public Toigon[] getTrain() {
        return this.train;
    }

    public Toigon getToigon(int indice) {
        if(indice >= this.train.length)
            throw new IllegalArgumentException("l'indice de toigon " + indice + " n'existe pas");
        return this.train[indice];
    }
}