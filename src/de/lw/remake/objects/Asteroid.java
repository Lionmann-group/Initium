package de.lw.remake.objects;

import de.lw.remake.MainScene;
import de.todo.engine.collision.Collider;
import de.todo.engine.collision.CollisionHandler;
import de.todo.engine.collision.ObjectCollider;
import de.todo.engine.entities.GameObject;
import de.todo.engine.event.CollisionEvent;
import de.todo.engine.render.definition.TextureRenderDefinition;
import de.todo.engine.render.mesh.RectangularMesh;
import de.todo.engine.utility.UpdateMode;
import org.joml.Vector2f;

public class Asteroid extends GameObject {

    public static final int TYPE_ASTEROID = Collider.getNextFreeColliderType();

    private final float speed, rotation;

    public Asteroid(final float xOffset, final float y) {
        super(MainScene.WINDOW_WIDTH + xOffset, y);

        useRelativePosition(false);

        setUpdateMode(UpdateMode.ALWAYS);

        setMesh(new RectangularMesh(72.6f, 66.7f));
        setRenderDefinition(new TextureRenderDefinition("/Enemies/Asteroid.png"));

        final float rnd = (float) (Math.random() * 1.0);

        setLayer(0.35f + ((1.0f - rnd) / 100.0f));

        this.rotation = 180.0f * (0.2f + (1.0f - rnd));
        this.speed = 256.0f * (0.2f + (1.0f - rnd));
        setScale(0.2f + rnd);

        setCollider(new AsteroidCollider(this));

        setCollision(true);
    }

    @Override
    public void update() {
        setRotationZ(getRotation().z + (rotation * super.timeDelta));
        move(new Vector2f(-speed, 0.0f));

        if (getRelativePosition().x < -50) {
            cleanup();
        }
    }

    @Override
    public void onCollision(CollisionEvent event) {
        if (event.getCollisionAxis() != CollisionHandler.CollisionAxis.NONE) disable();
    }

    private static final class AsteroidCollider extends ObjectCollider {

        public AsteroidCollider(final GameObject object) {
            super(object);
        }

        @Override
        public int getType() {
            return TYPE_ASTEROID;
        }

        @Override
        public boolean canTrigger(final int type) {
            return type != TYPE_ASTEROID;
        }

    }

}
