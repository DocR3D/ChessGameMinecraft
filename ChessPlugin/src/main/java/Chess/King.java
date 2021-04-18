package Chess;

import java.util.ArrayList;

public class King extends Piece{
    boolean hasBeenChecked = false;
    int isCastlening = 0;
    public King(boolean isWhite, int numero, int x, int z) {
        super(isWhite, numero, x, z);
    }

    @Override
    public ArrayList<int[]> whereCanItGo(Piece[][] leTableau) {
        ArrayList<int[]> result = new ArrayList<int[]>();
        if (x - 1 >= 0)
            if (leTableau[x - 1][z] == null) result.add(new int[]{x - 1,z});
            else if(leTableau[x - 1][z].isWhite != this.isWhite) result.add(new int[]{x - 1,z}) ;
        if (x + 1 < 8)
            if (leTableau[x + 1][z] == null) result.add(new int[]{x + 1,z});
            else if(leTableau[x + 1][z].isWhite != this.isWhite) result.add(new int[]{x + 1,z}) ;
        if (z + 1 < 8)
            if (leTableau[x][z + 1] == null) result.add(new int[]{x,z+1});
            else if(leTableau[x][z+1].isWhite != this.isWhite) result.add(new int[]{x,z+1}) ;
        if (z - 1 >= 0 )
            if (leTableau[x][z - 1] == null) result.add(new int[]{x,z-1});
            else if(leTableau[x][z - 1].isWhite != this.isWhite) result.add(new int[]{x,z - 1}) ;
            
        if (x +1 < 8 && z +1 < 8)
            if (leTableau[x +1][z +1] == null) result.add(new int[]{x+1, z+1});
            else {
                if (leTableau[x +1][z +1].isWhite != isWhite) result.add(new int[]{x+1, z+1});
            }
        if (x +1 < 8 && z -1 >= 0)
            if (leTableau[x +1][z -1] == null) result.add(new int[]{x+1, z-1});
            else {
                if (leTableau[x +1][z -1].isWhite != isWhite) result.add(new int[]{x+1, z-1});
            }
        if (x -1 >= 0 && z +1 < 8)
            if (leTableau[x -1][z +1] == null) result.add(new int[]{x-1, z+1});
            else {
                if (leTableau[x -1][z +1].isWhite != isWhite) result.add(new int[]{x-1, z+1});
            }
        if (x -1 >= 0 && z -1 >= 0)
            if (leTableau[x -1][z -1] == null) result.add(new int[]{x-1, z-1});
            else {
                if (leTableau[x -1][z -1].isWhite != isWhite) result.add(new int[]{x-1, z-1});

            }
            System.out.println(hasBeenChecked);
            if(!hasBeenChecked) {
                if(this.isWhite) {
                    //System.out.println(this.hasMoved + " et " + leTableau[z - 3][x] + " " + leTableau[z - 1][x] + " " + leTableau[z - 2][x] );
                    //System.out.println(this.hasMoved + " et " + leTableau[z + 3][x] + " " + leTableau[z + 1][x] + " " + leTableau[z + 2][x] );
                    if (!this.hasMoved && leTableau[x][z - 3] != null && !leTableau[x][z - 3].hasMoved && leTableau[x][z - 1] == null && leTableau[x][z - 2] == null) {
                        this.isCastlening = 1;
                        System.out.println("ça rook\n\n\n");
                        result.add(new int[]{x, z - 3});
                    }
                    if (!this.hasMoved && leTableau[x][z + 4] != null && !leTableau[x][z + 4].hasMoved && leTableau[z + 1][x] == null && leTableau[x][z + 2] == null && leTableau[x][z + 3] == null) {
                        this.isCastlening = 2;
                        result.add(new int[]{x, z + 4});

                    }
                }else{
                    if (!this.hasMoved && leTableau[x][z + 3] != null && !leTableau[x][z + 3].hasMoved && leTableau[x][z + 1] == null && leTableau[x][z + 2] == null) {
                        this.isCastlening = 1;
                        System.out.println("ça rook\n\n\n");
                        result.add(new int[]{x, z + 3});
                    }
                    if (!this.hasMoved && leTableau[x][z - 4] != null && !leTableau[x][z - 4].hasMoved && leTableau[z - 1][x] == null && leTableau[x][z - 2] == null && leTableau[x][z - 3] == null) {
                        this.isCastlening = 2;
                        result.add(new int[]{x, z - 4});

                    }
                }
            }



            
        return result;
    }

}
