package training.nuttyyokel.dto.tree;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author DrMarcell
 */
public class TreeUpdateRequest {

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
