package de.lw.remake.projectile;

import de.todo.engine.collision.Collider;

public class EnemyProjectile extends BaseProjectile {

    public static final int TYPE_PROJECTILE = Collider.getNextFreeColliderType();

    public EnemyProjectile(final float x, final float y) {
        super(x, y, -1, TYPE_PROJECTILE);

        setRotationZ(180.0f);
    }

}
