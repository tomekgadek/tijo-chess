package pl.edu.atar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import pl.edu.atar.domain.dto.FigureMoveDto;
import pl.edu.atar.domain.enums.FigureType;
import pl.edu.atar.domain.mapper.InternalMapper;
import pl.edu.atar.game.checker.MovesChecker;
import pl.edu.atar.game.model.FigurePosition;

import java.util.List;
import java.util.Optional;

@Service
public class ChessServiceImpl implements ChessService {
    private final InternalMapper<String, FigurePosition> positionInternalMapper;

    private final List<MovesChecker<FigureType>> checkers;

    @Autowired
    public ChessServiceImpl(
            InternalMapper<String, FigurePosition> positionInternalMapper,
            List<MovesChecker<FigureType>> checkers
    ) {
        this.positionInternalMapper = positionInternalMapper;
        this.checkers = checkers;
    }

    @Override
    public boolean isMoveCorrect(FigureMoveDto figureMoveDto) {

        if(figureMoveDto.start().isBlank() || figureMoveDto.destination().isBlank()) {
            return false;
        }

        final Pair<FigurePosition, FigurePosition> figurePositionPair =
                getBeginAndDestinationPair(figureMoveDto);

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
        return Pair.of(
                positionInternalMapper.map(figureMoveDto.start()),
                positionInternalMapper.map(figureMoveDto.destination())
        );
    }
}
