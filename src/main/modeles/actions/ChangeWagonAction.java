package main.modeles.actions;

import main.modeles.Direction;
import main.modeles.entites.Bandit;

public class ChangeWagonAction extends Action {
    private Direction dir;

    public ChangeWagonAction(Direction dir) {
        this.dir = dir;
    }

    public Direction getDir() {
        return dir;
    }

    @Override
    public void apply(Bandit bandit) {

    }
}
