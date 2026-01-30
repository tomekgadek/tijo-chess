package pl.edu.atar.chess.domain;

import java.util.List;

class ChessBoard {

    static final List<String> X_BOARD_ELEMENTS = List.of("a", "b", "c", "d", "e", "f", "g", "h");

    static final List<String> Y_BOARD_ELEMENTS = List.of("1", "2", "3", "4", "5", "6", "7", "8");

    static int getXIndex(String x) {
        return X_BOARD_ELEMENTS.indexOf(x);
    }

    static int getYIndex(String y) {
        return Y_BOARD_ELEMENTS.indexOf(y);
    }
}
