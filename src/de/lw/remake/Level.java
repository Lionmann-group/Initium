package de.lw.remake;

import de.lw.remake.objects.BaseShip;
import de.todo.engine.entities.GameObject;
import de.todo.engine.utility.UpdateMode;

public class Level extends GameObject {

    private int levelNumber;
    public Formation[] formations;

    public Level(int levelNumber) {
        super(MainScene.WINDOW_WIDTH, MainScene.WINDOW_HEIGHT / 2.0f);
        this.levelNumber = levelNumber;

        setUpdateMode(UpdateMode.ALWAYS);
    }

    private class Formation {
        protected BaseShip[] formation;

        protected Formation(BaseShip[] formation) {
            this.formation = formation;
        }

    }

}
