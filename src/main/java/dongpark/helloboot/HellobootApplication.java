package dongpark.helloboot;

import dongpark.config.MySpringBootAnnotation;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@MySpringBootAnnotation
public class HellobootApplication {

    public static void main(String[] args) {
        SpringApplication.run(HellobootApplication.class, args); // 결국엔 원형으로 돌아갔다..
    }

}
