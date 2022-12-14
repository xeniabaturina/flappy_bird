package org.example.logic;

import org.example.logic.column_manager.ColumnManager;
import org.example.model.Bird;
import org.example.model.Column;
import org.example.model.Game;
import org.example.model.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Painter {

    private final Color skyColor = Color.cyan;
    private final Color groundColor = Color.orange;
    private final Color grassColor = Color.green;
    private final Color birdColor = Color.red;
    private final Color columnColor = Color.green.darker();
    private final Color titleColor = Color.white;

    private final Game game;
    private final ColumnManager columnManager;
    private final Bird bird;
    private final Screen screen;

    public Painter(Game game, ColumnManager columnManager, Bird bird, Screen screen) {
        this.game = game;
        this.columnManager = columnManager;
        this.bird = bird;
        this.screen = screen;
    }

    private BufferedImage crop(BufferedImage img, int x, int y, int newW, int newH) {
        BufferedImage tmp = img.getSubimage(x, y, newW, newH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    private BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    private void drawRectangle(Graphics graphics, Color color, Rectangle rectangle, String fileName) {
        if (!fileName.isEmpty()) {
            try {
                BufferedImage image;
                File path = new File("src/main/resources");
                image = ImageIO.read(new File(path, fileName));
                int imageWidth = rectangle.width;
                int imageHeight = rectangle.height;
                int imageX = rectangle.x;
                int imageY = rectangle.y;

                switch (fileName) {
                    case "bird.png":
                        image = resize(image, imageWidth / 2 * 3, imageHeight / 2 * 3);
                        imageX -= imageWidth / 4;
                        imageY -= imageHeight / 4;
                        break;
                    case "pillarUp.png":
                        image = crop(image, 0, image.getHeight() - imageHeight, imageWidth, imageHeight);
                        break;
                    case "pillarBot.png":
                        image = crop(image, 0, 0, imageWidth, imageHeight);
                        break;
                    default:
                        break;
                }

                graphics.drawImage(image, imageX, imageY, null);
                image.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            graphics.setColor(color);
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }

    private void drawTitle(Graphics graphics) {
        graphics.setColor(titleColor);
        graphics.setFont(new Font("Arial", Font.BOLD, 100));

        switch (game.getGameStatus()) {
            case INITIAL -> graphics.drawString("Click to start!", 75, screen.getHeight() / 2 - 50);
            case GAME_OVER -> graphics.drawString("Game Over!", 100, screen.getHeight() / 2 - 50);
            case GAME_PLAYING ->
                    graphics.drawString(String.valueOf(game.getPassedColumns()), screen.getWidth() / 2 - 25, 100);
        }
    }

    public void paint(Graphics graphics) {
        drawRectangle(graphics, skyColor, new Rectangle(0, 0, screen.getWidth(), screen.getHeight()), "");

        drawRectangle(graphics, groundColor, new Rectangle(0, screen.getHeight() - columnManager.getGrassHeight(), screen.getWidth(), columnManager.getGrassHeight()), "");

        drawRectangle(graphics, grassColor, new Rectangle(0, screen.getHeight() - columnManager.getGrassHeight(), screen.getWidth(), columnManager.getGrassHeight() / 6), "");

        drawRectangle(graphics, birdColor, bird.getBird(), "bird.png");

        for (Column column : columnManager.getColumns()) {
            drawRectangle(graphics, columnColor, column.getUpperColumn(), "pillarUp.png");
            drawRectangle(graphics, columnColor, column.getBottomColumn(), "pillarBot.png");
        }

        drawTitle(graphics);
    }
}
