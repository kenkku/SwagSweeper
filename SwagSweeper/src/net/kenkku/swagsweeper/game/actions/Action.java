package net.kenkku.swagsweeper.game.actions;

import net.kenkku.swagsweeper.game.Field;
import net.kenkku.swagsweeper.game.MinesweeperGame;

/**
 * Yksi askel miinaharavapelissä
 * 
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public abstract class Action {
    protected MinesweeperGame game;
    protected Field field;
    
    public abstract void execute();
    public abstract void undo();
    
    
}
