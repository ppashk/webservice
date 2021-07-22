package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ValidationServiceTest {
    @Autowired
    private ValidationService validationService;

    @Test
    void performFirstExample() {
        List<String> result = new ArrayList<>();
        result.add("fish");
        result.add("horse");
        result.add("egg");
        result.add("goose");
        result.add("eagle");

        String url = "https://api.jsonbin.io/b/60f999d4a917050205cdc83d";
        assertThat(validationService.perform(url).equals(result));
    }

    @Test
    void performSecondExample() {
        List<String> result = new ArrayList<>();
        result.add("fish");
        result.add("horse");

        String url = "https://api.jsonbin.io/b/60f9c8bf99892a4ae9a82858";
        assertThat(validationService.perform(url).equals(result));
    }

    @Test
    void performThirdExample() {
        List<String> result = new ArrayList<>();
        result.add("fish");
        result.add("horse");

        String url = "https://api.jsonbin.io/b/60f9ccf599892a4ae9a82afd";
        assertThat(validationService.perform(url).equals(result));
    }

    @Test
    void performFourthExample() {
        String url = "https://api.jsonbin.io/b/60f999d4a917050205cdc83d";
        assertThat(validationService.perform(url) == null);
    }

    @Test
    void performFailTest() {
        String url = "dnm";
        assertThat(validationService.perform(url) == null);
    }
}