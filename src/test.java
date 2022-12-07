import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
//        Search search=new Search(new int[][]{{0,0,0,0,0,},{0,13,4,3,5},{0,15,2,8,10},{0,9,14,1,6},{0,12,11,7,0}},15);
//        ArrayList<int[][]> a=search.doSearch();
//        for (int[][] b:a) {
//            for (int [] c:b) {
//                for (int d:c) {
//                    System.out.print(d);
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }





        int[][] array = getInput();
        Game_Frame game = new Game_Frame(array.length,array[0].length);
        game.createChessboard(array);


        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j]+"--");
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = i+j;
                game.showMove(array);
            }
        }



    }


//输入
    public static int[][] getInput(){

        Scanner input = new Scanner(System.in);
        int row = input.nextInt();
        int col = input.nextInt();
        int[][] array = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                array[i][j] = input.nextInt();
            }
        }
        int blockNumber = input.nextInt();
        int[] pivots = new int[blockNumber];
        int[][] blockSize = new int[blockNumber][2];

        for (int i = 0; i < blockNumber; i++) {
            pivots[i] = input.nextInt();
            String block = input.nextLine();
            blockSize[i][0] = block.charAt(1)-'0';
            blockSize[i][1] = block.charAt(3)-'0';
        }


        for (int i = 0; i < blockNumber; i++) {
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    if (pivots[i] == array[j][k]){
                        for (int l = j; l < j + blockSize[i][0]; l++) {
                            for (int m = k; m < k + blockSize[i][1]; m++) {
                                array[l][m]=-array[l][m];


                            }

                        }
                    }
                }
            }
        }

        input.close();
        return array;
    }
}
