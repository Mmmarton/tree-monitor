package training.nuttyyokel.dto.tree;

import training.nuttyyokel.dto.GenericResponse;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author DrMarcell
 */
public class TreeUpdateRequest extends GenericResponse {

  @Min(1)
  @Max(100000)
  private double height;

  @Min(1)
  @Max(9)
  private int health;

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }
}
