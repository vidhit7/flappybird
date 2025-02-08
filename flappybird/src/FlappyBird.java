import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends JPanel{
    int boardWidth = 360;
    int boardHeight = 640;
    FlappyBird(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLUE);
    }
}
