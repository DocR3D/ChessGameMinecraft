package Chess;

import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(boolean isWhite, int numero, int x, int z) {
        super(isWhite, numero, x, z);
    }

    @Override
    public ArrayList<int[]> whereCanItGo(Piece[][] leTableau) {
        ArrayList<int[]> result = new ArrayList<int[]>();
        if(isWhite) {
            if (x - 1 >= 0) if (leTableau[x - 1][z] == null) result.add(new int[]{x - 1, z});
            if (x - 2 >= 0 && !this.hasMoved && leTableau[x - 1][z] == null) if (leTableau[x - 2][z] == null) result.add(new int[]{x - 2, z});
            if (x - 1 >= 0 && z + 1 < 8)
                if (leTableau[x - 1][z + 1] != null && !leTableau[x - 1][z + 1].isWhite)
                    result.add(new int[]{x - 1, z + 1});
            if (x - 1 >= 0 && z - 1 >= 0)
                if (leTableau[x - 1][z - 1] != null && !leTableau[x - 1][z - 1].isWhite)
                    result.add(new int[]{x - 1, z - 1});
        }else{
            if (x + 1 < 8) if (leTableau[x + 1][z] == null) result.add(new int[]{x + 1, z});
            if (x + 2 < 8 && !this.hasMoved && leTableau[x + 1][z] == null) if (leTableau[x + 2][z] == null) result.add(new int[]{x + 2, z});
            if (x + 1 < 8 && z + 1 < 8)
                if (leTableau[x + 1][z + 1] != null && leTableau[x + 1][z + 1].isWhite)
                    result.add(new int[]{x + 1, z + 1});
            if (x + 1 < 8 && z - 1 >= 0)
                if (leTableau[x + 1][z - 1] != null && leTableau[x + 1][z - 1].isWhite)
                    result.add(new int[]{x + 1, z - 1});
        }
        return result;
    }
}
