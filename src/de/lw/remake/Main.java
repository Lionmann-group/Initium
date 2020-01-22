package de.lw.remake;

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
import de.todo.engine.utility.ResourceRepository;
import org.joml.Vector2f;
import org.w3c.dom.ls.LSResourceResolver;

import java.awt.Font;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

public class Main extends Scene {

    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 700;

    public static void main(final String[] args) {
        ResourceRepository.addRepository(Main.class);

        final GameEngine ge = new GameEngine(
                "Not Space Invaders",
                WINDOW_WIDTH,
                WINDOW_HEIGHT,
                new Main()
        );

        ge.getConfig().setWindowVSync(true);

        ge.start();
    }

    // Class

    private PlayerShip playerShip;

    @Override
    protected void initScene(final Window.WindowDimensions windowDimensions) {
        setActiveCamera(new Camera(0, 0));

        final GameObject stats = DebugStatistics.getInstance().createTextObject(new Vector2f(0, 0), new Font("Ubuntu Mono", Font.BOLD, 14), GLColor.WHITE, 4);

        addGameObjects(
                playerShip = new PlayerShip(),
                new EnemySpawner(),
                new Hud(),
                new StarBackground(),
                stats
        );
    }

    @Override
    public void input(final IInputState inputState) {
        playerShip.input(inputState);
    }

}
