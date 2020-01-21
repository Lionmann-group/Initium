package de.lw.remake.objects;

import de.lw.remake.Main;
import de.todo.engine.entities.GameObject;
import de.todo.engine.render.definition.TextureRenderDefinition;
import de.todo.engine.render.mesh.RectangularMesh;
import org.joml.Vector2f;

public final class StarBackground extends GameObject {

    public StarBackground() {
        super(Main.WINDOW_WIDTH + 100, Main.WINDOW_HEIGHT / 2.0f);

        setLayer(0.1f);
        setMesh(new RectangularMesh(2600, 1460));
        setRenderDefinition(new TextureRenderDefinition("/Background/AGN.jpg"));
    }

    @Override
    public void update() {
        move(new Vector2f(-0.5f, 0.0f));
    }

}
