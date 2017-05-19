package training.nuttyyokel.builders;


import training.nuttyyokel.dto.tree.TreeUpdateRequest;

public class TreeUpdateRequestBuilder {

  private int health;
  private int height;

  public TreeUpdateRequestBuilder normalTreeUpdateRequestBuilder() {
    health = 5;
    height = 100;
    return this;
  }

  public TreeUpdateRequestBuilder emptyTreeUpdateRequestBuilder() {
    return this;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public TreeUpdateRequest build() {
    TreeUpdateRequest treeUpdateRequest = new TreeUpdateRequest();
    treeUpdateRequest.setHealth(health);
    treeUpdateRequest.setHeight(height);
    return treeUpdateRequest;
  }


}
