package training.nuttyyokel.configuration;


import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableAutoConfiguration
@ComponentScan(basePackages = "training.nuttyyokel")
public class TestConfiguration extends WebMvcConfigurerAdapter {

  private static ModelMapper modelMapper = new ModelMapper();

  @Bean
  public ModelMapper modelMapper() {
    return modelMapper;
  }
}
