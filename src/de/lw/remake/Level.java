package de.lw.remake;

import de.lw.remake.objects.Asteroid;
import de.lw.remake.objects.BaseShip;
import de.lw.remake.objects.EnemyShip;
import de.todo.engine.entities.GameObject;
import de.todo.engine.utility.UpdateMode;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Level extends GameObject {

    public boolean astroidsEnabled;
    private boolean lastFormationAlive;
    public int astroidSpawnSpeed = 100;
    private int astroidTimer;

    private int levelNumber;
    public List formations;

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
        if(astroidsEnabled && astroidTimer >= astroidSpawnSpeed){
            spawnAsteriods();
            astroidTimer = 0;
        }
        updateTimers();
    }

    private void updateTimers(){
        astroidTimer++;
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
    private void spawnAsteriods(){
        final Random rng = new Random();
        for (int i = 0; i < 5; i++) {
            addChildren(new Asteroid(100 + rng.nextInt(200), 50 + rng.nextInt(MainScene.WINDOW_HEIGHT - 50)));
        }
    }

    //formations
    private Formation formation1 = new Formation(
            new EnemyShip( 0.0f, ((MainScene.WINDOW_HEIGHT / 6.0f)         )),
            new EnemyShip(40.0f, ((MainScene.WINDOW_HEIGHT / 6.0f) * 2 + 25)),
            new EnemyShip(40.0f, ((MainScene.WINDOW_HEIGHT / 6.0f) * 4 - 25)),
            new EnemyShip( 0.0f, ((MainScene.WINDOW_HEIGHT / 6.0f) * 5 + 25))
    );

}
