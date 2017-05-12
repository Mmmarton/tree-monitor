package training.nuttyyokel.repository;

import org.springframework.data.repository.CrudRepository;
import training.nuttyyokel.model.Tree;

public interface TreeRepository extends CrudRepository<Tree, Integer> {
}
