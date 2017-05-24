package training.nuttyyokel.builders;

public class WebPathBuilder {

  private String path;

  private int port;

  public WebPathBuilder(int port) {
    this.port = port;
  }

  public WebPathBuilder base() {
    path = "http://localhost:" + port + "/api/";
    return this;
  }

  public WebPathBuilder tree() {
    path += "tree/";
    return this;
  }

  public WebPathBuilder tree(int id) {
    path += "tree/" + id;
    return this;
  }

  public String build() {
    return path;
  }
}
