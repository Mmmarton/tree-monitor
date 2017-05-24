package training.nuttyyokel.builders;


import training.nuttyyokel.model.Tree;

import java.util.Date;

public class TreeBuilder {

  public static final Date DATE_PLANTED = new Date();
  public static final int HEALTH = 5;
  public static final int HEIGHT = 100;
  public static final int ID = 1;
  public static final String NAME = "Peter";
  public static final String TYPE = "Pine";

  private Date datePlanted;
  private int health;
  private int height;
  private int id;
  private String name;
  private String type;

  public TreeBuilder normalTree() {
    datePlanted = DATE_PLANTED;
    health = HEALTH;
    height = HEIGHT;
    id = ID;
    name = NAME;
    type = TYPE;
    return this;
  }

  public TreeBuilder emptyTree() {
    return this;
  }

  public TreeBuilder setDatePlanted(Date datePlanted) {
    this.datePlanted = datePlanted;
    return this;
  }

  public TreeBuilder setHealth(int health) {
    this.health = health;
    return this;
  }

  public TreeBuilder setHeight(int height) {
    this.height = height;
    return this;
  }

  public TreeBuilder setId(int id) {
    this.id = id;
    return this;
  }

  public TreeBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public TreeBuilder setType(String type) {
    this.type = type;
    return this;
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
