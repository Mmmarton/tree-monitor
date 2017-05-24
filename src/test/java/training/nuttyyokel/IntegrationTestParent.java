package training.nuttyyokel;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import training.nuttyyokel.builders.WebPathBuilder;
import training.nuttyyokel.configuration.TestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:/clear.sql"})
public class IntegrationTestParent {

  @Value("${local.server.port}")
  private int port;

  private RestTemplate restTemplate;

  public IntegrationTestParent() {
    restTemplate = new TestRestTemplate().getRestTemplate();
  }

  public RestTemplate getRestTemplate() {
    return restTemplate;
  }

  public WebPathBuilder getWebPath() {
    return new WebPathBuilder(port);
  }
}
