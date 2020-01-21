package de.lw.remake.projectile;

import de.todo.engine.collision.CollisionHandler;
import de.todo.engine.collision.ObjectCollider;
import de.todo.engine.entities.GameObject;
import de.todo.engine.event.CollisionEvent;
import de.todo.engine.render.definition.TextureRenderDefinition;
import de.todo.engine.render.mesh.RectangularMesh;
import org.joml.Vector2f;

public class BaseProjectile extends GameObject {

    // Class

    private final int direction;

    public BaseProjectile(final float x, final float y, final int direction, final int type) {
        super(x, y);

        this.direction = direction;

        useRelativePosition(false);

        setMesh(new RectangularMesh(79.3f, 17.6f));
        setRenderDefinition(new TextureRenderDefinition("/Objects/Shot.png"));
        setScale(0.2f);

        setCollider(new ProjectileCollider(this, type));
        setCollision(true);
    }

    @Override
    public void update() {
        move(new Vector2f(256.0f * direction, 0.0f));
    }

    @Override
    public void onCollision(final CollisionEvent event) {
        if (event.getCollisionAxis() != CollisionHandler.CollisionAxis.NONE) {
            disable();
        }
    }

    static final class ProjectileCollider extends ObjectCollider {

        private final int type;

        public ProjectileCollider(final GameObject object, final int type) {
            super(object);

            this.type = type;
        }

        @Override
        public boolean canTrigger(final int type) {
            return type != this.type;
        }

        @Override
        public int getType() {
            return type;
        }

    }

}
