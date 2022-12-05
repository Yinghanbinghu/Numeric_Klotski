import java.util.ArrayList;


public class Search {
    /*private int[] chain21;    //2高1宽
    private int[] chain22;    //2高2宽
    private int[] chain12;    //1高2宽*/
    ArrayList<int[][]> map;
    ArrayList<Move> way;
    int currentStep;
    ArrayList<Integer> hasFound;
    int hasNum;

    public Search(/*int[] chain21, int[] chain22, int[] chain12,*/ int[][] initial,int hasNum) {       //初始化map    方块中-1对应0 其余数字取绝对值
        /*this.chain21 = chain21;
        this.chain22 = chain22;
        this.chain12 = chain12;*/
        map=new ArrayList<>();
        map.add(initial);
        /*map=new HashMap<>();
        map.put(0, initial);*/
        way=new ArrayList<>();
        hasFound=new ArrayList<>();
        currentStep = 0;
        this.hasNum=hasNum;
    }

    public ArrayList<int[][]> doSearch() {
        Search:while (true) {
            Move move=way.size()-1==(currentStep)? way.get(currentStep):new Move(1,1,0);
            int[][] nextMap=currentMove(move);
            if (nextMap==null){
                if(currentStep==0){
                    return null;
                }
                map.remove(currentStep);
                way.remove(currentStep);
                currentStep--;
                continue Search;
            }else {
                Integer h=mapHash(nextMap);
                if(hasFound!=null) {
                    for (Integer i : hasFound
                    ) {
                        if (i.compareTo(h) == 0) {
                            /*map.remove(currentStep);
                            way.remove(currentStep);
                            currentStep--;*/
                            continue Search;
                        }
                    }
                }
                hasFound.add(h);
                currentStep++;
                map.add(nextMap);
                if(ifOrder(nextMap)){
                    return map;
                }
            }
        }
    }
    private Integer mapHash(int[][] a){
        int h=0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if(a[i][j]==0) h ^= ((h << 8) ^ 100) + h;
                else h^=((h<<8)^a[i][j])+h;

            }
        }
        return h;
    }

    private boolean ifOrder(int[][] nextMap) {
        int currentNum = 1;
        for (int i=1;i< nextMap.length;i++
        ) {
            for (int j =1;j< nextMap[i].length;j++
            ) {
                if (currentNum<=hasNum?nextMap[i][j] == currentNum:nextMap[i][j]==0) {
                    currentNum++;
                } else return false;
            }
        }
        return true;
    }


    private int[][] currentMove(Move move) {            //从当前的移动开始向后遍历
        int[] loc = move.getLoc();
        int dir = move.getDir();
        int[][] currentMap = map.get(currentStep);
        int[][] nextMap = new int[currentMap.length][currentMap[0].length];
        for (int i = 0; i < currentMap.length; i++) {
            System.arraycopy(currentMap[i], 0, nextMap[i], 0, currentMap[i].length);
        }
        for (int i = loc[0]; i < nextMap.length; i++) {
            for (int j = i==loc[0]?loc[1]:1; j < nextMap[i].length; j++) {
                if (nextMap[i][j] > 0) {
                    for (int k =i==loc[0]&&j==loc[1]? dir + 1:1; k < 5; k++) {
                        boolean canMove=false;
                        switch (k) {                   //上下左右
                            case 1://↑
                                /*int a=currentMap[i][j];
                                if(i == 1 || nextMap[i - 1][j] != 0)
                                for (int l:chain21
                                     ) {
                                    if (l==a){
                                        nextMap[i-1]
                                    }
                                }*/
                                if (i >= 2 && nextMap[i - 1][j] == 0) {
                                    if (j == nextMap[i].length - 1 || nextMap[i][j + 1] >= 0) {
                                        nextMap[i - 1][j] = currentMap[i][j];
                                        nextMap[i][j] = 0;
                                        if (i < nextMap.length - 1 && nextMap[i + 1][j] < 0) {
                                            nextMap[i][j] = currentMap[i + 1][j];
                                            nextMap[i + 1][j] = 0;
                                        }
                                        canMove=true;
                                    } else if (nextMap[i - 1][j + 1] == 0) {
                                        nextMap[i - 1][j] = nextMap[i][j];
                                        nextMap[i - 1][j + 1] = nextMap[i][j + 1];
                                        if (i < nextMap.length - 1 && nextMap[i + 1][j] < 0) {
                                            nextMap[i][j] = nextMap[i + 1][j];
                                            nextMap[i][j + 1] = nextMap[i + 1][j + 1];
                                        }
                                        canMove=true;
                                    }
                                }
                                break;
                            case 2://→
                                if (j < nextMap[i].length - 1 && nextMap[i][j + 1] == 0) {
                                    if (i == nextMap.length - 1 || nextMap[i + 1][j] >= 0) {
                                        nextMap[i][j + 1] = currentMap[i][j];
                                        nextMap[i][j] = 0;
                                        canMove=true;
                                    } else if (nextMap[i + 1][j + 1] == 0) {
                                        nextMap[i][j + 1] = currentMap[i][j];
                                        nextMap[i + 1][j + 1] = currentMap[i + 1][j];
                                        nextMap[i][j] = 0;
                                        nextMap[i + 1][j] = 0;
                                        canMove=true;
                                    }


                                }
                                if (j < nextMap[i].length - 2 && nextMap[i][j + 1] < 0 && nextMap[i][j + 2] == 0) {

                                    if (i == nextMap.length - 1 || nextMap[i + 1][j] >= 0) {
                                        nextMap[i][j + 1] = currentMap[i][j];
                                        nextMap[i][j + 2] = currentMap[i][j + 1];
                                        nextMap[i][j] = 0;
                                        canMove=true;
                                    } else if (nextMap[i + 1][j + 2] == 0) {
                                        nextMap[i][j + 1] = currentMap[i][j];
                                        nextMap[i + 1][j + 1] = currentMap[i + 1][j];
                                        nextMap[i][j + 2] = currentMap[i][j + 1];
                                        nextMap[i + 1][j + 2] = currentMap[i + 1][j + 1];
                                        nextMap[i][j] = 0;
                                        nextMap[i + 1][j] = 0;
                                        canMove=true;
                                    }
                                }
                                break;
                            case 3://↓
                                if (i < nextMap.length - 1 && nextMap[i + 1][j] == 0) {
                                    if (j == nextMap[i].length - 1 || nextMap[i][j + 1] >= 0) {
                                        nextMap[i + 1][j] = currentMap[i][j];
                                        nextMap[i][j] = 0;
                                        canMove=true;
                                    } else if (nextMap[i + 1][j + 1] == 0) {
                                        nextMap[i + 1][j] = currentMap[i][j];
                                        nextMap[i][j] = 0;
                                        nextMap[i + 1][j + 1] = currentMap[i][j + 1];
                                        nextMap[i][j + 1] = 0;
                                        canMove=true;
                                    }
                                }
                                if (i < nextMap.length - 2 && nextMap[i + 1][j] < 0 && nextMap[i + 2][j] == 0) {
                                    if (j == nextMap[i].length - 1 || nextMap[i][j + 1] >= 0) {
                                        nextMap[i + 1][j] = currentMap[i][j];
                                        nextMap[i + 2][j] = currentMap[i + 1][j];
                                        nextMap[i][j] = 0;
                                        canMove=true;
                                    } else if (nextMap[i + 1][j + 1] == 0) {
                                        nextMap[i + 1][j] = currentMap[i][j];
                                        nextMap[i + 2][j] = currentMap[i + 1][j];
                                        nextMap[i][j] = 0;
                                        nextMap[i + 1][j + 1] = currentMap[i][j + 1];
                                        nextMap[i + 2][j + 1] = currentMap[i + 1][j + 1];
                                        nextMap[i][j + 1] = 0;
                                        canMove=true;
                                    }
                                }
                                break;
                            case 4://←
                                if (j >= 2 && nextMap[i][j - 1] == 0) {
                                    if (i == nextMap.length - 1 || nextMap[i + 1][j] >= 0) {
                                        nextMap[i][j - 1] = currentMap[i][j];
                                        nextMap[i][j] = 0;
                                        if (j < nextMap[i].length-2 && nextMap[i][j + 1] < 0) {
                                            nextMap[i][j] = currentMap[i][j + 1];
                                            nextMap[i][j + 1] = 0;
                                        }
                                        canMove=true;
                                    } else if (nextMap[i + 1][j - 1] == 0) {
                                        if (i == nextMap.length - 1 || nextMap[i + 1][j] >= 0) {
                                            nextMap[i][j - 1] = currentMap[i][j];
                                            nextMap[i][j] = 0;
                                            nextMap[i + 1][j - 1] = currentMap[i + 1][j];
                                            nextMap[i + 1][j] = 0;
                                            if (j < nextMap[i].length-2 && nextMap[i][j + 1] < 0) {
                                                nextMap[i][j] = currentMap[i][j + 1];
                                                nextMap[i][j + 1] = 0;
                                                nextMap[i + 1][j] = currentMap[i + 1][j + 1];
                                                nextMap[i + 1][j + 1] = 0;
                                            }
                                            canMove=true;
                                        }

                                    }
                                    break;
                                }

                        }
                        if(canMove){
                            if(way.size()==currentStep+1) {
                                way.set(currentStep, new Move(i, j, k));
                            }else way.add(new Move(i, j, k));
                            return nextMap;
                        }
                    }
                }
            }
        }
        return null;
    }
}

class Move {
    private int[] loc;
    private int dir;      //1上 2右 3下 4左

    public Move(int row, int col, int dir) {
        this.loc = new int[]{row, col};
        this.dir = dir;
    }

    public int[] getLoc() {
        return loc;
    }

    public int getDir() {
        return dir;
    }
}

