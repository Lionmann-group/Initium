package de.lw.remake;

import de.lw.remake.objects.BaseShip;
import de.todo.engine.entities.GameObject;

public class Level extends GameObject {

    public Formation[] formations;

    public Level(float x, float y) {
        super(x, y);
    }

    private class Formation {
        protected BaseShip[] formation;

        protected Formation(BaseShip[] formation) {
            this.formation = formation;
        }

    }

}
