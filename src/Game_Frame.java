import javax.swing.*;
import java.awt.*;


public class Game_Frame extends JFrame{
     //JFrame用于生成一个窗体（Y）

        private int WIDTH;
        private int HEIGTH;
//        public  int frame_SIZE;
        private Dimension frame_SIZE ;

        public Game_Frame(int width, int height){
            this.WIDTH = width;
            this.HEIGTH = height;
         //   this.frame_SIZE = new Dimension(width,height);
            setTitle("Numeric_Klotski");
            setSize(WIDTH, HEIGTH); //设置窗体的大小（Y）
            setLocationRelativeTo(null); // Center the window.
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
            setLayout(null);
            this.setAlwaysOnTop(true);
            this.setVisible(true);
        }











    }
