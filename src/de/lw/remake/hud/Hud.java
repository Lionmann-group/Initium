package de.lw.remake.hud;

import de.lw.remake.Main;
import de.todo.engine.entities.GameObject;

public class Hud extends GameObject {

    public Hud() {
        super(Main.WINDOW_WIDTH / 2.0f, Main.WINDOW_HEIGHT - 32.0f);

        setChildren(
                new Lifebar(0.0f, 0.0f)
        );
    }



}
