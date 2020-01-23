package de.lw.remake;

import de.lw.remake.Scene.StartScene;
import de.lw.remake.hud.Hud;
import de.lw.remake.objects.EnemySpawner;
import de.lw.remake.objects.PlayerShip;
import de.lw.remake.objects.StarBackground;
import de.todo.engine.GameEngine;
import de.todo.engine.Window;
import de.todo.engine.entities.Camera;
import de.todo.engine.entities.GameObject;
import de.todo.engine.input.IInputState;
import de.todo.engine.render.GLColor;
import de.todo.engine.scene.Scene;
import de.todo.engine.utility.DebugStatistics;
import org.joml.Vector2f;

import java.awt.Font;

public class Main extends Scene {

    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 700;
    public static GameEngine ENGINE;

    public static void main(String[] args) {
        ENGINE = new GameEngine(
                "Not Space Invaders",
                WINDOW_WIDTH,
                WINDOW_HEIGHT,
                new StartScene()
        );

        ENGINE.getConfig().setTargetFps(120);

        ENGINE.start();
    }

    // Class

    private PlayerShip playerShip;

    @Override
    protected void initScene(final Window.WindowDimensions windowDimensions) {
        setActiveCamera(new Camera(0, 0));

        final GameObject stats = DebugStatistics.getInstance().createTextObject(new Vector2f(0, 0), new Font("Ubuntu Mono", Font.BOLD, 14), GLColor.WHITE, 4);

        final Hud hud = new Hud();

        addGameObjects(
                playerShip = new PlayerShip(hud.getLifebar()),
                new EnemySpawner(),
                hud,
                new StarBackground(),
                stats
        );
    }

    @Override
    public void input(final IInputState inputState) {
        playerShip.input(inputState);
    }

}
