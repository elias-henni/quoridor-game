package quoridor.quoridorModel;

import javafx.scene.paint.Color;

/**
 * changes color for pawn board and walls
 * @author Elias Henni
 */
public enum ColorPalette {
    GRAY(Color.rgb(210, 210, 210)), ORANGE(Color.rgb(247, 197, 159)), BLUE(Color.rgb(173, 216, 230)),
    YELLOW(Color.rgb(238,232,170)), PURPLE(Color.rgb(111, 115, 210)), DARKNIGHT(Color.rgb(0, 0, 0));

    private final Color color;
    ColorPalette(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
