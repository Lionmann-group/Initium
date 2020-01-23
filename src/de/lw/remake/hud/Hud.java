package de.lw.remake.hud;

import de.lw.remake.MainScene;
import de.todo.engine.entities.GameObject;

public class Hud extends GameObject {

    private final Lifebar lifebar;

    public Hud() {
        super(MainScene.WINDOW_WIDTH / 2.0f, MainScene.WINDOW_HEIGHT - 32.0f);

        setChildren(
                lifebar = new Lifebar(0.0f, 0.0f)
        );
    }

    public final Lifebar getLifebar() {
        return lifebar;
    }

}
