package de.lw.remake.hud;

import de.todo.engine.entities.GameObject;
import de.todo.engine.render.definition.TextureRenderDefinition;
import de.todo.engine.render.mesh.SquareMesh;

public class Lifebar extends GameObject {

    public Lifebar(final float x, final float y) {
        super(x, y);

        setLayer(0.8f);

        setChildren(
                new Heart(-(32.0f + 3.0f), 0.0f),
                new Heart(0.0f, 0.0f),
                new Heart(32.0f + 3.0f, 0.0f)
        );
    }

    private static final class Heart extends GameObject {

        public Heart(final float x, final float y) {
            super(x, y);

            setMesh(new SquareMesh(32));
            setRenderDefinition(new TextureRenderDefinition("/Lifebar/Heart.png"));
        }

    }

}
