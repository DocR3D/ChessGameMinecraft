package Chess;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(boolean isWhite, int numero, int x, int z) {
        super(isWhite, numero, x, z);
    }

    @Override
    public ArrayList<int[]> whereCanItGo(Piece[][] leTableau) {
        boolean upLeft = true;
        boolean upRight = true;
        boolean downRight = true;
        boolean downLeft = true;

        ArrayList<int[]> result = new ArrayList<int[]>();
        for (int a = 1; a < 8; a++) {
            if (x + a < 8 && z + a < 8 && upRight)
                if (leTableau[x + a][z + a] == null) result.add(new int[]{x+a, z+a});
                else {
                    if (leTableau[x + a][z + a].isWhite != isWhite) result.add(new int[]{x+a, z+a});
                    upRight = false;
                }
            if (x + a < 8 && z - a >= 0 && downRight)
                if (leTableau[x + a][z - a] == null) result.add(new int[]{x+a, z-a});
                else {
                    if (leTableau[x + a][z - a].isWhite != isWhite) result.add(new int[]{x+a, z-a});
                    downRight = false;
                }
            if (x - a >= 0 && z + a < 8 && upLeft)
                if (leTableau[x - a][z + a] == null) result.add(new int[]{x-a, z+a});
                else {
                    if (leTableau[x - a][z + a].isWhite != isWhite) result.add(new int[]{x-a, z+a});
                    upLeft = false;
                }
            if (x - a >= 0 && z - a >= 0 && downLeft)
                if (leTableau[x - a][z - a] == null) result.add(new int[]{x-a, z-a});
                else {
                    if (leTableau[x - a][z - a].isWhite != isWhite) result.add(new int[]{x-a, z-a});
                    downLeft = false;

                }
        }
        return result;
    }
}
