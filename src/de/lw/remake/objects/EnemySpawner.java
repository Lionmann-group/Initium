package de.lw.remake.objects;

import de.lw.remake.MainScene;
import de.todo.engine.entities.GameObject;
import de.todo.engine.utility.UpdateMode;

import java.util.Random;
import java.util.function.Supplier;

public class EnemySpawner extends GameObject {

    public static int ALIVE = 0;

    // @formatter:off
    private static final Supplier<GameObject[]>[] WAVES = new Supplier[] {
            () -> new GameObject[]{
                    new EnemyShip( 0.0f, ((MainScene.WINDOW_HEIGHT / 6.0f)         )),
                    new EnemyShip(40.0f, ((MainScene.WINDOW_HEIGHT / 6.0f) * 2 + 25)),
                    new EnemyShip(40.0f, ((MainScene.WINDOW_HEIGHT / 6.0f) * 4 - 25)),
                    new EnemyShip( 0.0f, ((MainScene.WINDOW_HEIGHT / 6.0f) * 5     ))
            }
    };
    // @formatter:on

    // Class

    private int wave = 3;

    public EnemySpawner() {
        super(MainScene.WINDOW_WIDTH, MainScene.WINDOW_HEIGHT / 2.0f);

        setUpdateMode(UpdateMode.ALWAYS);
    }

    @Override
    public void update() {
        if (ALIVE < 1) {
            prepareWave();

            wave += 1;
        }
    }

    private void prepareWave() {
        addChildren(
                WAVES[0].get()
        );

        ALIVE += 4;

        if (wave > 3) {
            final Random rng = new Random();
            for (int i = 0; i < 5; i++) {
                addChildren(new Asteroid(100 + rng.nextInt(200), 50 + rng.nextInt(MainScene.WINDOW_HEIGHT - 50)));
            }
        }
    }

}
