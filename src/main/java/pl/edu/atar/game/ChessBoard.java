package pl.edu.atar.game;

import java.util.List;

public class ChessBoard {

    public static final List<String> X_BOARD_ELEMENTS = List.of("a", "b", "c", "d", "e", "f", "g", "h");

    public static final List<String> Y_BOARD_ELEMENTS = List.of("1", "2", "3", "4", "5", "6", "7", "8");

    public static int getXIndex(String x) {
        return X_BOARD_ELEMENTS.indexOf(x);
    }

    public static int getYIndex(String y) {
        return Y_BOARD_ELEMENTS.indexOf(y);
    }
}
