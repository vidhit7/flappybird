import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class FlappyBird extends JPanel implements ActionListener,KeyListener{
    int boardWidth = 360;
    int boardHeight = 640;

    Image bottomPipeImg;
    Image topPipeImg;
    Image birdImg;
    Image backgroundImg;

    int velocityY = 0;
    int gravity = 1;

    class Bird{
        int x = boardWidth/8;
        int y = boardHeight/2;
        int  width = 34;
        int height = 24;
        Image img;

        Bird(Image img){
            this.img = img;
        }
    }

    //game logic
    Bird bird;
    Timer gameloop;
    FlappyBird(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        //setBackground(Color.BLUE);
        setFocusable(true);
        addKeyListener(this);

        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();

        bird = new Bird(birdImg);
        gameloop = new Timer(1000/60, this);
        gameloop.start();
        

    }

    public void move(){
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);
        


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    //    System.out.println("text");
    }
    public void draw(Graphics g){
        g.drawImage(backgroundImg,0,0,boardWidth,boardHeight,null);
        g.drawImage(birdImg,bird.x,bird.y,bird.width,bird.height,null);
  
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        
    }

 

    @Override
    public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_SPACE){ 
            velocityY = -9;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
