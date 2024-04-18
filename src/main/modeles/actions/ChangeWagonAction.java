package main.modeles.actions;

import main.modeles.Direction;
import main.modeles.entites.Bandit;

public class ChangeWagonAction extends Action {
    private Direction dir;

    public ChangeWagonAction(Bandit bandit, Direction dir) {
        super(bandit);
        this.dir = dir;
    }

    public Direction getDir() {
        return dir;
    }

    @Override
    public void apply() {
        if(!this.canApply()) {
            if(this.dir == Direction.AVANT) {
                System.out.println(this.bandit.getNom() + " est déjà sur la locomotive.");
            }
            else {
                System.out.println(this.bandit.getNom() + " est déjà sur le dernier wagon.");
            }
            return;
        }
        //throw new RuntimeException(this.bandit.getNom() + " ne peut pas aller au wagon en " + this.dir.name());

        this.bandit.deplace(this.dir);
        System.out.println(this.bandit.getNom() + " passe au wagon " + this.dir.name().toLowerCase() + ".");
    }

    @Override
    public boolean canApply() {
        return this.bandit.getToigon().getVoisin(this.dir) != null;
    }
}
