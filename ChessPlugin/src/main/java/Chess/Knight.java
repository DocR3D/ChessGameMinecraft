package Chess;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(boolean isWhite, int numero, int x, int z) {
        super(isWhite, numero, x, z);
    }

    @Override
    public ArrayList<int[]> whereCanItGo(Piece[][] leTableau) {
        boolean up = true;
        boolean down = true;
        boolean right = true;
        boolean left = true;
        ArrayList<int[]> result = new ArrayList<int[]>();
        if(x+1 < 8 && z +2 < 8)
            if(leTableau[x+1][z+2] != null){
                if(leTableau[x+1][z+2].isWhite != isWhite)result.add(new int[]{x+1, z+2});
            }else result.add(new int[]{x+1, z+2});

        if(x+2 < 8 && z +1 < 8)
            if(leTableau[x+2][z+1] != null){
                if(leTableau[x+2][z+1].isWhite != isWhite) result.add(new int[]{x+2, z+1});
            }else result.add(new int[]{x+2, z+1});

        if(x-1 >= 0 && z -2 >= 0)
            if(leTableau[x-1][z-2] != null){
                if (leTableau[x - 1][z - 2].isWhite != isWhite) result.add(new int[]{x-1, z-2});
            }else result.add(new int[]{x-1, z-2});

        if(x-2 >= 0 && z +1 < 8) if(leTableau[x-2][z+1] != null){
            if(leTableau[x-2][z+1].isWhite != isWhite)
                result.add(new int[]{x-2, z+1});
        } else result.add(new int[]{x-2, z+1});

        if(x-2 >= 0 && z -1 >=0 ) if(leTableau[x-2][z-1] != null){
            if(leTableau[x-2][z-1].isWhite != isWhite)
                result.add(new int[]{x-2, z-1});
        } else result.add(new int[]{x-2, z-1});

        if(x-1 >= 0 && z +2 <8)  if(leTableau[x-1][z+2]  != null) {
            if( leTableau[x-1][z+2].isWhite != isWhite)
                result.add(new int[]{x-1, z+2});
        }else result.add(new int[]{x-1, z+2});
        if(x+1 < 8 && z -2 >= 0) if( leTableau[x+1][z-2] != null) {
            if( leTableau[x+1][z-2].isWhite != isWhite)
                result.add(new int[]{x+1, z-2});
        }else result.add(new int[]{x+1, z-2});

        if(x+2 < 8 && z -1 >= 0) if( leTableau[x+2][z-1] != null) {
            if( leTableau[x+2][z-1].isWhite != isWhite)
                result.add(new int[]{x+2, z-1});
        }else result.add(new int[]{x+2, z-1});

        return result;
    }
}
