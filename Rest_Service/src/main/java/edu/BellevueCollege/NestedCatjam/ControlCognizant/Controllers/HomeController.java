package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin!";
    }
}
