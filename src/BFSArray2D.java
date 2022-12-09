import java.util.Arrays;

public class BFSArray2D {
    public BFSArray2D(int[][] map, BFSArray2D node, String move) {
        this.map = map;
        this.node = node;
        this.move = move;
    }

    String move;

    public int[][] getMap() {
        return map;
    }

    public BFSArray2D getNode() {
        return node;
    }

     int[][] map;

    BFSArray2D node;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BFSArray2D array2D = (BFSArray2D) o;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != array2D.getMap()[i][j]&&map[i][j] != -array2D.getMap()[i][j]) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = -999999;
        for (int[] i : map
        ) {
            for (int j : i
            ) {
                hash = 7 * hash + j;
            }
        }
        return hash;
    }
}
