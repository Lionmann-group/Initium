package de.lw.remake.objects;

import de.todo.engine.animation.Animation;
import de.todo.engine.entities.GameObject;
import de.todo.engine.render.definition.AnimationRenderDefinition;
import de.todo.engine.render.definition.RenderDefinition;
import de.todo.engine.render.definition.TextureRenderDefinition;
import de.todo.engine.render.mesh.SquareMesh;
import de.todo.engine.render.texture.TextureFactory;
import de.todo.engine.time.TimedUpdate;
import de.todo.engine.utility.UpdateMode;
import org.joml.Vector2f;

public class EnemyBoss extends BaseShip {

    private final int
            MOVEMENT_TYPE_STANDING = 0,
            MOVEMENT_TYPE_MOVING = 1,
            MOVEMENT_TYPE_TELEPORTING = 2;

    private int currentMovementType = 0;
    private float moveInterval = 2f;

    private Vector2f positionTop = new Vector2f(975,175),
            positionLeft = new Vector2f(900,350),
            positionRight = new Vector2f(1050,350),
            positionBottom = new Vector2f(975,525);

    private final Vector2f[] positions = {
            positionTop, positionLeft, positionRight, positionBottom,
    };

    private Portal portalTop,portalRight,portalLeft,portalBottom;
    private TimedUpdate moveUpdate;
    private AnimationRenderDefinition renderDefinition;

    public EnemyBoss() {
        super(900,350);

        useRelativePosition(false);

        setLayer(0.5f);
        setMesh(new SquareMesh(120));
        renderDefinition = new AnimationRenderDefinition(Animation.
                createAnimation(TextureFactory.getInstance().
                        loadTextures("/Enemies/Remillia.gif"),8));
        setRenderDefinition(renderDefinition);
        setUpdateMode(UpdateMode.ALWAYS);

        //Portals
        portalTop = new Portal(positionTop);
        portalLeft = new Portal(positionLeft);
        portalRight = new Portal(positionRight);
        portalBottom = new Portal(positionBottom);

        //Timer
        moveUpdate = new TimedUpdate(moveInterval, (v) -> move());
        setChildren(portalBottom,portalLeft,portalRight,portalTop);

    }

    private void move(){
        if(currentMovementType == MOVEMENT_TYPE_STANDING){

        }else if(currentMovementType == MOVEMENT_TYPE_MOVING){
            //doesn't work yet
            move(new Vector2f(500,500));
        }else if(currentMovementType == MOVEMENT_TYPE_TELEPORTING){

        }
    }

    private Vector2f randomPosition() {
        return positions[(int) Math.floor(Math.random() * 4)];
    }

    @Override
    public void update() {
        moveUpdate.update(super.timeDelta);
    }

    private class Portal extends GameObject {

        private AnimationRenderDefinition portalRenderDefinition;

        public Portal(Vector2f position) {
            super(position);

            setLayer(0.4f);
            setScale(1f);
            setMesh(new SquareMesh(240));
            portalRenderDefinition  = new AnimationRenderDefinition(Animation.
                    createAnimation(TextureFactory.getInstance().
                            loadTextures("/Objects/Red_Portal.gif"),8));
            setRenderDefinition(portalRenderDefinition);
            setUpdateMode(UpdateMode.ALWAYS);

        }
    }

}
