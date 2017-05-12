package training.nuttyyokel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tree")
public class TreeController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String home() {
        System.out.println("Called home");
        return "Trees";
    }
}
