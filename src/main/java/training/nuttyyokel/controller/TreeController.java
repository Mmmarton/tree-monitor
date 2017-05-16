package training.nuttyyokel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import training.nuttyyokel.dto.ResponseDTO;
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

  @RequestMapping(method = RequestMethod.GET, value = "{id}")
  public Tree getTree(@PathVariable("id") int id) {
    return treeService.getTree(id);
  }

  @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json")
  public ResponseDTO save(@RequestBody Tree tree) {
    treeService.save(tree);
    return new ResponseDTO("success", HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST, value = "{id}", produces = "application/json")
  public ResponseDTO update(@RequestBody Tree tree) {
    treeService.update(tree);
    return new ResponseDTO("success", HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "{id}", produces = "application/json")
  public ResponseDTO delete(@PathVariable("id") int id) {
    treeService.delete(id);
    return new ResponseDTO("success", HttpStatus.OK);
  }
}
