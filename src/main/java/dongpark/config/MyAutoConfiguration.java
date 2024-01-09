package dongpark.config;

import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
@Configuration(proxyBeanMethods = false) // proxyBeanMethods = false: @Configuration이 붙은 클래스의 @Bean 메소드들은 모두 프록시로 감싸지지 않고 직접 호출된다.
public @interface MyAutoConfiguration {
}
