package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    private final ValidationService validationService;

    public MainController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @GetMapping
    public String get() {
        return "index";
    }

    @PostMapping
    public String post(@RequestParam(required = true) String url, Map<String, Object> model) throws Exception {
        model.put("words", validationService.perform(url));
        return "index";
    }
}
