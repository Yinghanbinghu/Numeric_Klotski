import java.util.Map;

public class Search {
    private int[] chain21;
    private int[] chain22;
    private int[] chain12;
    Map<Integer,int[][]> map;
    Map<Integer,String> way;
    int currentStep;

    public Search(int[] chain21, int[] chain22, int[] chain12,int[][] initial) {
        this.chain21 = chain21;
        this.chain22 = chain22;
        this.chain12 = chain12;
        map.put(0,initial);
        currentStep=0;
    }
    private Move currentCanMove(Move move){
        int[] loc=move.getLoc();
        int dir= move.getDir();
        int [][] currentMap=map.get(currentStep);
        for (int i = loc[0]; i < currentMap.length; i++) {
            for (int j = loc[1]; j < currentMap[i].length; j++) {
                for (int k = dir+1; k < 5; k++) {
                    if(currentMap[i][j]<100){
                        switch (k){
                            case 1:if (j>=2&&currentMap[i][j-1]==0) return new Move(i,j,k);
                            else break;
                        }
                    }
                }

            }
        }
        return null;
    }
}
class Move{
    private int[] loc;
    private int dir;      //1上 2右 3下 4左

    public Move(int row,int col, int dir) {
        this.loc = new int[]{row,col};
        this.dir = dir;
    }

    public int[] getLoc() {
        return loc;
    }

    public int getDir() {
        return dir;
    }
}

