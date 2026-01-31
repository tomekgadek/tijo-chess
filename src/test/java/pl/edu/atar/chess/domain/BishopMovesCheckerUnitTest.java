package pl.edu.atar.chess.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.atar.chess.dto.FigurePosition;
import pl.edu.atar.chess.dto.FigureType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BishopMovesCheckerUnitTest {

    private final MovesChecker checker;

    @Autowired
    public BishopMovesCheckerUnitTest(List<MovesChecker<FigureType>> checkers) {
        this.checker = checkers.stream()
                .filter(checker -> checker.getType().equals(FigureType.BISHOP))
                .findFirst()
                .orElseThrow();
    }

    @ParameterizedTest
    @CsvSource({
            "a, 1, h, 8",
            "a, 1, g, 7",
            "a, 4, c, 2",
            "a, 2, b, 3",
    })
    void checkCorrectMoveForBishop(String xStart, String yStart, String xStop, String yStop) {

        // given
        FigurePosition source = new FigurePosition(xStart, yStart);
        FigurePosition destination = new FigurePosition(xStop, yStop);

        // when
        boolean result = checker.isCorrect(source, destination);

        // then
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({
            "c, 1, d, 3",
            "e, 1, e, 5"
    })
    void checkIncorrectMoveForBishop(String xStart, String yStart, String xStop, String yStop) {

        // given
        FigurePosition source = new FigurePosition(xStart, yStart);
        FigurePosition destination = new FigurePosition(xStop, yStop);

        // when
        boolean result = checker.isCorrect(source, destination);

        // then
        assertFalse(result);
    }
}
