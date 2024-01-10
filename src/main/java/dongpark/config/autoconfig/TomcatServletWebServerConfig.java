package dongpark.config.autoconfig;

import dongpark.config.ConditionalMyOnClass;
import dongpark.config.MyAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatServletWebServerConfig {

    @Bean("tomcatServerFactory")
    public ServletWebServerFactory serverFactory() {
        return new TomcatServletWebServerFactory();
    }
}
