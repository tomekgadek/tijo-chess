package pl.edu.atar.game.checker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BishopMovesCheckerIntegrationTest {

    @Test
    public void correctMoveFromA1ToH8(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/chess/is-correct-move")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"start\": \"a_1\", \"destination\":\"h_8\", \"type\": \"BISHOP\"}")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void incorrectMoveFromA1ToH8(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/chess/is-correct-move")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"start\": \"a_1\", \"destination\":\"b_3\", \"type\": \"BISHOP\"}")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}