package pl.edu.atar.chess.domain;

import pl.edu.atar.chess.dto.FigurePosition;

interface MovesChecker<T extends Enum<T>> {

    T getType();

    boolean isCorrect(FigurePosition begin, FigurePosition destination);
}
