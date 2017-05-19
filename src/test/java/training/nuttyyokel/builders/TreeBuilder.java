package training.nuttyyokel.builders;


import training.nuttyyokel.model.Tree;

import java.util.Date;

public class TreeBuilder {

  private Date datePlanted;
  private int health;
  private int height;
  private int id;
  private String name;
  private String type;

  public TreeBuilder normalTree() {
    datePlanted = new Date();
    health = 5;
    height = 100;
    id = 1;
    name = "Peter";
    type = "Pine";
    return this;
  }

  public TreeBuilder emptyTree() {
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

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Tree build() {
    Tree tree = new Tree();
    tree.setDatePlanted(datePlanted);
    tree.setHealth(health);
    tree.setHeight(height);
    tree.setId(id);
    tree.setName(name);
    tree.setType(type);
    return tree;
  }


}
