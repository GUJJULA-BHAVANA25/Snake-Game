import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; //this is going to used for storing the segments of the snake body
import java.util.Random; //this is going to be used for getting random x and y values to place our food on the screen
import javax.swing.*;

//we had a snake and food . now we need to make the snake move to make the snake move we need to create a game loop.so when we making the snake move then we are changing the x and y position of the snake and to reflect that on the frame we need to redraw the panel.so we draw the new rectangle with the updated x and y positions for that reason we need a game loop so that the game loop will constantly redraw the frames.to create game loop we need a timer.
public class SnakeGame extends JPanel implements ActionListener, KeyListener {
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
    ArrayList<Tile> snakeBody;

    // Food
    Tile food;
    Random random;

    // game logic
    Timer gameLoop;
    int velocityX;
    int velocityY;
    boolean gameOver = false;

    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight; // this keyword is used to distinguish the two boardwitdth,one as a parameter
                                        // and another as a field or a member of the snake game class
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();

        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 0;

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Grid
        // for (int i = 0; i < boardWidth / tileSize; i++) {
        // g.drawLine(i * tileSize, 0, i * tileSize, boardHeight); // vertical
        // g.drawLine(0, i * tileSize, boardWidth, i * tileSize); // horizontal
        // }

        // food
        g.setColor(Color.red);
        // g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);
        g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize, true);

        // snake head
        g.setColor(Color.green);
        // g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize,
        // tileSize);
        g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize, true);

        // snake Body
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            // g.fillRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize,
            // tileSize);
            g.fill3DRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize, true);
        }

        // score
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if (gameOver) {
            g.setColor(Color.red);
            g.drawString("Game Over: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        } else {
            g.drawString("Score: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        }
    }

    public void placeFood() {
        food.x = random.nextInt(boardWidth / tileSize); // 600/25 = 24
        food.y = random.nextInt(boardHeight / tileSize);
    }

    public boolean collision(Tile tile1, Tile tile2) { // to detect collision between snake body and head
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move() {
        // eat food
        if (collision(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        // snake body moving
        // iterate backward
        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if (i == 0) {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            } else {
                Tile prevSnakePart = snakeBody.get(i - 1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        // snake head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        // game over conditions
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            // collide with the snake head
            if (collision(snakeHead, snakePart)) {
                gameOver = true;
            }
        }

        if (snakeHead.x * tileSize < 0 || snakeHead.x * tileSize > boardWidth || snakeHead.y * tileSize < 0
                || snakeHead.y * tileSize > boardHeight) {
            gameOver = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    // do not need
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
