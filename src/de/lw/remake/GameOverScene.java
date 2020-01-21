package de.lw.remake;

import de.todo.engine.Window;
import de.todo.engine.entities.TextObject;
import de.todo.engine.input.IInputState;
import de.todo.engine.input.KeyState;
import de.todo.engine.render.GLColor;
import de.todo.engine.render.RenderType;
import de.todo.engine.scene.Scene;
import org.joml.Vector2f;

import java.awt.*;

public class GameOverScene extends Scene {

    @Override
    protected void initScene(final Window.WindowDimensions windowDimensions) throws Exception {
        final TextObject to = new TextObject(
                "Game Over!",
                new Vector2f(),
                RenderType.STATIC,
                new Font(Font.DIALOG_INPUT, Font.BOLD, 24),
                GLColor.WHITE
        );

        to.setPosition(new Vector2f(
                windowDimensions.getWidth() / 2.0f - to.getWidth() / 2.0f,
                windowDimensions.getHeight() / 2.0f - to.getHeight() / 2.0f
        ));

        addGameObjects(to);
    }

    @Override
    public void input(final IInputState inputState) throws Exception {
        if (inputState.isKeyReleased(KeyState.KEY_ENTER)) Main.ENGINE.setScene(new Main());
    }

}
