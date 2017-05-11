package training.nuttyyokel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tree")
public class TreeController {

    @RequestMapping
    public String home() {
        return "Trees";
    }
}
