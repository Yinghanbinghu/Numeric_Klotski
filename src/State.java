import java.util.Arrays;

public class State {
    Move move;
    State next;
    Array2D array2D;
    public State(int[][] data){
        move=new Move(1,1,0);
        array2D=new Array2D(data);
        next=null;
    }

    private int[][] currentMove() {            //从当前的移动开始向后遍历
        int[] loc = move.getLoc();
        int dir = move.getDir();
        int[][] currentMap = array2D.getMap();
        int[][] nextMap = new int[currentMap.length][currentMap[0].length];
        for (int i = 0; i < currentMap.length; i++) {
            System.arraycopy(currentMap[i], 0, nextMap[i], 0, currentMap[i].length);
        }
        for (int i = loc[0]; i < nextMap.length; i++) {
            for (int j = i == loc[0] ? loc[1] : 1; j < nextMap[i].length; j++) {
                if (nextMap[i][j] > 0) {
                    for (int k = i == loc[0] && j == loc[1] ? dir + 1 : 1; k < 5; k++) {
                        boolean canMove = false;
                        switch (k) {                   //上下左右
                            case 1://↑
                                if (i >= 2 && nextMap[i - 1][j] == 0) {
                                    if (j == nextMap[i].length - 1 || nextMap[i][j + 1] >= 0) {
                                        nextMap[i - 1][j] = currentMap[i][j];
                                        nextMap[i][j] = 0;
                                        if (i < nextMap.length - 1 && nextMap[i + 1][j] < 0) {
                                            nextMap[i][j] = currentMap[i + 1][j];
                                            nextMap[i + 1][j] = 0;
                                        }
                                        canMove = true;
                                    } else if (nextMap[i - 1][j + 1] == 0) {
                                        nextMap[i - 1][j] = currentMap[i][j];
                                        nextMap[i - 1][j + 1] = currentMap[i][j + 1];
                                        nextMap[i][j]=0;
                                        nextMap[i][j + 1]=0;
                                        if (i < nextMap.length - 1 && nextMap[i + 1][j] < 0) {
                                            nextMap[i][j] = currentMap[i + 1][j];
                                            nextMap[i][j + 1] = currentMap[i + 1][j + 1];
                                            nextMap[i + 1][j]=0;
                                            nextMap[i + 1][j + 1]=0;
                                        }
                                        canMove = true;
                                    }
                                }
                                break;
                            case 2://→
                                if (j < nextMap[i].length - 1 && nextMap[i][j + 1] == 0) {
                                    if (i == nextMap.length - 1 || nextMap[i + 1][j] >= 0) {
                                        nextMap[i][j + 1] = currentMap[i][j];
                                        nextMap[i][j] = 0;
                                        canMove = true;
                                    } else if (nextMap[i + 1][j + 1] == 0) {
                                        nextMap[i][j + 1] = currentMap[i][j];
                                        nextMap[i + 1][j + 1] = currentMap[i + 1][j];
                                        nextMap[i][j] = 0;
                                        nextMap[i + 1][j] = 0;
                                        canMove = true;
                                    }


                                }
                                if (j < nextMap[i].length - 2 && nextMap[i][j + 1] < 0 && nextMap[i][j + 2] == 0) {

                                    if (i == nextMap.length - 1 || nextMap[i + 1][j] >= 0) {
                                        nextMap[i][j + 1] = currentMap[i][j];
                                        nextMap[i][j + 2] = currentMap[i][j + 1];
                                        nextMap[i][j] = 0;
                                        canMove = true;
                                    } else if (nextMap[i + 1][j + 2] == 0) {
                                        nextMap[i][j + 1] = currentMap[i][j];
                                        nextMap[i + 1][j + 1] = currentMap[i + 1][j];
                                        nextMap[i][j + 2] = currentMap[i][j + 1];
                                        nextMap[i + 1][j + 2] = currentMap[i + 1][j + 1];
                                        nextMap[i][j] = 0;
                                        nextMap[i + 1][j] = 0;
                                        canMove = true;
                                    }
                                }
                                break;
                            case 3://↓
                                if (i < nextMap.length - 1 && nextMap[i + 1][j] == 0) {
                                    if (j == nextMap[i].length - 1 || nextMap[i][j + 1] >= 0) {
                                        nextMap[i + 1][j] = currentMap[i][j];
                                        nextMap[i][j] = 0;
                                        canMove = true;
                                    } else if (nextMap[i + 1][j + 1] == 0) {
                                        nextMap[i + 1][j] = currentMap[i][j];
                                        nextMap[i][j] = 0;
                                        nextMap[i + 1][j + 1] = currentMap[i][j + 1];
                                        nextMap[i][j + 1] = 0;
                                        canMove = true;
                                    }
                                }
                                if (i < nextMap.length - 2 && nextMap[i + 1][j] < 0 && nextMap[i + 2][j] == 0) {
                                    if (j == nextMap[i].length - 1 || nextMap[i][j + 1] >= 0) {
                                        nextMap[i + 1][j] = currentMap[i][j];
                                        nextMap[i + 2][j] = currentMap[i + 1][j];
                                        nextMap[i][j] = 0;
                                        canMove = true;
                                    } else if (nextMap[i + 1][j + 1] == 0) {
                                        nextMap[i + 1][j] = currentMap[i][j];
                                        nextMap[i + 2][j] = currentMap[i + 1][j];
                                        nextMap[i][j] = 0;
                                        nextMap[i + 1][j + 1] = currentMap[i][j + 1];
                                        nextMap[i + 2][j + 1] = currentMap[i + 1][j + 1];
                                        nextMap[i][j + 1] = 0;
                                        canMove = true;
                                    }
                                }
                                break;
                            case 4://←
                                if (j >= 2 && nextMap[i][j - 1] == 0) {
                                    if (i == nextMap.length - 1 || nextMap[i + 1][j] >= 0) {
                                        nextMap[i][j - 1] = currentMap[i][j];
                                        nextMap[i][j] = 0;
                                        if (j < nextMap[i].length - 2 && nextMap[i][j + 1] < 0) {
                                            nextMap[i][j] = currentMap[i][j + 1];
                                            nextMap[i][j + 1] = 0;
                                        }
                                        canMove = true;
                                    } else if (nextMap[i + 1][j - 1] == 0) {
                                        if (i == nextMap.length - 1 || nextMap[i + 1][j] >= 0) {
                                            nextMap[i][j - 1] = currentMap[i][j];
                                            nextMap[i][j] = 0;
                                            nextMap[i + 1][j - 1] = currentMap[i + 1][j];
                                            nextMap[i + 1][j] = 0;
                                            if (j < nextMap[i].length - 2 && nextMap[i][j + 1] < 0) {
                                                nextMap[i][j] = currentMap[i][j + 1];
                                                nextMap[i][j + 1] = 0;
                                                nextMap[i + 1][j] = currentMap[i + 1][j + 1];
                                                nextMap[i + 1][j + 1] = 0;
                                            }
                                            canMove = true;
                                        }

                                    }
                                    break;
                                }

                        }
                        if (canMove) {
                            move=new Move(i, j, k);
                            return nextMap;
                        }
                    }
                }
            }
        }
        return null;
    }
}
class Array2D{
    public Array2D(int[][] map) {
        this.map = map;
    }

    public int[][] getMap() {
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Array2D array2D = (Array2D) o;
        return Arrays.equals(map, array2D.map);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(map);
    }

    int[][] map;

}