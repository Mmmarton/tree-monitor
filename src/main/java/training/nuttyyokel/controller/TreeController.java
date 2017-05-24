package training.nuttyyokel.controller;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import training.nuttyyokel.dto.GenericResponse;
import training.nuttyyokel.dto.tree.TreeRequestResponse;
import training.nuttyyokel.dto.tree.TreeSaveResponse;
import training.nuttyyokel.dto.tree.TreeUpdateRequest;
import training.nuttyyokel.model.Tree;
import training.nuttyyokel.service.TreeService;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api/tree")
public class TreeController {

  public final static String MESSAGE_SUCCESS = "success";
  public final static String MESSAGE_INVALID_ID = "invalid id";
  public final static String MESSAGE_INVALID_FIELD = "invalid field";

  @Autowired
  private TreeService treeService;

  @Autowired
  private ModelMapper mapper;

  @RequestMapping(method = RequestMethod.GET, value = "/")
  public List<TreeRequestResponse> getAll() {
    return mapList(treeService.getAll());
  }

  @RequestMapping(method = RequestMethod.GET, value = "{id}")
  public ResponseEntity<Object> getTree(@PathVariable("id") int id) {
    Tree tree = treeService.getTree(id);
    if (tree != null) {
      return mapper.map(tree, TreeRequestResponse.class).build();
    } else {
      return new GenericResponse(MESSAGE_INVALID_ID, HttpStatus.BAD_REQUEST).build();
    }
  }

  @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json")
  public ResponseEntity<Object> save(@Valid @RequestBody TreeRequestResponse treeRequestResponse) {
    int id = treeService.save(mapper.map(treeRequestResponse, Tree.class));
    return new TreeSaveResponse(id).build();
  }

  @RequestMapping(method = RequestMethod.POST, value = "{id}", produces = "application/json")
  public ResponseEntity<Object> update(
      @Valid @RequestBody TreeUpdateRequest treeUpdateRequest,
      @PathVariable("id") int id) {
    Tree tree = mapper.map(treeUpdateRequest, Tree.class);
    tree.setId(id);
    treeService.update(tree);
    return new GenericResponse(MESSAGE_SUCCESS, HttpStatus.OK).build();
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "{id}", produces = "application/json")
  public ResponseEntity<Object> delete(@PathVariable("id") int id) {
    treeService.delete(id);
    return new GenericResponse(MESSAGE_SUCCESS, HttpStatus.OK).build();
  }

  private List<TreeRequestResponse> mapList(List<Tree> trees) {
    Type dataListType = new TypeToken<List<TreeRequestResponse>>() {
    }.getType();
    return mapper.map(trees, dataListType);
  }
}
