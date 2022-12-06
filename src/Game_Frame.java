import javax.swing.*;
import java.awt.*;



public class Game_Frame extends JFrame {
    private int WIDTH ;
    private int HEIGHT ;
    public JButton[][] chess;
    public JButton showStep = new JButton("Click");

    public Game_Frame(int row, int col) {
        this.WIDTH = 150*row;
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

    public void setChessboard(int[][] array,int pivot,int block_row,int block_col){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if(array[i][j]!=0){
                    chess[i][j].setText(String.valueOf(array[i][j]));
                    chess[i][j].setFont(new Font("Rockwell", Font.BOLD, 70));
                    //针对Block,换颜色
                    if(array[i][j] == pivot){
                        for (int k = i; k < i+block_row; k++) {
                            for (int l = j; l < j+block_col ; l++) {
                                chess[k][l].setBackground(Color.orange);
                                chess[i][j].setBorderPainted(false);
                            }
                        }
                    }

                }
            }
        }
    }



    public void initialChessboard(int[][] array,int pivot,int block_row,int block_col){

        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess.length; j++) {
                chess[i][j] = new JButton();
                chess[i][j].setBackground(Color.lightGray); //背景颜色
                chess[i][j].setLocation(100*i,100*j);//位置，大小
                chess[i][j].setSize(100,100);
                chess[i][j].setEnabled(false);
                this.add(chess[i][j]);
            }
        }
        setChessboard(array,pivot,block_row,block_col);
        setVisible(true);

    }

    public void addButton(){
        showStep.setFont(new Font("Rockwell", Font.BOLD, 20));
        showStep.setSize(100,60);
        showStep.setLocation(550,100);
        this.add(showStep);
    }

    public void showMove(int[][] array,int pivot,int block_row,int block_col){
        this.showStep.addActionListener((e) -> {
            setChessboard(array,pivot,block_row,block_col);
            // Y 在里面添加我鼠标点击该按钮之后要执行的命令语句


        });
    }}


