package net.kenkku.swagsweeper.game;

import net.kenkku.swagsweeper.util.Position;

/**
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
     * 
     * @todo satunnaisuus
     */
    public static Field randomField(int width, int height, int mines) {
        Field field = emptyField(width, height);
        return field;
    }
}
