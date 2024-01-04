package dongpark.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController // 타입 레벨을 지정해야 request mapping을 찾아서 처리해준다. 모든 이 안에 있는 메소드는 response body로 처리된다.
@RequestMapping("/hello") // 타입 레벨에 request mapping을 지정하면, 메소드 레벨에 지정된 request mapping은 타입 레벨의 request mapping 뒤에 붙는다.
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    // @ResponseBody // 이걸 붙이면, return 값이 response body로 처리된다.
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
