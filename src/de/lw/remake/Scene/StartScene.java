package de.lw.remake.Scene;

import de.lw.remake.Main;
import de.lw.remake.objects.StarBackground;
import de.todo.engine.GameEngine;
import de.todo.engine.Window;
import de.todo.engine.entities.Camera;
import de.todo.engine.entities.TextObject;
import de.todo.engine.input.IInputState;
import de.todo.engine.input.KeyState;
import de.todo.engine.render.GLColor;
import de.todo.engine.render.RenderType;
import de.todo.engine.scene.Scene;
import org.joml.Vector2f;

import java.awt.Font;

public class StartScene extends Scene {

    Font headlineFont,basicFont,smallFont;
    TextObject gameName,versionNumber,firstOption,secondOption,thirdOption;

    private void initTextobjects(){
        gameName = new TextObject("Placeholder_game",new Vector2f(),RenderType.STATIC,headlineFont,GLColor.WHITE);
        versionNumber = new TextObject("a0.1.0",new Vector2f(),RenderType.STATIC,smallFont,GLColor.WHITE);
        firstOption = new TextObject("1",new Vector2f(),RenderType.STATIC,basicFont,GLColor.WHITE);
        secondOption = new TextObject("2",new Vector2f(),RenderType.STATIC,basicFont,GLColor.WHITE);
        thirdOption = new TextObject("3",new Vector2f(),RenderType.STATIC,basicFont,GLColor.WHITE);
    }

    private void initFonts(){
        headlineFont = new Font(Font.DIALOG_INPUT, Font.BOLD, 36);
        basicFont = new Font(Font.DIALOG_INPUT, Font.BOLD, 24);
        smallFont = new Font(Font.DIALOG_INPUT, Font.BOLD, 10);
    }

    @Override
    protected void initScene(Window.WindowDimensions windowDimensions) throws Exception {

        final float WIDTH = windowDimensions.getWidth();
        final float HEIGHT = windowDimensions.getHeight();

        setActiveCamera(new Camera(0, 0));
        initFonts();
        initTextobjects();

        //Vectors positions for the TextObjects
        gameName.setPosition(new Vector2f(
                WIDTH / 2.0f - gameName.getWidth() / 2.0f,
                HEIGHT / 2.0f/2/2 - gameName.getHeight() / 2.0f
        ));
        versionNumber.setPosition(new Vector2f(
                0,0
        ));
        firstOption.setPosition(new Vector2f(
                windowDimensions.getWidth() / 2.0f - firstOption.getWidth() / 2.0f,
                windowDimensions.getHeight() / 2.0f/2 - firstOption.getHeight() / 2.0f
        ));
        secondOption.setPosition(new Vector2f(
                windowDimensions.getWidth() / 2.0f - secondOption.getWidth() / 2.0f,
                windowDimensions.getHeight() / 2.0f - secondOption.getHeight() / 2.0f
        ));


        addGameObjects(gameName, versionNumber, secondOption, firstOption, new StarBackground());

    }

    @Override
    public void input(IInputState inputState) throws Exception {
        if (inputState.isKeyReleased(KeyState.KEY_BACKSPACE)) {
            System.exit(0);
        }
        if (inputState.isKeyReleased(KeyState.KEY_1)){
            GameEngine.getInstance().setScene(new Main());
        }
        if (inputState.isKeyReleased(KeyState.KEY_2)){
            GameEngine.getInstance().setScene(new Main());
        }
        if (inputState.isKeyReleased(KeyState.KEY_3)){
            GameEngine.getInstance().setScene(new Main());
        }

    }
}