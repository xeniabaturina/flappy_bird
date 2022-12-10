package org.example;


import org.example.controller.FlappyBirdController;
import org.example.logic.Renderer;
import org.example.model.Screen;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jframe = new JFrame();
        FlappyBirdController controller = new FlappyBirdController();
        Screen screen = controller.getScreen();

        Renderer renderer = controller.getRenderer();

        jframe.add(renderer);
        jframe.setTitle("Flappy Bird");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(screen.getWidth(), screen.getHeight());
        jframe.addMouseListener(controller);
        jframe.addKeyListener(controller);
        jframe.setResizable(false);
        jframe.setVisible(true);

        controller.start();

        System.out.println("Hello world!");
    }
}
