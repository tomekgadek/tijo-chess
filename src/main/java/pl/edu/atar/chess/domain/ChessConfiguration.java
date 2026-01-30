package pl.edu.atar.chess.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.atar.chess.dto.FigureType;

import java.util.ArrayList;
import java.util.List;

@Configuration
class ChessConfiguration {

    @Bean
    ChessFacade chessFacade() {
        final List<MovesChecker<FigureType>> checkers = new ArrayList<>();
        checkers.add(new BishopMovesChecker());

        return new ChessFacade(checkers);
    }
}
