package pl.edu.atar.service;

import pl.edu.atar.domain.dto.FigureMoveDto;

public interface ChessService {

    boolean isMoveCorrect(FigureMoveDto figureMoveDto);
}
