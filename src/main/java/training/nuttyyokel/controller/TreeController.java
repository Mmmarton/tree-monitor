package training.nuttyyokel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<Tree> getAll() {
        return treeService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<String> save(@RequestBody Tree tree) {
        treeService.save(tree);
        return ResponseEntity.ok("success");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        treeService.delete(id);
        return ResponseEntity.ok("success");
    }
}
