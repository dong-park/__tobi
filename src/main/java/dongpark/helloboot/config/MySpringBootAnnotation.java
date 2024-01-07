package dongpark.helloboot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
@Configuration
@ComponentScan // 이 클래스의 하위 패키지를 스캔해서 @Component가 붙은 클래스를 찾아서 스프링 컨테이너에 등록한다.
public @interface MySpringBootAnnotation {

}
