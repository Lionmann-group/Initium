package de.lw.remake.objects;

import de.lw.remake.Main;
import de.lw.remake.hud.Lifebar;
import de.lw.remake.projectile.EnemyProjectile;
import de.lw.remake.projectile.PlayerProjectile;
import de.todo.engine.animation.Animation;
import de.todo.engine.collision.Collider;
import de.todo.engine.collision.CollisionHandler;
import de.todo.engine.collision.ObjectCollider;
import de.todo.engine.entities.BaseTopdownPlayer;
import de.todo.engine.entities.GameObject;
import de.todo.engine.event.CollisionEvent;
import de.todo.engine.input.IInputState;
import de.todo.engine.input.KeyState;
import de.todo.engine.render.GLColor;
import de.todo.engine.render.definition.AnimationRenderDefinition;
import de.todo.engine.render.definition.TextureRenderDefinition;
import de.todo.engine.render.mesh.Mesh;
import de.todo.engine.render.mesh.RectangularMesh;
import de.todo.engine.utility.DebugStatistics;
import de.todo.engine.utility.UpdateMode;
import org.joml.Vector2f;

public class PlayerShip extends BaseTopdownPlayer {

    public static final int TYPE_PLAYERSHIP = Collider.getNextFreeColliderType();
    private static final Mesh MESH = new RectangularMesh(85.8f, 30.75f);

    private final PlayerShield shield;
    private final Lifebar lifebar;
    private boolean shooting = false;
    private long lastShot;

    public PlayerShip(final Lifebar lifebar) {
        super(
                MESH,
                new Vector2f(Main.WINDOW_WIDTH / 4.0f, Main.WINDOW_HEIGHT / 2.0f),
                350.0f
        );

        this.lifebar = lifebar;

        setUpdateMode(UpdateMode.ALWAYS);

        setRenderDefinition(new AnimationRenderDefinition(Animation.loadAnimation("/Player/", "Spaceship_0", "png", 4)));

        setCollider(new PlayerCollider(this));
        setCollision(true);

        lastShot = System.currentTimeMillis();

        shield = new PlayerShield(getPosition());

        setChildren(shield);
    }

    @Override
    public void update() {
        super.update();

        if (shooting && System.currentTimeMillis() - lastShot >= 500) {
            addChildren(new PlayerProjectile(position.x, position.y));

            shooting = false;
            lastShot = System.currentTimeMillis();
        }
    }

    @Override
    public void onCollision(final CollisionEvent event) {
        if (event.getCollisionAxis() != CollisionHandler.CollisionAxis.NONE && (
                event.getType() == EnemyProjectile.TYPE_PROJECTILE ||
                        event.getType() == Asteroid.TYPE_ASTEROID ||
                        event.getType() == EnemyShip.TYPE_ENEMY
        )) {
            onHit();
        }
    }

    @Override
    public void input(final IInputState inputState) {
        super.input(inputState);

        if (inputState.isKeyPressed(KeyState.KEY_SPACE)) {
            shooting = true;
        }
    }

    private void onHit() {
        shield.charge -= 1;

        if (shield.charge <= -1){
            lifebar.updateHearts(-1);
        }
    }

    private static final class PlayerCollider extends ObjectCollider {

        public PlayerCollider(final GameObject object) {
            super(object);
        }

        @Override
        public int getType() {
            return TYPE_PLAYERSHIP;
        }

        @Override
        public boolean canTrigger(final int type) {
            return type != PlayerProjectile.TYPE_PROJECTILE;
        }

    }

    private static final class PlayerShield extends GameObject {

        private final TextureRenderDefinition renderDefinition;

        private int charge = 5;

        public PlayerShield(final Vector2f position) {
            super(position);

            setLayer(0.55f);

            useRelativePosition(false);

            setMesh(MESH);

            renderDefinition = new TextureRenderDefinition("/Player/Spaceship_Shield.png");
            renderDefinition.setColorOverride(GLColor.GREEN);

            setRenderDefinition(renderDefinition);

            setScale(1.4f);
        }

        @Override
        public void update() {
            setPosition(parent.getPosition());

            if (charge < 4) renderDefinition.setColorOverride(GLColor.YELLOW);
            if (charge < 2) renderDefinition.setColorOverride(GLColor.RED);
            if (charge <= 0) disable();

            DebugStatistics.getInstance().appendCustomDebug("Shield: " + charge);
        }

    }

}
