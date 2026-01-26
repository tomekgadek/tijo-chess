package pl.edu.atar.game.checker;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.atar.game.model.FigurePosition;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BishopMovesCheckerUnitTest {

    private final BishopMovesChecker checker;

    @Autowired
    public BishopMovesCheckerUnitTest(BishopMovesChecker checker) {
        this.checker = checker;
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
