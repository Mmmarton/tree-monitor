package training.nuttyyokel.controller;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import training.nuttyyokel.dto.ResponseDTO;
import training.nuttyyokel.dto.TreeDTO;
import training.nuttyyokel.dto.TreeUpdateDTO;
import training.nuttyyokel.model.Tree;
import training.nuttyyokel.service.TreeService;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api/tree")
public class TreeController {

  @Autowired
  private TreeService treeService;

  @Autowired
  private ModelMapper mapper;

  @RequestMapping(method = RequestMethod.GET, value = "/")
  public List<TreeDTO> getAll() {
    return mapList(treeService.getAll());
  }

  @RequestMapping(method = RequestMethod.GET, value = "{id}")
  public TreeDTO getTree(@PathVariable("id") int id) {
    return mapper.map(treeService.getTree(id), TreeDTO.class);
  }

  @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json")
  public ResponseEntity<Object> save(@Valid @RequestBody TreeDTO treeDTO) {
    treeService.save(mapper.map(treeDTO, Tree.class));
    return new ResponseDTO("success", HttpStatus.OK).build();
  }

  @RequestMapping(method = RequestMethod.POST, value = "{id}", produces = "application/json")
  public ResponseEntity<Object> update(@Valid @RequestBody TreeUpdateDTO treeUpdateDTO, @PathVariable("id") int id) {
    Tree tree = mapper.map(treeUpdateDTO, Tree.class);
    tree.setId(id);
    treeService.update(tree);
    return new ResponseDTO("success", HttpStatus.OK).build();
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "{id}", produces = "application/json")
  public ResponseEntity<Object> delete(@PathVariable("id") int id) {
    treeService.delete(id);
    return new ResponseDTO("success", HttpStatus.OK).build();
  }

  private List<TreeDTO> mapList(List<Tree> trees) {
    Type dataListType = new TypeToken<List<TreeDTO>>() {
    }.getType();
    return mapper.map(trees, dataListType);
  }
}
