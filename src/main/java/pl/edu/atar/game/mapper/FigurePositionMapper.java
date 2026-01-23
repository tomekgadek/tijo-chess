package pl.edu.atar.game.mapper;

import org.springframework.stereotype.Component;
import pl.edu.atar.domain.mapper.InternalMapper;
import pl.edu.atar.game.model.FigurePosition;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class FigurePositionMapper implements InternalMapper<String, FigurePosition> {

    @Override
    public FigurePosition map(String original) {
        final List<String> positions = Pattern.compile("_")
                .splitAsStream(original)
                .toList();

        return new FigurePosition(positions.get(0), positions.get(1));
    }
}
