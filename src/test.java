import java.util.ArrayList;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        Search search=new Search(new int[][]{{0,0,0,0,0,},{0,13,4,3,5},{0,15,2,8,10},{0,9,14,1,6},{0,12,11,7,0}},15);
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
