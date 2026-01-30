package pl.edu.atar.chess.domain;

import org.springframework.data.util.Pair;
import pl.edu.atar.chess.dto.FigureMoveDto;
import pl.edu.atar.chess.dto.FigurePosition;
import pl.edu.atar.chess.dto.FigureType;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class ChessFacade {

    private final List<MovesChecker<FigureType>> checkers;

    public ChessFacade(List<MovesChecker<FigureType>> checkers) {
        this.checkers = checkers;
    }

    public boolean isMoveCorrect(FigureMoveDto figureMoveDto) {

        if(figureMoveDto.start().isBlank() || figureMoveDto.destination().isBlank()) {
            return false;
        }

        final Pair<FigurePosition, FigurePosition> figurePositionPair = getBeginAndDestinationPair(figureMoveDto);

        if (figurePositionPair.getFirst().equals(figurePositionPair.getSecond())) {
            return false;
        }

        Optional<MovesChecker<FigureType>> figure = findByDto(figureMoveDto);

        return figure.map(figureTypeMovesChecker -> figureTypeMovesChecker.isCorrect(
                figurePositionPair.getFirst(),
                figurePositionPair.getSecond()
        )).orElse(false);
    }

    private Optional<MovesChecker<FigureType>> findByDto(FigureMoveDto figureMoveDto) {
        return checkers
                .stream()
                .filter(checker -> figureMoveDto.type().equals(checker.getType()))
                .findFirst();
    }

    private Pair<FigurePosition, FigurePosition> getBeginAndDestinationPair(FigureMoveDto figureMoveDto) {
        final List<String> pointStart = Pattern.compile("_")
                .splitAsStream(figureMoveDto.start())
                .toList();

        final List<String> pointDestination = Pattern.compile("_")
                .splitAsStream(figureMoveDto.destination())
                .toList();

        return Pair.of(
                new FigurePosition(pointStart.get(0), pointStart.get(1)),
                new FigurePosition(pointDestination.get(0), pointDestination.get(1))
        );
    }
}
