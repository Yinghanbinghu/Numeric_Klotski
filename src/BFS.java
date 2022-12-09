import java.util.ArrayList;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SeparateChainingHashST;

public class BFS {
    Queue<BFSArray2D> que;
    SeparateChainingHashST<BFSArray2D,Boolean> hasFound;
    int[] C12;
    int[] C21;
    int[] C22;
    BFSArray2D Complete;

    public BFS(int[][] initial, int[] C12, int[] C21, int[] C22,int[][] comp) {
        que = new Queue<BFSArray2D>();
        BFSArray2D a = new BFSArray2D(initial, null,null);
        que.enqueue(a);//初始状态存入
        hasFound = new SeparateChainingHashST<>();
        hasFound.put(a,true);
        this.C12 = C12;
        this.C21 = C21;
        this.C22 = C22;
        this.Complete=new BFSArray2D(comp,null,null);
    }

    public ArrayList<BFSArray2D> search() {
        while (!que.isEmpty()) {
            BFSArray2D a = currentMove(que.dequeue());
            if (a != null) {
                ArrayList<BFSArray2D> result = new ArrayList<>();
                result.add(a);
                while (a.getNode()!=null){
                    result.add(a.getNode());
                    a=a.getNode();
                }
                return result;
            }
        }
        return null;
    }

    private BFSArray2D currentMove(BFSArray2D current) {
        int[][] currentMap = current.getMap();

        for (int i = 0; i < currentMap.length; i++) {
            for (int j = 0; j < currentMap[0].length; j++) {
                if (currentMap[i][j] != 0) continue;
                for (int k = 1; k < 17; k++) {
                    int[][] nextMap=null;
                    String move=null;
                    switch (k) {
                        case 1:   //上方1*1
                            if (i >= 1 && currentMap[i-1][j]>0&&(!contain(currentMap[i - 1][j], C12))) {
                                nextMap = copy(currentMap);
                                nextMap[i][j] = currentMap[i - 1][j];
                                nextMap[i - 1][j] = 0;
                                move=currentMap[i-1][j]+" "+"D";
                                break;
                            }
                            continue;
                        case 2:   //上方2*1
                            if (i >= 2 &&currentMap[i-2][j]>0&& contain(currentMap[i - 2][j], C21)) {
                                nextMap = copy(currentMap);
                                nextMap[i][j] = currentMap[i - 1][j];
                                nextMap[i - 1][j] = currentMap[i - 2][j];
                                nextMap[i - 2][j] = 0;
                                move=currentMap[i-2][j]+" "+"D";
                                break;
                            }
                            continue;
                        case 3:   //右上方1*2
                            if (j < currentMap[0].length - 1 && currentMap[i][j + 1] == 0 && i >= 1&&currentMap[i-1][j]>0  && contain(currentMap[i - 1][j], C12)) {
                                nextMap = copy(currentMap);
                                nextMap[i][j] = currentMap[i - 1][j];
                                nextMap[i - 1][j] = 0;
                                nextMap[i][j + 1] = currentMap[i - 1][j + 1];
                                nextMap[i - 1][j + 1] = 0;
                                move=currentMap[i-1][j]+" "+"D";
                                break;
                            }
                            continue;
                        case 4:   //右上方2*2
                            if (j < currentMap[0].length - 1 && currentMap[i][j + 1] == 0 && i >= 2&&currentMap[i-2][j]>0  && contain(currentMap[i - 2][j], C22)) {
                                nextMap = copy(currentMap);
                                nextMap[i][j] = currentMap[i - 1][j];
                                nextMap[i - 1][j] = currentMap[i - 2][j];
                                nextMap[i - 2][j] = 0;
                                nextMap[i][j + 1] = currentMap[i - 1][j + 1];
                                nextMap[i - 1][j + 1] = currentMap[i - 2][j + 1];
                                nextMap[i - 2][j + 1] = 0;
                                move=currentMap[i-2][j]+" "+"D";
                                break;
                            }
                            continue;
                        case 5:   //右方1*1
                            if (j < currentMap[0].length - 1 &&currentMap[i][j+1]>0&&  (!contain(currentMap[i][j + 1],C21))&& (!contain(currentMap[i][j + 1],C22))&& (!contain(currentMap[i][j + 1],C12))) {
                                nextMap = copy(currentMap);
                                nextMap[i][j] = currentMap[i][j + 1];
                                nextMap[i][j + 1] = 0;
                                move=currentMap[i][j+1]+" "+"L";
                                break;
                            }
                            continue;
                        case 6:   //右方1*2
                            if (j < currentMap[0].length - 1 &&currentMap[i][j+1]>0&& contain(currentMap[i][j + 1], C12)) {
                                nextMap = copy(currentMap);
                                nextMap[i][j] = currentMap[i][j + 1];
                                nextMap[i][j+1] = currentMap[i][j + 2];
                                nextMap[i][j + 2] = 0;
                                move=currentMap[i][j+1]+" "+"L";
                                break;
                            }
                            continue;
                        case 7:   //右方2*1
                            if (i < currentMap.length - 1 && currentMap[i + 1][j] == 0 && j < currentMap[0].length - 1 && currentMap[i][j + 1] > 0 && contain(currentMap[i][j + 1], C21)) {
                                nextMap = copy(currentMap);
                                nextMap[i][j] = currentMap[i][j + 1];
                                nextMap[i][j + 1] = 0;
                                nextMap[i + 1][j] = currentMap[i + 1][j + 1];
                                nextMap[i + 1][j + 1] = 0;
                                move=currentMap[i][j+1]+" "+"L";
                                break;
                            }
                            continue;
                        case 8:   //右方2*2
                            if (i < currentMap.length - 1 && currentMap[i + 1][j] == 0 && j < currentMap[0].length - 2 &&currentMap[i][j+1]>0&& contain(currentMap[i][j + 1], C22)) {
                                nextMap = copy(currentMap);
                                nextMap[i][j] = currentMap[i][j + 1];
                                nextMap[i][j + 1] = currentMap[i][j + 2];
                                nextMap[i][j + 2] = 0;
                                nextMap[i + 1][j] = currentMap[i + 1][j + 1];
                                nextMap[i + 1][j + 1] = currentMap[i + 1][j + 2];
                                nextMap[i + 1][j + 2] = 0;
                                move=currentMap[i][j+1]+" "+"L";
                                break;
                            }
                            continue;
                        case 9:    //下方1*1
                            if (i < currentMap.length - 1 && currentMap[i+1][j]>0&& (!contain(currentMap[i + 1][j], C21)) && (!contain(currentMap[i + 1][j], C12)) && (!contain(currentMap[i + 1][j], C22))) {
                                nextMap = copy(currentMap);
                                nextMap[i][j] = currentMap[i + 1][j];
                                nextMap[i + 1][j] = 0;
                                move=currentMap[i+1][j]+" "+"U";
                                break;
                            }
                            continue;
                        case 10:    //下方2*1
                            if (i < currentMap.length - 2 &&currentMap[i+1][j]>0&&  (contain(currentMap[i + 1][j], C21))) {
                                nextMap = copy(currentMap);
                                nextMap[i][j] = currentMap[i + 1][j];
                                nextMap[i + 1][j] = currentMap[i + 2][j];
                                nextMap[i + 2][j] = 0;
                                move=currentMap[i+1][j]+" "+"U";
                                break;
                            }
                            continue;
                        case 11:    //下方1*2
                            if (j < currentMap[0].length - 1 && currentMap[i][j + 1] == 0 && i < currentMap.length - 1&&currentMap[i+1][j]>0 && (contain(currentMap[i + 1][j], C12))) {
                                nextMap = copy(currentMap);
                                nextMap[i][j] = currentMap[i + 1][j];
                                nextMap[i + 1][j] = 0;
                                nextMap[i][j + 1] = currentMap[i + 1][j + 1];
                                nextMap[i + 1][j + 1] = 0;
                                move=currentMap[i+1][j]+" "+"U";
                                break;
                            }
                            continue;
                        case 12:    //下方2*2
                            if (j < currentMap[0].length - 1 && currentMap[i][j + 1] == 0 && i < currentMap.length - 2&&currentMap[i+1][j]>0 && (contain(currentMap[i + 1][j], C22))) {
                                nextMap = copy(currentMap);
                                nextMap[i][j] = currentMap[i + 1][j];
                                nextMap[i + 1][j] = currentMap[i + 2][j];
                                nextMap[i + 2][j] = 0;
                                nextMap[i][j + 1] = currentMap[i + 1][j + 1];
                                nextMap[i + 1][j + 1] = currentMap[i + 2][j + 1];
                                nextMap[i + 2][j + 1] = 0;
                                move=currentMap[i+1][j]+" "+"U";
                                break;
                            }
                            continue;
                        case 13:    //左方1*1
                            if (j >= 1 &&currentMap[i][j-1]>0&& (!contain(currentMap[i][j - 1], C21))) {
                                nextMap = copy(currentMap);
                                nextMap[i][j] = currentMap[i][j - 1];
                                nextMap[i][j - 1] = 0;
                                move=currentMap[i][j-1]+" "+"R";
                                break;
                            }
                            continue;
                        case 14:    //左方1*2
                            if (j >= 2 &&currentMap[i][j-2]>0&& (contain(currentMap[i][j - 2], C12))) {
                                nextMap = copy(currentMap);
                                nextMap[i][j] = currentMap[i][j - 1];
                                nextMap[i][j - 1] = currentMap[i][j - 2];
                                nextMap[i][j - 2] = 0;
                                move=currentMap[i][j-2]+" "+"R";
                                break;
                            }
                            continue;
                        case 15:    //左方2*1
                            if (i<currentMap.length-1&&currentMap[i+1][j]==0&&j >= 1 &&currentMap[i][j-1]>0&& (contain(currentMap[i][j - 1], C21))) {
                                nextMap = copy(currentMap);
                                nextMap[i][j] = currentMap[i][j - 1];
                                nextMap[i+1][j] = currentMap[i+1][j - 1];
                                nextMap[i][j - 1] = 0;
                                nextMap[i+1][j - 1] = 0;
                                move=currentMap[i][j-1]+" "+"R";
                                break;
                            }
                            continue;
                        case 16:    //左下2*2
                            if (i<currentMap.length-1&&currentMap[i+1][j]==0&&j >= 2 &&currentMap[i][j-2]>0&& (contain(currentMap[i][j - 2], C22))) {
                                nextMap = copy(currentMap);
                                nextMap[i][j] = currentMap[i][j - 1];
                                nextMap[i][j-1] = currentMap[i][j - 2];
                                nextMap[i+1][j] = currentMap[i+1][j - 1];
                                nextMap[i+1][j-1] = currentMap[i+1][j - 2];
                                nextMap[i][j - 2] = 0;
                                nextMap[i+1][j - 2] = 0;
                                move=currentMap[i][j-2]+" "+"R";
                                break;
                            }
                            continue;
                    }
                    BFSArray2D nextArray2D = new BFSArray2D(nextMap, current,move);
                    if (addQue(nextArray2D)) return nextArray2D;
                }
            }
        }
        return null;
    }

    private int[][] copy(int[][] a) {
        int[][] b = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                b[i][j]=a[i][j];
            }
        }
        return b;
    }

    private boolean addQue(BFSArray2D bfsArray2D) {
        if (!hasFound.contains(bfsArray2D)) {
            que.enqueue(bfsArray2D);
            hasFound.put(bfsArray2D,true);
        }
        return bfsArray2D.equals(Complete);
    }

    private boolean contain(int a, int[] b) {
        if (b == null) return false;
        if (a < 0) return false;
        for (int i : b
        ) {
            if (a == i) return true;
        }
        return false;
    }
}
    /*public void bfs(HashMap<Character, LinkedList<Character>> graph,HashMap<Character,Integer> dist,char start){
        Queue<Character> queue = new LinkedList<>();
        queue.offer(start);
        dist.put(start, 0);
        int i = 0;
        while (!queue.isEmpty()) {
            char queueTop = queue.poll(); //获取队首元素，并且将此元素移除
            i++;
            System.out.println("第" + i + "次循环的栈顶为" + queueTop + "并且这个栈顶距离S的举例为" + dist.get(queueTop));
            int distance = dist.get(queueTop)+1;//自动获取到周边节点距离S的长度
            for (Character s : graph.get(queueTop)) {
                if (!dist.containsKey(s)) {//如果dist节点中不含有这个元素，那就是这个元素还没有被offer进队列
                    dist.put(s, distance);
                    queue.offer(s);
                }
            }

        }
    }*/

