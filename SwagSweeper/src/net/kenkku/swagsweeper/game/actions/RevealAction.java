package net.kenkku.swagsweeper.game.actions;

import java.util.LinkedList;
import java.util.List;
import net.kenkku.swagsweeper.game.Field;
import net.kenkku.swagsweeper.game.MinesweeperGame;
import net.kenkku.swagsweeper.game.Square;
import net.kenkku.swagsweeper.util.Position;

/**
 * Yhden ruudun avaaminen, tarvittaessa avataan viereisiä ruutuja
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class RevealAction extends Action {

    private Square squareToReveal;
    private List<Square> revealed = new LinkedList<Square>();

    public RevealAction(MinesweeperGame game, Position position) {
        this.game = game;
        this.field = game.getField();
        squareToReveal = field.getSquare(position);
    }

    /**
     * Avaa Square-olion sekä sen ympärillä olevat oliot, jos 
     * ruudun ympärillä ei ole miinoja
     */
    private void recursiveReveal(Square square) {
        square.setRevealed(true);
        revealed.add(square);
        if (field.getNumMines(square) > 0) {
            return;
        }

        for (Square s : field.getAdjacent(square)) {
            if (!s.isRevealed()) {
                recursiveReveal(s);
            }
        }
    }

    @Override
    public void execute() {
        if (squareToReveal.isMine()) {
            squareToReveal.setRevealed(true);
            revealed.add(squareToReveal);
            
            game.addMove(new RevealAll(game));
            game.gameOver();
            return;
        }
        recursiveReveal(squareToReveal);
    }

    @Override
    public void undo() {
        for (Square square : revealed) {
            square.setRevealed(false);
        }
    }
}
