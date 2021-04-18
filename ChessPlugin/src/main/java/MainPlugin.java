import Chess.Board;
import Chess.ChessPlayer;
import Chess.Piece;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class MainPlugin extends JavaPlugin implements CommandExecutor, Listener {
    final static int XCHESS = 300;
    final static int YCHESS = 100;
    final static int ZCHESS = 300;
    ChessPlayer white;
    ChessPlayer black;
    Board board;
    boolean isItWhiteToPlay;
    FixedMetadataValue metaDataFloor;
    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");
        metaDataFloor = new FixedMetadataValue(this, "chessFloor");
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        isItWhiteToPlay = true;
        Player player = (Player) sender;
        white = new ChessPlayer(player, true);
        Player player2 = Bukkit.getPlayer(args[0]);
        if(player2 != null){
            player2.sendMessage("Vous allez jouer aux echecs :) ");
            black = new ChessPlayer(player2, false);
        }else{
            black = new ChessPlayer(player, false);
        }
        getServer().getPluginManager().registerEvents(this, this);
        player.sendMessage("&red Teleportation en cours");
        Location where = new Location(player.getWorld(),XCHESS, YCHESS ,ZCHESS);
        player.teleport(where);
        player2.teleport(where);
        player2.setGameMode(GameMode.SPECTATOR);
        player.setGameMode(GameMode.SPECTATOR);
        board = new Board(3, where,metaDataFloor);
        board.generateBoard(player);

        return false;
    }

    @EventHandler
    public void pieceSelection(PlayerInteractEntityEvent event) {
        if(event.getHand().equals(EquipmentSlot.HAND)) {
            if (event.getPlayer().equals(white.thePlayer)) {
                Piece unePiece = board.getPieceFromEntity(event.getRightClicked());
                if (unePiece.isWhite) {
                    selectAPiece(unePiece, white);
                }
            }
            if (event.getPlayer().equals(black.thePlayer)) {
                Piece unePiece = board.getPieceFromEntity(event.getRightClicked());
                if (!unePiece.isWhite) {
                    selectAPiece(unePiece, black);
                }
            }
        }
    }

    private void selectAPiece(Piece unePiece, ChessPlayer leJoueur) {
        if(leJoueur.selected != null){
            board.resetBoard();
        }
        leJoueur.selectPiece(unePiece);
        if(leJoueur.selected != null){
            board.changeBlock(leJoueur.selected.x, leJoueur.selected.z, Material.RED_CONCRETE);
            board.showPossibleMove(leJoueur.selected, leJoueur.isWhite);
        }
    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK && (event.getClickedBlock().getMetadata("type").contains(metaDataFloor) && event.getClickedBlock().getType().equals(Material.YELLOW_CONCRETE))){
            Piece laPiece;
            /*if(isItWhiteToPlay)  laPiece= white.selected;
            else laPiece = black.selected;*/
            if(white.selected != null) laPiece = white.selected;
            else laPiece = black.selected;
            board.move(laPiece, (event.getClickedBlock().getX()-XCHESS)/board.size, (event.getClickedBlock().getZ()-ZCHESS) /board.size);
            white.selectPiece(null);
            board.resetBoard();
            isItWhiteToPlay= !isItWhiteToPlay;
        }
    }


}
