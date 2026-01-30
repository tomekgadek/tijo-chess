package pl.edu.atar.chess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.atar.chess.domain.ChessFacade;
import pl.edu.atar.chess.dto.FigureMoveDto;

@RestController
@RequestMapping(value = "/api")
class ChessApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChessApiController.class);

    private final ChessFacade chessFacade;

    public ChessApiController(ChessFacade chessFacade) {
        this.chessFacade = chessFacade;
    }

    @CrossOrigin
    @PostMapping(value = "/chess/is-correct-move")
    ResponseEntity<Boolean> isCorrectMove(@RequestBody FigureMoveDto figureMoveDto) {
        LOGGER.info("*** move details : {}", figureMoveDto);
        return ResponseEntity.ok(chessFacade.isMoveCorrect(figureMoveDto));
    }
}
