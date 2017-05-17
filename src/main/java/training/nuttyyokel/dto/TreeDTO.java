package training.nuttyyokel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author DrMarcell
 */
public class TreeDTO {

  @Id
  private int id;

  @NotNull
  private String name;

  @NotNull
  private String type;

  @NotNull
  @Past
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date datePlanted;

  @Min(1)
  @Max(100000)
  private double height;

  @Min(1)
  @Max(9)
  private int health;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Date getDatePlanted() {
    return datePlanted;
  }

  public void setDatePlanted(Date datePlanted) {
    this.datePlanted = datePlanted;
  }

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
