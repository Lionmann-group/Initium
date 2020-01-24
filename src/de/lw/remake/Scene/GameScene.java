package de.lw.remake.Scene;

import de.lw.remake.Level;
import de.todo.engine.Window;
import de.todo.engine.input.IInputState;
import de.todo.engine.scene.Scene;

public class GameScene extends Scene {

    private Level level;

    public GameScene(Level level){
        this.level = level;
    }

    @Override
    protected void initScene(Window.WindowDimensions windowDimensions) throws Exception {

    }

    @Override
    public void input(IInputState iInputState) throws Exception {

    }
}
