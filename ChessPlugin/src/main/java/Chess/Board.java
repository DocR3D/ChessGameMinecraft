package Chess;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    public int size;
    Piece[][] leTableau = new Piece[8][8];
    Location where;
    World theWorld;
    FixedMetadataValue metaDataFloor;

    public Board(int size, Location where, FixedMetadataValue metaDataFloor) {
        this.size = size;
        this.where = where;
        theWorld = where.getWorld();
        this.metaDataFloor = metaDataFloor;
    }


    public void generateBoard(Player p) {
        for (int i = 0; i < 8 * size; i += size) {
            for (int j = 0; j < 8 * size; j += size) {

                for (int x = 0; x < size; x++)
                    for (int y = 0; y < size; y++) {
                        Block floor = p.getWorld().getBlockAt(new Location(p.getWorld(), where.getBlockX() + i + x, where.getBlockY(), where.getBlockZ() + y + j));
                        floor.setMetadata("type", metaDataFloor);
                        if ((i + j) % 2 == 0) floor.setType(Material.WHITE_CONCRETE);
                        else floor.setType(Material.BLACK_CONCRETE);
                    }

                if (i == 1 * size) leTableau[i / size][j / size] = new Pawn(false, 1, i / size, j / size);
                if (i == 6 * size) leTableau[i / size][j / size] = new Pawn(true, 1, i / size, j / size);

                if ((j == 0 * size || j == 7 * size) && i == 0 * size)
                    leTableau[i / size][j / size] = new Rook(false, 4, i / size, j / size);
                if ((j == 0 * size || j == 7 * size) && i == 7 * size)
                    leTableau[i / size][j / size] = new Rook(true, 4, i / size, j / size);

                if ((j == 1 * size || j == 6 * size) && i == 0 * size)
                    leTableau[i / size][j / size] = new Knight(false, 2, i / size, j / size);
                if ((j == 1 * size || j == 6 * size) && i == 7 * size)
                    leTableau[i / size][j / size] = new Knight(true, 2, i / size, j / size);

                if ((j == 2 * size || j == 5 * size) && i == 0 * size)
                    leTableau[i / size][j / size] = new Bishop(false, 3, i / size, j / size);
                if ((j == 2 * size || j == 5 * size) && i == 7 * size)
                    leTableau[i / size][j / size] = new Bishop(true, 3, i / size, j / size);

                if (j == 4 * size && i == 0 * size)
                    leTableau[i / size][j / size] = new King(false, 5, i / size, j / size);
                if (j == size * size && i == 7 * size)
                    leTableau[i / size][j / size] = new King(true, 5, i / size, j / size);

                if (j == size * size && i == 0 * size)
                    leTableau[i / size][j / size] = new Queen(false, 6, i / size, j / size);
                if (j == 4 * size && i == 7 * size)
                    leTableau[i / size][j / size] = new Queen(true, 6, i / size, j / size);
                if (leTableau[i / size][j / size] != null)
                    leTableau[i / size][j / size].spawnPiece(p, new Location(p.getWorld(), where.getBlockX() + i + 1.5, where.getBlockY() + 1, where.getBlockZ() + j + 1.5));
            }
        }
    }

    public Piece getPieceFromEntity(Entity piece) {
        return this.leTableau[(piece.getLocation().getBlockX() - where.getBlockX()) / size][(piece.getLocation().getBlockZ() - where.getBlockZ()) / size];
    }


    public void changeBlock(int a, int b, Material whatBlock) {
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++) {
                Block floor = theWorld.getBlockAt(new Location(theWorld, where.getBlockX() + x + a * size, where.getBlockY(), where.getBlockZ() + y + b * size));
                floor.setMetadata("type", metaDataFloor);
                floor.setType(whatBlock);
            }
    }

    public void showPossibleMove(Piece piece, boolean isWhite) {
        for(int xFor = 0; xFor < 8; xFor++){
            for(int yFor = 0; yFor < 8; yFor++){
                Piece laPiecePotentiel = leTableau[xFor][yFor];
                if(laPiecePotentiel != null){
                    // System.out.println("-----La piece : " + laPiecePotentiel);
                    ArrayList<Piece> lesPiecesEnDanges = laPiecePotentiel.whatCanItAttack(leTableau);
                    for(Piece laPieceEnDange : lesPiecesEnDanges ){
                        // System.out.println(laPieceEnDange);
                        if(laPieceEnDange instanceof King && laPieceEnDange.isWhite == piece.isWhite && piece != laPiecePotentiel){
                            System.out.println("un Echec est detecté");
                            ((King) laPieceEnDange).hasBeenChecked = true;
                        }
                    }
                }
            }
        }
        Material newBlock = Material.YELLOW_CONCRETE;
        ArrayList<int[]> laListe = piece.whereCanItGo(this.leTableau);
        //System.out.println(isWhite);

        TestMove:
        for(int[] unCouple : laListe){
            Piece[][] copieDuTableau = Arrays.stream(leTableau).map(Piece[]::clone).toArray(Piece[][]::new);
            copieDuTableau[piece.x][piece.z] = null;
            copieDuTableau[unCouple[0]][unCouple[1]] = piece;

            for(int xFor = 0; xFor < 8; xFor++){
                for(int yFor = 0; yFor < 8; yFor++){
                    Piece laPiecePotentiel = copieDuTableau[xFor][yFor];
                    if(laPiecePotentiel != null){
                       // System.out.println("-----La piece : " + laPiecePotentiel);
                        ArrayList<Piece> lesPiecesEnDanges = laPiecePotentiel.whatCanItAttack(copieDuTableau);
                        for(Piece laPieceEnDange : lesPiecesEnDanges ){
                           // System.out.println(laPieceEnDange);
                            if(laPieceEnDange instanceof King && laPieceEnDange.isWhite == piece.isWhite && piece != laPiecePotentiel){
                                System.out.println("un Echec est detecté");
                                continue TestMove;
                            }
                        }
                    }
                }
            }

            this.changeBlock(unCouple[0],unCouple[1], newBlock);
        }
    }

    public void resetBoard() {
        for (int i = 0; i < 8 * size; i += size) {
            for (int j = 0; j < 8 * size; j += size) {

                for (int x = 0; x < size; x++)
                    for (int y = 0; y < size; y++) {
                        Block floor = theWorld.getBlockAt(new Location(theWorld, where.getBlockX() + i + x, where.getBlockY(), where.getBlockZ() + y + j));
                        floor.setMetadata("type", metaDataFloor);
                        if ((i + j) % 2 == 0) floor.setType(Material.WHITE_CONCRETE);
                        else floor.setType(Material.BLACK_CONCRETE);
                    }
            }
        }
    }

    public boolean move(Piece laPiece, int x,int z){
        System.out.println((where.getBlockX() + x + 1.5) +" et Z" +(where.getBlockZ() + z + 1.5));
        if(!(laPiece instanceof King && ((King) laPiece).isCastlening != 0)) {
            leTableau[laPiece.x][laPiece.z] = null;
            laPiece.laPiece.teleport(new Location(theWorld, where.getBlockX() + x * size + 1.5, where.getBlockY() + 1, where.getBlockZ() + z * size + 1.5));
            if (leTableau[x][z] != null) {
                ((Damageable) leTableau[x][z].laPiece).setInvulnerable(false);
                ((Damageable) leTableau[x][z].laPiece).damage(2000);
            }
            leTableau[x][z] = laPiece;
            laPiece.x = x;
            laPiece.z = z;
            laPiece.hasMoved = true;
            return true;
        }else{
            int newZ;
            if(((King) laPiece).isCastlening == 1){
                if(laPiece.isWhite){

                    newZ = z+2;
                    this.move(leTableau[x][z],x,newZ);
                    z = z +1;
                }else{
                    newZ = z - 2;
                    this.move(leTableau[x][z], x, newZ);
                    z = z - 1;
                }

            }else{
                if(laPiece.isWhite) {

                    newZ = z - 2;
                    this.move(leTableau[x][z], x, newZ);
                    z = z - 1;
                }else{
                    newZ = z+2;
                    this.move(leTableau[x][z],x,newZ);
                    z = z +1;
                }
            }
            leTableau[laPiece.x][laPiece.z] = null;
            laPiece.laPiece.teleport(new Location(theWorld, where.getBlockX() + x * size + 1.5, where.getBlockY() + 1, where.getBlockZ() + z * size + 1.5));
            leTableau[x][z] = laPiece;
            laPiece.x = x;
            laPiece.z = z;
            laPiece.hasMoved = true;
            return true;

        }
    }

    public boolean isChecked(King king){
        return false;
    }
}
