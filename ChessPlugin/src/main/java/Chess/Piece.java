package Chess;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public abstract class Piece {
    public boolean isWhite;
    int piece;
    String name;
    EntityType type;
    Entity laPiece;
    public int x;
    public int z;
    boolean hasMoved = false;

    public Piece(boolean isWhite, int numero, int x, int z){
        this.isWhite = isWhite;
        this.piece = numero;
        this.x = x;
        this.z = z;
        switch (this.piece) {
            case 1 -> {
                name = "Pawn";
                if (this.isWhite)
                    type = EntityType.PIG;
                else
                    type = EntityType.SLIME;
            }
            case 2 -> {
                name = "Knight";
                if (this.isWhite)
                    type = EntityType.WOLF;
                else
                    type = EntityType.SPIDER;
            }
            case 3 -> {
                name = "Bishop";
                if (this.isWhite)
                    type = EntityType.COW;
                else
                    type = EntityType.SKELETON;
            }
            case 4 -> {
                name = "Rook";
                if (this.isWhite)
                    type = EntityType.IRON_GOLEM;
                else
                    type = EntityType.ZOMBIE;
            }
            case 5 -> {
                name = "King";
                if (this.isWhite)
                    type = EntityType.PANDA;
                else
                    type = EntityType.ENDERMAN;
            }
            case 6 -> {
                name = "Queen";
                if (this.isWhite)
                    type = EntityType.VILLAGER;
                else
                    type = EntityType.CREEPER;
            }
            default -> throw new IllegalStateException("Unexpected value: " + this.piece);
        }
    }


    public void spawnPiece(Player p, Location loc){

        if(isWhite) loc.setDirection(new Vector(-1,0,0));
        else loc.setDirection(new Vector(1,0,0));
        laPiece = p.getWorld().spawnEntity(loc, type);

        laPiece.setCustomName(this.isWhite + " " + name);
        laPiece.setInvulnerable(true);
        ((LivingEntity) laPiece).setAI(false);
        laPiece.setSilent(true);
        if(type == EntityType.SLIME) ((Slime) laPiece).setSize(1);
        if(type == EntityType.ZOMBIE) ((Zombie) laPiece).setAge(1);
        System.out.println("Spawn de : " + type + " à " + loc);
    }
    @Override
    public String toString(){
        return "Piece de type : " + this.name + " portant l'apparance : " + this.type + " X : " + x + "; Z : " + z;
    }

    public void setGlowing(boolean glowing){
        this.laPiece.setGlowing(glowing);
    }

    abstract public ArrayList<int[]> whereCanItGo(Piece[][] leTableau);
    public ArrayList<Piece> whatCanItAttack(Piece[][] leTableau){
        ArrayList<Piece> result = new ArrayList<>();
        ArrayList<int[]> laListe = this.whereCanItGo(leTableau);
        System.out.println(this);
        for(int[] unCouple : laListe){
            System.out.println("x : " + unCouple[0] + " et Z " + unCouple[1]);
            if(unCouple[0] >= 0 && unCouple[0] < 8 && unCouple[1] >= 0 && unCouple[1] < 8) {
                if(leTableau[unCouple[0]][unCouple[1]]!= null){
                    result.add(leTableau[unCouple[0]][unCouple[1]]);
                }
            }
        }
        return result;
    }

}
