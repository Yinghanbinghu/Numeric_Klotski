import javax.swing.*;
import java.awt.*;


public class Game_Frame extends JFrame{
        private int WIDTH ;
        private int HEIGTH ;
        private Dimension frame_SIZE ;
        private int GAME_SIZE ;

        public Game_Frame(int width, int height,int game_size){
            this.WIDTH = width;
            this.HEIGTH = height;
            this.GAME_SIZE = width/game_size;
            this.setTitle("Numeric_Klotski");
            this.setSize(WIDTH, HEIGTH);

            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setLayout(null);
            this.setAlwaysOnTop(true);
            this.setVisible(true);
        }

    }
