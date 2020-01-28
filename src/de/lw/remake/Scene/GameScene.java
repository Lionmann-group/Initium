package de.lw.remake.Scene;

import de.lw.remake.Level;
import de.lw.remake.hud.Hud;
import de.lw.remake.objects.PlayerShip;
import de.lw.remake.objects.StarBackground;
import de.todo.engine.Window;
import de.todo.engine.entities.Camera;
import de.todo.engine.entities.GameObject;
import de.todo.engine.input.IInputState;
import de.todo.engine.render.GLColor;
import de.todo.engine.scene.Scene;
import de.todo.engine.utility.DebugStatistics;
import org.joml.Vector2f;

import java.awt.*;

public class GameScene extends Scene {

    private int levelNumber = 0;

    private Level level;
    private PlayerShip playerShip;

    public GameScene(int levelNumber){
        this.levelNumber = levelNumber;
    }

    @Override
    protected void initScene(Window.WindowDimensions windowDimensions) throws Exception {
        setActiveCamera(new Camera(0, 0));

        final GameObject stats = DebugStatistics.getInstance().createTextObject(new Vector2f(0, 0), new Font("Ubuntu Mono", Font.BOLD, 14), GLColor.WHITE, 4);

        final Hud hud = new Hud();

        addGameObjects(
                playerShip = new PlayerShip(hud.getLifebar()),
                level = new Level(levelNumber),
                hud,
                new StarBackground(),
                stats
        );
    }

    @Override
    public void input(IInputState inputState) throws Exception {
        playerShip.input(inputState);
    }

}
