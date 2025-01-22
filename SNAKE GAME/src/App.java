import javax.swing.*; //to import window

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 600;
        int boardHeight = boardWidth; // size of the window in pixels

        JFrame frame = new JFrame("Snake");
        frame.setVisible(true); // making the frame visible
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null); // this is going to opwn up the window at the center of the screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this is going to set that to the program will terminate
                                                              // when the user click on x button on the window

    }
}
