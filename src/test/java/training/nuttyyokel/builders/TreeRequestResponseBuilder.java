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
    datePlanted = TreeBuilder.DATE_PLANTED;
    health = TreeBuilder.HEALTH;
    height = TreeBuilder.HEIGHT;
    name = TreeBuilder.NAME;
    type = TreeBuilder.TYPE;
    return this;
  }

  public TreeRequestResponseBuilder emptyTreeRequestResponseBuilder() {
    return this;
  }

  public TreeRequestResponseBuilder setDatePlanted(Date datePlanted) {
    this.datePlanted = datePlanted;
    return this;
  }

  public TreeRequestResponseBuilder setHealth(int health) {
    this.health = health;
    return this;
  }

  public TreeRequestResponseBuilder setHeight(int height) {
    this.height = height;
    return this;
  }

  public TreeRequestResponseBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public TreeRequestResponseBuilder setType(String type) {
    this.type = type;
    return this;
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
