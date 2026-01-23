package pl.edu.atar.game.checker;

import org.springframework.stereotype.Component;
import pl.edu.atar.domain.enums.FigureType;
import pl.edu.atar.game.ChessBoard;
import pl.edu.atar.game.model.FigurePosition;

@Component

public class BishopMovesChecker implements MovesChecker<FigureType> {

    @Override
    public FigureType getType() {
        return FigureType.BISHOP;
    }

    @Override
    public boolean isCorrect(FigurePosition begin, FigurePosition destination) {

        final int beginX = ChessBoard.getXIndex(begin.x());
        final int destinationX = ChessBoard.getXIndex(destination.x());

        final int beginY = ChessBoard.getYIndex(begin.y());
        final int destinationY = ChessBoard.getYIndex(destination.y());

        return Math.abs(beginX - destinationX) == Math.abs(beginY - destinationY);
    }
}
