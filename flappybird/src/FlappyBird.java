import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 360;
    int boardHeight = 640;

    Image bottomPipeImg;
    Image topPipeImg;
    Image birdImg;
    Image backgroundImg;

    // Bird class
    class Bird {
        int x = boardWidth / 8;
        int y = boardHeight / 2;
        int width = 34;
        int height = 24;
        Image img;

        Bird(Image img) {
            this.img = img;
        }
    }

    // Pipe class
    class Pipe {
        int x = boardWidth;
        int y = 0;
        int width = 64;
        int height = 512;
        boolean passed = false;
        Image img;

        Pipe(Image img) {
            this.img = img;
        }
    }

    ArrayList<Pipe> pipes;
    Bird bird;
    Timer gameloop;
    Timer pipeTimer;
    boolean gameOver = false;
    double score = 0;
    int velocityX = -4;
    int velocityY = 0;
    int gravity = 1;

    // Constructor
    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();

        pipes = new ArrayList<>();
        bird = new Bird(birdImg);

        pipeTimer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                placePipe();
            }
        });
        pipeTimer.start();

        gameloop = new Timer(1000 / 60, this);
        gameloop.start();
    }

    public void placePipe() {
        Pipe pipet = new Pipe(topPipeImg);
        int randomPipeY = (int) (pipet.y - pipet.height / 4 - Math.random() * (pipet.height / 2));
        pipet.y = randomPipeY;
        pipes.add(pipet);

        int opening = pipet.height / 4;
        Pipe pipeb = new Pipe(bottomPipeImg);
        pipeb.y = pipet.y + pipet.height + opening;
        pipes.add(pipeb);
    }

    public void move() {
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;

            if(!pipe.passed && bird.x >pipe.x+pipe.width){
                pipe.passed = true;
                score += 0.5;
            }

            // **Fixed Collision Detection**
            if (collision(bird, pipe)) {
                gameOver = true;
                return;
            }
        }

        if (bird.y > boardHeight) {
            gameOver = true;
        }
    }

    public boolean collision(Bird a, Pipe b) {
        return a.x < b.x + b.width &&
               a.x + a.width > b.x &&
               a.y < b.y + b.height &&
               a.y + a.height > b.y;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);
        g.drawImage(birdImg, bird.x, bird.y, bird.width, bird.height, null);
        for (Pipe pipe : pipes) {
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        if(gameOver){
            g.drawString("Game Over" +String.valueOf((int)score),10,35 );
        }
        else{
            g.drawString(String.valueOf((int)score),10,35 );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            gameloop.stop();
            pipeTimer.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
