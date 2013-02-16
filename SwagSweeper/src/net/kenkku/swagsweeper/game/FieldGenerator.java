package net.kenkku.swagsweeper.game;

import java.security.InvalidParameterException;
import java.util.Random;
import net.kenkku.swagsweeper.util.Position;

/**
 * Luokka Field-olioiden luomiseen
 * 
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
public class FieldGenerator {

    /**
     * Alustaa Field-olion täyteen tyhjiä Square-olioita
     */
    public static Field emptyField(int width, int height) {
        Field field = new Field();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                field.setSquare(new Square(new Position(x, y), false));
            }
        }
        return field;
    }
    
    /**
     * Alustaa uuden Field-olion, johon asetetaan satunnaisiin paikkoihin miinoja
     */
    public static Field randomField(int width, int height, int mines) {
        if(mines > width * height) {
            throw new InvalidParameterException("Can't have more mines than there are squares");
        }
        
        Field field = emptyField(width, height);
        Object[] squares = field.getAllSquares().toArray();
        Random rand = new Random();
        
        while (mines > 0) {
            Square randomSquare = (Square) squares[rand.nextInt(squares.length)];
            if(!randomSquare.isMine()) {
                randomSquare.setMine(true);
                mines--;
            }
        }
        return field;
    }
}
