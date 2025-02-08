import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int borderwidth = 360;
        int borderheight = 640;
        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(borderwidth, borderheight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
       
         
        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        flappyBird.requestFocus();
        frame.pack();
        frame.setVisible(true);


    }
}
