package Game;

import java.awt.Color;

public enum Cell {
    ALIVE(Color.WHITE),
    DEAD(Color.BLACK);

    private Color color;

    Cell(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
