package training.nuttyyokel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import training.nuttyyokel.model.Tree;
import training.nuttyyokel.service.TreeService;

import java.util.List;

@RestController
@RequestMapping("/api/tree")
public class TreeController {

    @Autowired
    private TreeService treeService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String home() {
        return "Trees";
    }

    @RequestMapping(method = RequestMethod.GET, value = "all")
    public List<Tree> getAll() {
        return treeService.getAll();
    }
}
