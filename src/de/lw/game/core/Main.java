package de.lw.game.core;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        ImageRepository.getInstance();

        SwingUtilities.invokeLater(() -> new DrawMainCanvas().run());
    }

}