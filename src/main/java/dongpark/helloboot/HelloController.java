package dongpark.helloboot;

public class HelloController {

    private String name;

    public HelloController() {
        this.name = "";
    }

    @Override
    public String toString() {
        return "Hello, Spring Boot! " + name;
    }

    public void hello(String name) {
        this.name = name;
    }
}
