package dongpark.config.autoconfig;

import dongpark.config.MyAutoConfiguration;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@MyAutoConfiguration
public class JettyServletWebServerConfig {

    @Bean("jettyServerFactory")
    public ServletWebServerFactory serverFactory() {
        return new JettyServletWebServerFactory();
    }

}
