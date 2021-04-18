package Chess;


import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ChessPlayer implements Listener {
    public boolean isWhite;
    public Player thePlayer;
    public Piece selected;


    public ChessPlayer(Player p, boolean isWhite ){
        this.thePlayer = p;
        this.isWhite = isWhite;
    }


    public void selectPiece(Piece laPiece){
        if(selected != laPiece){
            if(selected != null) selected.setGlowing(false);
            selected = laPiece;
            if(selected != null) selected.setGlowing(true);

        } else{
            if(selected != null) selected.setGlowing(false);
            selected = null;
        }
        System.out.println("Player white ? " + isWhite + " has selected : " + selected);

    }
}
