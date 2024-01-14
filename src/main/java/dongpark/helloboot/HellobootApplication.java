package dongpark.helloboot;

import dongpark.config.MySpringBootAnnotation;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@MySpringBootAnnotation
public class HellobootApplication {

    @Bean
    ApplicationRunner applicationRunner(Environment env) {
        return args -> {
            System.out.println("foo: " + env.getProperty("foo"));
            // 단 application.properties에 foo=bar를 추가해야 한다.
            // application.properties는 envrionment보다 우선순위가 낮다.
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(HellobootApplication.class, args); // 결국엔 원형으로 돌아갔다..
    }

}
