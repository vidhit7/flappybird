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

    //for inititalizing the bird 
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
    
    //for initializing the pipes
    class  Pipe {
        int x = boardWidth;
        int y = 0;
        int width = 64;
        int height = 512;
        boolean passed = false;
        Image img;
        Pipe(Image img){
            this.img = img;
        }
    }    ArrayList<Pipe> pipes ;

    //game logic
    Pipe pipet;
    Pipe pipeb;


    Bird bird;
    Timer gameloop;
    Timer pipeTimer;
    int velocityX = -4;
    int velocityY = 0;
    int gravity = 1;

    //main constructor
    FlappyBird(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        //setBackground(Color.BLUE);
        setFocusable(true);
        addKeyListener(this);

        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();

        pipes = new ArrayList<>();
        bird = new Bird(birdImg);

        pipeTimer = new Timer(1500 , new ActionListener(){
            public void actionPerformed(ActionEvent e){
                placePipe();
            }
        });
        pipeTimer.start();

        gameloop = new Timer(1000/60, this);
        gameloop.start();
        

    }

    public void placePipe(){
        pipet = new Pipe(topPipeImg);
        int randomPipeY = (int)(pipet.y -pipet.height/4 - Math.random()*(pipet.height/2));
        pipet.y = randomPipeY;
        pipes.add(pipet);
        pipeb = new Pipe(bottomPipeImg);
      //  pipes.add(pipeb);
    }
 
    public void move(){
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);
         for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;
             } 
        }
        


    

    // rendering the images
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    //    System.out.println("text");
    }
    public void draw(Graphics g){
        g.drawImage(backgroundImg,0,0,boardWidth,boardHeight,null);
        g.drawImage(birdImg,bird.x,bird.y,bird.width,bird.height,null);
        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img,pipe.x,pipe.y,pipe.width,pipe.height,null);
        }
  
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
