package training.nuttyyokel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training.nuttyyokel.model.Tree;
import training.nuttyyokel.repository.TreeRepository;

import java.util.List;

@Service
public class TreeService {

    @Autowired
    TreeRepository treeRepository;

    public List<Tree> getAll() {
        return (List<Tree>) treeRepository.findAll();
    }
}
