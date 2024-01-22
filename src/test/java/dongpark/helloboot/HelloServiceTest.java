package dongpark.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface FastUnitTest {

}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Test
@interface UnitTest {

}


public class HelloServiceTest {
    @FastUnitTest
    void simpleHelloServiceTest() {
        HelloService helloService = new SimpleHelloService(helloRepositoryStub);
        String result = helloService.sayHello("dongpark");
        Assertions.assertThat(result).isEqualTo("Hello, dongpark");
    }

    private static HelloRepository helloRepositoryStub = new HelloRepository() {
        @Override
        public Hello findHello(String name) {
            return null;
        }

        @Override
        public void increaseCount(String name) {

        }
    };

    @UnitTest
    void decoratorHelloServiceTest() {
        HelloService helloService = new HelloDecorator(name -> name);
        String result = helloService.sayHello("dongpark");
        Assertions.assertThat(result).isEqualTo("*dongpark*");
    }
}
