package de.lw.remake.objects;

import de.todo.engine.animation.Animation;
import de.todo.engine.render.definition.AnimationRenderDefinition;
import de.todo.engine.render.definition.TextureRenderDefinition;
import de.todo.engine.render.mesh.SquareMesh;
import de.todo.engine.render.texture.TextureFactory;
import de.todo.engine.utility.UpdateMode;
import org.joml.Vector2f;

public class EnemyBoss extends BaseShip {

    private Vector2f position1,position2,position3,position4;

    public EnemyBoss(float x, float y) {
        super(x, y);

        useRelativePosition(false);

        setLayer(0.5f);
        setMesh(new SquareMesh(45));
        setRenderDefinition(new AnimationRenderDefinition(Animation.
                createAnimation(TextureFactory.getInstance().
                        loadTextures("/Enemies/Remillia.gif"),10)));


        setUpdateMode(UpdateMode.ALWAYS);

    }

}
