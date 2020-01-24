package de.lw.remake.objects;

import de.lw.remake.MainScene;
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
import de.todo.engine.utility.Timer;
import de.todo.engine.utility.UpdateMode;
import org.joml.Vector2f;

public class PlayerShip extends BaseTopdownPlayer {

    public static final int TYPE_PLAYERSHIP = Collider.getNextFreeColliderType();
    private static final Mesh MESH = new RectangularMesh(85.8f, 30.75f);

    private final PlayerShield shield;
    private final Lifebar lifebar;
    private boolean shooting = false;
    private boolean invincible = false;
    private long lastShot;
    private float resetInvinciblityTimer = 0.0f;
    private float invincibleTime = 10.5f;

    public PlayerShip(final Lifebar lifebar) {
        super(
                MESH,
                new Vector2f(MainScene.WINDOW_WIDTH / 4.0f, MainScene.WINDOW_HEIGHT / 2.0f),
                350.0f
        );

        this.lifebar = lifebar;

        setUpdateMode(UpdateMode.ALWAYS);

        setRenderDefinition(new AnimationRenderDefinition(Animation.loadAnimation("/Player/", "Spaceship_0", "png", 12)));

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

        if(invincible) {
            resetInvinciblityTimer += Timer.getTimeDelta();
        }

        if (resetInvinciblityTimer >= invincibleTime){
            invincible = false;
            resetInvinciblityTimer -= invincibleTime;

        }

        DebugStatistics.getInstance().appendCustomDebug(" " + invincible);
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
        if (inputState.isKeyPressed(KeyState.KEY_G)) {
            shield.charge = 5;
        }
    }

    private void onHit() {
        if(!invincible){
            shield.charge -= 1;

            if (shield.charge <= -1){
                shield.charge = 5;
                lifebar.updateHearts(-1);
                invincible = true;
            }
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

    private final class PlayerShield extends GameObject {

        private TextureRenderDefinition shieldRenderDefinition;
        private AnimationRenderDefinition invincibleShieldRenderDefinition;

        private int charge = 5;

        public PlayerShield(final Vector2f position) {
            super(position);

            setUpdateMode(UpdateMode.ALWAYS);
            setLayer(0.55f);

            useRelativePosition(false);

            setMesh(MESH);

            shieldRenderDefinition = new TextureRenderDefinition("/Player/Spaceship_Shield.png");
            invincibleShieldRenderDefinition = new AnimationRenderDefinition(Animation.loadAnimation("/Player/Invincible_Shield/", "Shield_0", "png", 24));


            setRenderDefinition(shieldRenderDefinition);

            setScale(1.2f);
        }

        @Override
        public void update() {
            setPosition(parent.getPosition());
            boolean shouldShow = true;

            if (invincible) {
                setRenderDefinition(invincibleShieldRenderDefinition);
            } else {
                setRenderDefinition(shieldRenderDefinition);
                if (charge >= 4) shieldRenderDefinition.setColorOverride(GLColor.GREEN);
                if (charge < 4) shieldRenderDefinition.setColorOverride(GLColor.YELLOW);
                if (charge < 2) shieldRenderDefinition.setColorOverride(GLColor.RED);
                if (charge == 0) {
                    shouldShow = false;
                }

            }
            if(shouldShow){
                shieldRenderDefinition.show();
            } else {
                shieldRenderDefinition.hide();
            }

        }

    }

}
