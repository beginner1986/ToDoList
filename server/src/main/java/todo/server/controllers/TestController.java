package todo.server.controllers;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("!prod")
@RestController
public class TestController {

    @GetMapping("/test")
    public String checkGet() {
        return "test";
    }
}
