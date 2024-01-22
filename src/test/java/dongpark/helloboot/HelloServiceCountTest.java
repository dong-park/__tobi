package dongpark.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@HelloBootTest
public class HelloServiceCountTest {

    @Autowired HelloService helloService;
    @Autowired HelloRepository helloRepository;

    @Test
    void sayHelloIncrementsCount() {
        helloRepository.increaseCount("bob");
        Assertions.assertThat(helloRepository.countOf("bob")).isEqualTo(1);
    }

}
