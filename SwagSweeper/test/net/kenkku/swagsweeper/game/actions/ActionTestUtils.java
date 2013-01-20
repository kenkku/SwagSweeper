package net.kenkku.swagsweeper.game.actions;

import java.util.Scanner;
import net.kenkku.swagsweeper.game.Field;
import net.kenkku.swagsweeper.game.Square;
import net.kenkku.swagsweeper.util.Position;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
class ActionTestUtils {

    static boolean revealedMatches(Field f, String mask) {
        Scanner scanner = new Scanner(mask);
        
        int x = 0;
        int y = 0;
        scanner.useDelimiter("");
        while(scanner.hasNext()) {
            String next = scanner.next();
            if(next.equals("\n")) {
                x = 0;
                y++;
                continue;
            }
            if(!matches(f.getSquare(new Position(x, y)), next)) {
                return false;
            }
            x++;
        }
        return true;
    }
    
    private static boolean matches(Square square, String string) {
        if((string.equals("1") && square.isRevealed()) 
                || (string.equals("0") && !square.isRevealed())) {
            return true;
        } 
        return false;
    }
    
}
