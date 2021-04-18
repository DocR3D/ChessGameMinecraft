package Chess;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(boolean isWhite, int numero, int x, int z) {
        super(isWhite, numero, x, z);
    }

    @Override
    public ArrayList<int[]> whereCanItGo(Piece[][] leTableau) {
        boolean upLeft = true;
        boolean upRight = true;
        boolean downRight = true;
        boolean downLeft = true;
        boolean up = true;
        boolean down = true;
        boolean right = true;
        boolean left = true;

        ArrayList<int[]> result = new ArrayList<>();
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

            if ( z + a < 8 && up)
                if (leTableau[x][z+a] == null) result.add(new int[]{x, z+a});
                else {
                    if (leTableau[x][z+a].isWhite != isWhite) result.add(new int[]{x, z+a});
                    up = false;
                }
            if (z - a >= 0 && down)
                if (leTableau[x][z - a] == null) result.add(new int[]{x,z-a});
                else {
                    if (leTableau[x][z - a].isWhite != isWhite) result.add(new int[]{x, z-a});
                    down = false;
                }
            if (x - a >= 0 && left)
                if (leTableau[x - a][z] == null) result.add(new int[]{x - a, z});
                else {
                    if (leTableau[x - a][z].isWhite != isWhite) result.add(new int[]{x - a, z});
                    left = false;
                }
            if (x + a <  8 && right)
                if (leTableau[x + a][z] == null) result.add(new int[]{x + a, z});
                else {
                    if (leTableau[x + a][z].isWhite != isWhite) result.add(new int[]{x + a, z});
                    right = false;
                }
        }
        return result;
    }
}
