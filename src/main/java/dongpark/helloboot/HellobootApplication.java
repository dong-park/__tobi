package dongpark.helloboot;

import dongpark.config.MySpringBootAnnotation;
import org.springframework.boot.SpringApplication;

@MySpringBootAnnotation
public class HellobootApplication {
    public static void main(String[] args) {
        SpringApplication.run(HellobootApplication.class, args); // 결국엔 원형으로 돌아갔다..
    }

}
