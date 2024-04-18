package main.modeles.actions;

import main.modeles.Direction;
import main.modeles.entites.Bandit;

public class TireAction extends Action {
    private Direction dir;

    public TireAction(Bandit bandit, Direction dir) {
        super(bandit);
        this.dir = dir;
    }

    public Direction getDir() {
        return dir;
    }

    @Override
    public void apply() {

    }

    @Override
    public boolean canApply() {
        return false;
    }
}
