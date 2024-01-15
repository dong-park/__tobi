package dongpark.config.autoconfig;

import dongpark.config.ConditionalMyOnClass;
import dongpark.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatServletWebServerConfig {

    @Bean("tomcatServerFactory")
    @ConditionalOnMissingBean
    public ServletWebServerFactory serverFactory(Environment env) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setContextPath(env.getProperty("contextPath", "/app"));
        return factory;
    }
}
