package training.nuttyyokel.builders;


import training.nuttyyokel.dto.tree.TreeUpdateRequest;

public class TreeUpdateRequestBuilder {

  private int health;
  private int height;

  public TreeUpdateRequestBuilder normalTreeUpdateRequest() {
    health = TreeBuilder.HEALTH;
    height = TreeBuilder.HEIGHT;
    return this;
  }

  public TreeUpdateRequestBuilder emptyTreeUpdateRequest() {
    return this;
  }

  public TreeUpdateRequestBuilder setHealth(int health) {
    this.health = health;
    return this;
  }

  public TreeUpdateRequestBuilder setHeight(int height) {
    this.height = height;
    return this;
  }

  public TreeUpdateRequest build() {
    TreeUpdateRequest treeUpdateRequest = new TreeUpdateRequest();
    treeUpdateRequest.setHealth(health);
    treeUpdateRequest.setHeight(height);
    return treeUpdateRequest;
  }


}
