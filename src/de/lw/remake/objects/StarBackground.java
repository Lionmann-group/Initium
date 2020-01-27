package de.lw.remake.objects;

import de.lw.remake.MainScene;
import de.todo.engine.entities.GameObject;
import de.todo.engine.render.definition.TextureRenderDefinition;
import de.todo.engine.render.mesh.RectangularMesh;
import org.joml.Vector2f;

public final class StarBackground extends GameObject {

    GameObject stars,planets;

    public StarBackground() {
        super(MainScene.WINDOW_WIDTH + 100, MainScene.WINDOW_HEIGHT / 2.0f);
        setLayer(0.1f);
        setMesh(new RectangularMesh(3600, 700));
        setRenderDefinition(new TextureRenderDefinition("/Background/Background.png"));

        stars = new GameObject(0,0);
        stars.setLayer(0.11f);
        stars.setMesh(new RectangularMesh(3600, 700));
        stars.setRenderDefinition(new TextureRenderDefinition("/Background/Background_Star.png"));

        planets = new GameObject(0,0);
        planets.setLayer(0.12f);
        planets.setMesh(new RectangularMesh(3600, 700));
        planets.setRenderDefinition(new TextureRenderDefinition("/Background/Background_Planets.png"));

        addChildren(stars,planets);
    }

    @Override
    public void update()
    {
        stars.move(new Vector2f(-1.0f, 0.0f));
        planets.move(new Vector2f(-10.0f, 0.0f));
    }

}
