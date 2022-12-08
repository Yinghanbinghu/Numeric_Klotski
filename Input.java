import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        int[][] initial=new int[sc.nextInt()][sc.nextInt()];
        int[][] aim=new int[initial.length][initial[0].length];
        int MaxBlock=0;
        for (int i = 0; i < initial.length; i++) {
            for (int j = 0; j < initial[0].length; j++) {
                initial[i][j]= sc.nextInt();
                MaxBlock= Math.max(initial[i][j], MaxBlock);
            }
        }
        int currentBlock=1;
        for (int i = 0; i < initial.length; i++) {
            for (int j = 0; j < initial[0].length; j++) {
                if(currentBlock<=MaxBlock) aim[i][j]=currentBlock++;
                else break;
            }
        }
        ArrayList<Integer> c21=new ArrayList<>();
        ArrayList<Integer> c12=new ArrayList<>();
        ArrayList<Integer> c22=new ArrayList<>();
        int BigBlock= sc.nextInt();
        for (int i = 0; i < BigBlock; i++) {
            int bigBlock= sc.nextInt();
            switch (sc.next()){
                case "2*1": c21.add(bigBlock);break;
                case "2*2": c22.add(bigBlock);break;
                case "1*2": c12.add(bigBlock);break;
            }
        }
        int[] C21;
        int[] C22;
        int[] C12;
        if (c21.size()==0) C21=null;
        else {
            C21=new int[c21.size()];
            for (int i = 0; i < c21.size(); i++) {
                C21[i]=c21.get(i);
                int l=C21[i]+initial[0].length;
                for (int j = 0; j < initial.length; j++) {
                    for (int k = 0; k < initial[0].length; k++) {
                        if (initial[j][k]==l) initial[j][k]*=-1;
                    }
                }
            }

        }
        if (c12.size()==0) C12=null;
        else {
            C12=new int[c12.size()];
            for (int i = 0; i < c12.size(); i++) {
                C12[i]=c12.get(i);
                int l=C12[i]+1;
                for (int j = 0; j < initial.length; j++) {
                    for (int k = 0; k < initial[0].length; k++) {
                        if (initial[j][k]==l) initial[j][k]*=-1;
                    }
                }
            }
        }
        if (c22.size()==0) C22=null;
        else {
            C22=new int[c22.size()];
            for (int i = 0; i < c22.size(); i++) {
                C22[i]=c22.get(i);
                int l1=C22[i]+initial[0].length;
                int l2=C22[i]+initial[0].length+1;
                int l3=C22[i]+1;
                for (int j = 0; j < initial.length; j++) {
                    for (int k = 0; k < initial[0].length; k++) {
                        if (initial[j][k]==l1||initial[j][k]==l2||initial[j][k]==l3) initial[j][k]*=-1;
                    }
                }
            }
        }

        BFS search=new BFS(initial,C12,C21,C22,aim);
        ArrayList<BFSArray2D> a=search.search();
        if(a==null) System.out.println("No");
        else {
            System.out.println("Yes");
            for (int i = a.size() - 1; i >= 0; i--
            ) {
                BFSArray2D b = a.get(i);
                for (int[] c : b.getMap()
                ) {
                    for (int d : c
                    ) {
                        System.out.print(d+"  ");
                    }
                    System.out.println();
                }
                System.out.println(b.move);
                System.out.println();
            }
        }
    }
}

