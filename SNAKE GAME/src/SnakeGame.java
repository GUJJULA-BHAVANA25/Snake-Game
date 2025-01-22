import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; //this is going to used for storing the segments of the snake body
import java.util.random.*; //this is going to be used for getting random x and y values to place our food on the screen
import javax.swing.*;

public class SnakeGame extends JPanel {
    int boardWidth;
    int boardHeight;

    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight; // this keyword is used to distinguish the two boardwitdth,one as a parameter
                                        // and another as a field or a member of the snake game class
    }
}
