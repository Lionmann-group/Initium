package de.lw.remake.objects;

import de.lw.remake.MainScene;
import de.lw.remake.projectile.EnemyProjectile;
import de.todo.engine.collision.Collider;
import de.todo.engine.collision.CollisionHandler;
import de.todo.engine.collision.ObjectCollider;
import de.todo.engine.entities.GameObject;
import de.todo.engine.event.CollisionEvent;
import de.todo.engine.render.definition.TextureRenderDefinition;
import de.todo.engine.render.mesh.SquareMesh;
import de.todo.engine.utility.UpdateMode;
import org.joml.Vector2f;

public class EnemyShip extends BaseShip {

    public static final int TYPE_ENEMY = Collider.getNextFreeColliderType();

    // Class

    private final float shootInterval;
    private float shotTimer;
    private boolean alive = true;

    public EnemyShip(final float xOffset, final float y) {
        super(MainScene.WINDOW_WIDTH + 50 + xOffset, y);

        this.shootInterval = 1.5f + (float) (Math.random() * 2);

        useRelativePosition(false);

        setLayer(0.4f);
        setMesh(new SquareMesh(45));
        setRenderDefinition(new TextureRenderDefinition("/Enemies/EnemyShip.png"));

        setCollider(new EnemyShipCollider(this));
        setCollision(true);

        setUpdateMode(UpdateMode.ALWAYS);
    }

    @Override
    public void onCollision(final CollisionEvent event) {
        if (event.getCollisionAxis() != CollisionHandler.CollisionAxis.NONE) {
            die();
        }
    }

    @Override
    public void update() {
        move(new Vector2f(-128.0f, (float) (Math.sin(position.x / 20.0f) * 128.0f)));

        if (getRelativePosition().x < -50) {
            die();
        }

        shotTimer += super.timeDelta;

        if (shotTimer >= shootInterval) {
            shotTimer -= shootInterval;

            addChildren(new EnemyProjectile(position.x - 25, position.y));
        }
    }

    private void die() {
        EnemySpawner.ALIVE -= 1;
        alive = false;
        cleanup();
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    private static final class EnemyShipCollider extends ObjectCollider {

        public EnemyShipCollider(final GameObject object) {
            super(object);
        }

        @Override
        public int getType() {
            return TYPE_ENEMY;
        }

        @Override
        public boolean canTrigger(final int type) {
            return type != TYPE_ENEMY && type != EnemyProjectile.TYPE_PROJECTILE && type != Asteroid.TYPE_ASTEROID;
        }

        @Override
        public boolean isTrigger() {
            return true;
        }

    }

}
