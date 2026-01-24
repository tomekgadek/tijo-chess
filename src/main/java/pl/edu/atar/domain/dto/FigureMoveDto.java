package pl.edu.atar.domain.dto;

import pl.edu.atar.domain.enums.FigureType;

import java.io.Serializable;

public record FigureMoveDto(
        String start,
        String destination,
        FigureType type) implements Serializable { }
