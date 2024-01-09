package dongpark.config.autoconfig;

import dongpark.config.MyAutoConfiguration;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@MyAutoConfiguration
public class TomcatServletWebServerConfig {

    @Bean
    public ServletWebServerFactory serverFactory() {
        return new org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory();
    }

}
