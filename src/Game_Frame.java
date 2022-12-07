import javax.swing.*;
import java.awt.*;



public class Game_Frame extends JFrame {
    private int WIDTH ;
    private int HEIGHT ;
    public JButton[][] chess;
    public JButton showStep = new JButton("Click");
    public   Container c = getContentPane();
    public  JPanel board = new JPanel();

    public Game_Frame(int row, int col) {
        this.WIDTH = 200*row;
        this.HEIGHT = 125*col;
        this.setTitle("Numeric_Klotski");
        this.setSize(WIDTH, HEIGHT);
        this.chess = new JButton[row][col];
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setAlwaysOnTop(true);
        this.addButton();
        this.setVisible(true);

    }

    public void setChessboard(int[][] array){
       initialChessboard();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if(array[i][j] != 0) {
                    chess[i][j].setText(String.valueOf(Math.abs(array[i][j])));
                    chess[i][j].setFont(new Font("Rockwell", Font.BOLD, 50));
                    this.repaint();
                    //针对Block,换颜色
                    if (array[i][j] < 0) {
                        chess[i][j].setBackground(Color.orange);
                        chess[i][j].setBorderPainted(false);
                        this.repaint();
                    }
                    else  {chess[i][j].setBackground(Color.lightGray); //背景颜色
                         this.repaint();
                    }}
                else {chess[i][j].setText(" ");
                    chess[i][j].setBackground(Color.lightGray); //背景颜色
                    this.repaint();
                     }
                    }

                }

            }




    public void createChessboard(int[][] array){

        board.setBounds(0,0,600,500);
        board.setLayout(new GridLayout(chess.length,chess[0].length,0,0));
        c.add(board);
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                chess[i][j] = new JButton();
                chess[i][j].setLocation(100*i,100*j);//位置，大小
                chess[i][j].setSize(100,100);
                chess[i][j].setEnabled(false);
                board.add(chess[i][j]);
            }
        }
        setChessboard(array);
        this.setVisible(true);

    }

    public void addButton(){
        showStep.setFont(new Font("Rockwell", Font.BOLD, 20));
        showStep.setSize(100,60);
        showStep.setLocation(650,100);
        this.add(showStep);
    }

    public void showMove(int[][] array){
        this.showStep.addActionListener((e) -> {
            setChessboard(array);
            // Y 在里面添加我鼠标点击该按钮之后要执行的命令语句
         setChessboard(array);

        });
    }

    public void initialChessboard(){
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                chess[i][j].setBackground(Color.lightGray);
                chess[i][j].setBorderPainted(true);
                this.repaint();
            }
        }
    }

}


