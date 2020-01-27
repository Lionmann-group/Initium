package de.lw.remake;

import de.lw.remake.objects.BaseShip;
import de.lw.remake.objects.EnemyShip;
import de.todo.engine.entities.GameObject;
import de.todo.engine.utility.UpdateMode;

import java.util.LinkedList;
import java.util.List;


public class Level extends GameObject {

    public boolean astroidsEnabled;
    private boolean lastFormationAlive;

    private int levelNumber;
    public List formations;

    //formations
    private Formation formation1 = new Formation(
            new EnemyShip( 0.0f, ((MainScene.WINDOW_HEIGHT / 6.0f)         )),
            new EnemyShip(40.0f, ((MainScene.WINDOW_HEIGHT / 6.0f) * 2 + 25)),
            new EnemyShip(40.0f, ((MainScene.WINDOW_HEIGHT / 6.0f) * 4 - 25)),
            new EnemyShip( 0.0f, ((MainScene.WINDOW_HEIGHT / 6.0f) * 5 + 25))
    );

    public Level(int levelNumber) {
        super(MainScene.WINDOW_WIDTH, MainScene.WINDOW_HEIGHT / 2.0f);
        this.levelNumber = levelNumber;
        formations = new LinkedList();
        initLevel();

        setUpdateMode(UpdateMode.ALWAYS);
    }

    private class Formation {
        protected EnemyShip[] formation;

        public Formation(EnemyShip... formation) {
            this.formation = formation;
        }

    }

    @Override
    public void update() {

    }

    private void initLevel(){
        if (levelNumber == 0){
            addFormationToLevel(formation1);
        }
        if (levelNumber == 1){
            addFormationToLevel(formation1);
            addFormationToLevel(formation1);
            System.out.println(formations);
        }

    }

    private void addFormationToLevel(Formation formation){
        formations.add(formation);
    }

}
