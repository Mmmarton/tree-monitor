package training.nuttyyokel.dto.tree;

import training.nuttyyokel.dto.GenericResponse;

import javax.persistence.Id;

/**
 * @author DrMarcell
 */
public class TreeSaveResponse extends GenericResponse {

  @Id
  private int id;

  public TreeSaveResponse() {
  }

  public TreeSaveResponse(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
