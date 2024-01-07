package dongpark.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {
    @Test
    void simpleHelloServiceTest() {
        HelloService helloService = new SimpleHelloService();
        String result = helloService.sayHello("dongpark");
        Assertions.assertThat(result).isEqualTo("Hello, dongpark");
    }

    @Test
    void decoratorHelloServiceTest() {
        HelloService helloService = new HelloDecorator(name -> name);
        String result = helloService.sayHello("dongpark");
        Assertions.assertThat(result).isEqualTo("*dongpark*");
    }
}
