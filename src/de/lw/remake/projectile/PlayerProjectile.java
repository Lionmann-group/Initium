package de.lw.remake.projectile;

import de.todo.engine.collision.Collider;

public class PlayerProjectile extends BaseProjectile {

    public static final int TYPE_PROJECTILE = Collider.getNextFreeColliderType();

    public PlayerProjectile(final float x, final float y) {
        super(x, y, 1, TYPE_PROJECTILE);
    }

}
