import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; //this is going to used for storing the segments of the snake body
import java.util.Random;
import java.util.random.*; //this is going to be used for getting random x and y values to place our food on the screen
import javax.swing.*;

//we had a snake and food . now we need to make the snake move to make the snake move we need to create a game loop.so when we making the snake move then we are changing the x and y position of the snake and to reflect that on the frame we need to redraw the panel.so we draw the new rectangle with the updated x and y positions for that reason we need a game loop so that the game loop will constantly redraw the frames.to create game loop we need a timer.
public class SnakeGame extends JPanel implements ActionListener {
    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    int boardWidth;
    int boardHeight;
    int tileSize = 25;

    // Snake
    Tile snakeHead;

    // Food
    Tile food;
    Random random;

    // game logic
    Timer gameLoop;

    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight; // this keyword is used to distinguish the two boardwitdth,one as a parameter
                                        // and another as a field or a member of the snake game class
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);

        snakeHead = new Tile(5, 5);

        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Grid
        for (int i = 0; i < boardWidth / tileSize; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, boardHeight); // vertical
            g.drawLine(0, i * tileSize, boardWidth, i * tileSize); // horizontal
        }

        // food
        g.setColor(Color.red);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);

        // snake
        g.setColor(Color.green);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
    }

    public void placeFood() {
        food.x = random.nextInt(boardWidth / tileSize); // 600/25 = 24
        food.y = random.nextInt(boardHeight / tileSize);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }
}
