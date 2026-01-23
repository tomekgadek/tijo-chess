package pl.edu.atar.game.checker;

import pl.edu.atar.game.model.FigurePosition;

public interface MovesChecker<T extends Enum<T>> {

    T getType();

    boolean isCorrect(FigurePosition begin, FigurePosition destination);
}
