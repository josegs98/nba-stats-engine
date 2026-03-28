package com.josegs98.nbastats;

import com.josegs98.nbastats.cli.CliRunner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("dev")
class NbaStatsApplicationTests {

    @MockitoBean
    CliRunner cliRunner;

    @Test
    void contextLoads() {
    }
}
