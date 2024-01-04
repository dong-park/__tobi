package dongpark.helloboot;

public class HelloController {

    private String name;

    public HelloController(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hello, Spring Boot! " + name;
    }
}
