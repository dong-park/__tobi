package dongpark.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {

    @Test
    void hello() {
        HelloController controller = new HelloController(name -> name);
        String result = controller.hello("dongpark");
        Assertions.assertThat(result).isEqualTo("dongpark");
    }

    @Test
    void failHello() {
        HelloController controller = new HelloController(name -> name);

        // null, "" 둘 다 IllegalArgumentException 발생
        Assertions.assertThatThrownBy(() -> {
            controller.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> {
            controller.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
