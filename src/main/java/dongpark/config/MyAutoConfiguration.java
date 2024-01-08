package dongpark.config;

import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
@Configuration(proxyBeanMethods = false)
public @interface MyAutoConfiguration {
}
