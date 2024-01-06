package dongpark.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan // 이 클래스의 하위 패키지를 스캔해서 @Component가 붙은 클래스를 찾아서 스프링 컨테이너에 등록한다.
public class HellobootApplication {

    @Bean
    public ServletWebServerFactory serverFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    public static void main(String[] args) {
        SpringApplication.run(HellobootApplication.class, args); // 결국엔 원형으로 돌아갔다..
    }


}
