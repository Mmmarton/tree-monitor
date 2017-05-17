package training.nuttyyokel;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "training.nuttyyokel")
public class Application {

  private static ModelMapper modelMapper;

  public static void main(String[] args) {
    modelMapper = new ModelMapper();
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return modelMapper;
  }
}
