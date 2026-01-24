package pl.edu.atar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.atar.domain.dto.FigureMoveDto;
import pl.edu.atar.service.ChessService;

@Controller
@RequestMapping(value = "/api")
public class ChessApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChessApiController.class);

    private final ChessService chessService;

    @Autowired
    public ChessApiController(ChessService chessService) {
        this.chessService = chessService;
    }

    @CrossOrigin
    @PostMapping(value = "/chess/is-correct-move")
    public ResponseEntity<Boolean> isCorrectMove(@RequestBody FigureMoveDto figureMoveDto) {
        LOGGER.info("*** move details : {}", figureMoveDto);
        return ResponseEntity.ok(chessService.isMoveCorrect(figureMoveDto));
    }
}
