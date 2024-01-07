package dongpark.helloboot.config;

import dongpark.helloboot.config.autoconfig.DispatcherServletConfig;
import dongpark.helloboot.config.autoconfig.TomcatServletWebServerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
@Import({TomcatServletWebServerConfig.class, DispatcherServletConfig.class})
public @interface EnableMyAutoConfiguration {

}
