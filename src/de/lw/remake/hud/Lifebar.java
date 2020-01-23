package de.lw.remake.hud;

import de.lw.remake.Scene.GameOverScene;
import de.lw.remake.Main;
import de.todo.engine.entities.GameObject;
import de.todo.engine.render.definition.TextureRenderDefinition;
import de.todo.engine.render.mesh.SquareMesh;

public class Lifebar extends GameObject {

    private int hearts = 2;

    public Lifebar(final float x, final float y) {
        super(x, y);

        setLayer(0.8f);

        setChildren(
                new Heart(-(32.0f + 3.0f), 0.0f),
                new Heart(0.0f, 0.0f),
                new Heart(32.0f + 3.0f, 0.0f)
        );
    }

    public void updateHearts(final int factor) {
        if (factor < 0) {
            getChildren()[hearts].disable();
            hearts --;

            if (hearts <= -1) Main.ENGINE.setScene(new GameOverScene());
        } else if (factor > 0 && hearts < 2) {
            getChildren()[hearts].enable();
            hearts ++;
        }
    }

    private static final class Heart extends GameObject {

        public Heart(final float x, final float y) {
            super(x, y);

            setLayer(0.9f);

            setMesh(new SquareMesh(32));
            setRenderDefinition(new TextureRenderDefinition("/Lifebar/Heart.png"));
        }

    }

}
