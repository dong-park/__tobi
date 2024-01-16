package dongpark.config.autoconfig;

import dongpark.config.MyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@MyAutoConfiguration
public class PropertyPlaceholderConfig {

    @Bean
    PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
