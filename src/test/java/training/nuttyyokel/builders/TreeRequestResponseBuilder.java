package training.nuttyyokel.builders;


import training.nuttyyokel.dto.tree.TreeRequestResponse;

import java.util.Date;

public class TreeRequestResponseBuilder {

  private int id = 1;
  private Date datePlanted;
  private int health;
  private int height;
  private String name;
  private String type;

  public TreeRequestResponseBuilder normalTreeRequestResponseBuilder() {
    datePlanted = new Date();
    health = 5;
    height = 100;
    name = "Peter";
    type = "Pine";
    return this;
  }

  public TreeRequestResponseBuilder emptyTreeRequestResponseBuilder() {
    return this;
  }

  public void setDatePlanted(Date datePlanted) {
    this.datePlanted = datePlanted;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setType(String type) {
    this.type = type;
  }

  public TreeRequestResponse build() {
    TreeRequestResponse treeSaveRequest = new TreeRequestResponse();
    treeSaveRequest.setDatePlanted(datePlanted);
    treeSaveRequest.setHealth(health);
    treeSaveRequest.setHeight(height);
    treeSaveRequest.setName(name);
    treeSaveRequest.setType(type);
    return treeSaveRequest;
  }
}
