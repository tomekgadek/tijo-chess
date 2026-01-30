package pl.edu.atar.chess.dto;

import java.io.Serializable;

public record FigureMoveDto(
        String start,
        String destination,
        FigureType type) implements Serializable { }
