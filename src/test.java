import java.util.ArrayList;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        Search search=new Search(new int[][]{{0,0,0},{0,1,2},{0,0,3}},3);
        ArrayList<int[][]> a=search.doSearch();
        for (int[][] b:a
             ) {
            for (int [] c:b
                 ) {
                for (int d:c
                     ) {
                    System.out.print(d);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
