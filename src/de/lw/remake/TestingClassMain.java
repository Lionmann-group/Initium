package de.lw.remake;

import com.sun.jna.platform.unix.X11;
import de.lw.remake.objects.PlayerShip;
import de.todo.engine.GameEngine;
import de.todo.engine.Window;
import de.todo.engine.audio.AudioHandler;
import de.todo.engine.audio.AudioTrack;
import de.todo.engine.entities.Camera;
import de.todo.engine.entities.GameObject;
import de.todo.engine.input.IInputState;
import de.todo.engine.input.KeyState;
import de.todo.engine.render.GLColor;
import de.todo.engine.render.definition.TextureRenderDefinition;
import de.todo.engine.render.mesh.RectangularMesh;
import de.todo.engine.scene.Scene;
import de.todo.engine.utility.DebugStatistics;
import de.todo.engine.utility.ResourceRepository;
import de.todo.games.tobodo.character.Player;
import org.joml.Vector2f;

import java.awt.Font;
import java.util.ArrayList;

public class TestingClassMain extends Scene {

    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 1000;
    public static final Vector2f point1 = new Vector2f(250,250);
    public static final Vector2f point2 = new Vector2f(750,750);

    public int spawnX = 500,spawnY = 500;
    public TestObject testObject;

    private AudioTrack track;

    public static void main(final String[] args) {
        ResourceRepository.addRepository(TestingClassMain.class);

        final GameEngine engine = new GameEngine(
                "Test",
                WINDOW_WIDTH,
                WINDOW_HEIGHT,
                new TestingClassMain()
        );

        engine.getConfig().setTargetFps(120);

        engine.start();
    }


    @Override
    protected void initScene(Window.WindowDimensions windowDimensions) throws Exception {
        setActiveCamera(new Camera(0, 0));
        track = AudioHandler.getInstance().createAudioTrack("/Music/Violet.ogg");
        AudioHandler.getInstance().playAudioTrack(track, false);
        final GameObject stats = DebugStatistics.getInstance().createTextObject(new Vector2f(0, 0), new Font("Ubuntu Mono", Font.BOLD, 14), GLColor.WHITE, 4);

        addGameObjects(
                stats,
                testObject = new TestObject(spawnX,spawnY)
        );

    }

    @Override
    public void input(IInputState inputState) throws Exception {
        if (inputState.isKeyPressed(KeyState.KEY_SPACE)) {
            testObject.setPosition(new Vector2f(spawnX,spawnY));
        }
        if (inputState.isKeyDown(KeyState.KEY_UP)) {
            spawnY--;
        }
        if (inputState.isKeyDown(KeyState.KEY_DOWN)) {
            spawnY++;
        }
        if (inputState.isKeyDown(KeyState.KEY_RIGHT)) {
            spawnX++;
        }
        if (inputState.isKeyDown(KeyState.KEY_LEFT)) {
            spawnX--;
        }
    }

    private class TestObject extends GameObject {

        int projectileTimer;
        float angle = -100f;

        public TestObject(float x, float y) {
            super(x, y);

            setMesh(new RectangularMesh(100f, 100f));
            setRenderDefinition(new TextureRenderDefinition("/Test/The_Girl_that_gets_my_PP_hard.png"));
            setScale(0.5f);
            setLayer(0.5f);
        }

        @Override
        public void update() {
            angle = angle + 0.1f;
            projectileTimer++;
            if(projectileTimer >= 20){
                projectileTimer = 0;
                addChildren(new TestProjectile(new Vector2f(getPosition()),angle,this));
            }
        }

    }

    private class TestProjectile extends GameObject {

        float movePhase;
        private int accelerationInterval = 10;
        private int accelerationTimer;
        float accerleration = -2f;
        float speed = 10;
        float angle;

        public TestProjectile(Vector2f position, float angle, TestObject parent) {
            super(position);
            this.angle = angle;

            setMesh(new RectangularMesh(100f, 100f));
            setRenderDefinition(new TextureRenderDefinition("/Objects/Energyball.png"));
            setLayer(1f);
            setScale(0.3f);
            useRelativePosition(false);

        }

        @Override
        public void update() {
            this.move();
        }

        public void move() {
            accelerationTimer++;
            if(accelerationTimer >= accelerationInterval){
                speed = speed + accerleration;
                accelerationTimer = 0;
            }
                movePhase = movePhase + speed;
            this.move(new Vector2f(movePhase,-0.01f*angle*movePhase));

        }
    }

}
