package Chess;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(boolean isWhite, int numero, int x, int z) {
        super(isWhite, numero, x, z);
    }

    @Override
    public ArrayList<int[]> whereCanItGo(Piece[][] leTableau) {
        boolean up = true;
        boolean down = true;
        boolean right = true;
        boolean left = true;
        ArrayList<int[]> result = new ArrayList<int[]>();
        for (int a = 1; a < 8; a++) {
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
