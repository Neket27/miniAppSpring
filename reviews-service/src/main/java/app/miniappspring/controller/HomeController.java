package app.miniappspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("")
    public String hi(){
        return "Hello!";
    }

    @GetMapping("/hi")
    public String hi2(){
        return "Hello2!";
    }
}
