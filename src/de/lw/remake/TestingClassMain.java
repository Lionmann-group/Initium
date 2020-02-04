package de.lw.remake;

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
import de.todo.engine.utility.Timer;
import org.joml.Vector2f;

import java.awt.Font;

public class TestingClassMain extends Scene {

    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 1000;
    public static final Vector2f point1 = new Vector2f(250, 250);
    public static final Vector2f point2 = new Vector2f(750, 750);

    public int spawnX = 500, spawnY = 500;
    public TestObject testObject;

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
    protected void initScene(final Window.WindowDimensions windowDimensions) throws Exception {
        setActiveCamera(new Camera(0, 0));

        final AudioTrack track = AudioHandler.getInstance().createAudioTrack("/Music/Violet.ogg");

        AudioHandler.getInstance().playAudioTrack(track, false);

        final GameObject stats = DebugStatistics.getInstance().createTextObject(new Vector2f(0, 0), new Font("Ubuntu Mono", Font.BOLD, 14), GLColor.WHITE, 4);

        addGameObjects(
                stats,
                testObject = new TestObject(spawnX, spawnY)
        );

    }

    @Override
    public void input(final IInputState inputState) throws Exception {
        if (inputState.isKeyPressed(KeyState.KEY_SPACE)) {
            testObject.setPosition(new Vector2f(spawnX, spawnY));
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

        float projectileTimer = 0.0f;
        float angle = -100.0f;

        private TestObject(float x, float y) {
            super(x, y);

            setMesh(new RectangularMesh(100f, 100f));
            setRenderDefinition(new TextureRenderDefinition("/Test/The_Girl_that_gets_my_PP_hard.png"));
            setScale(0.5f);
            setLayer(0.5f);
        }

        @Override
        public void update() {
            angle += 1.0f * Timer.getTimeDelta();
            projectileTimer += Timer.getTimeDelta();

            if (projectileTimer >= 0.3f) {
                projectileTimer -= 0.3f;

                addChildren(new TestProjectile(new Vector2f(getPosition()), angle));
            }
        }

    }

    private class TestProjectile extends GameObject {

        private int movePhase;
        private final float angle;

        private TestProjectile(final Vector2f position, final float angle) {
            super(position);
            this.angle = angle;

            setMesh(new RectangularMesh(100.0f, 100.0f));
            setRenderDefinition(new TextureRenderDefinition("/Objects/Energyball.png"));
            setLayer(1.0f);
            setScale(0.3f);
            useRelativePosition(false);
        }

        @Override
        public void update() {
            move();
        }

        public void move() {
            movePhase = movePhase + 10;

            move(new Vector2f(movePhase, -0.01f * angle * movePhase));
        }
    }

}
