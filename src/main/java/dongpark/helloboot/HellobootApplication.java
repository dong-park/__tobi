package dongpark.helloboot;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

@SpringBootApplication
public class HellobootApplication {

    public static void main(String[] args) {
        GenericWebApplicationContext context = new GenericWebApplicationContext();
        context.registerBean("helloController", HelloController.class);
        context.registerBean("helloService", SimpleHelloService.class); /// helloController.helloService is constructor injected with SimpleHelloService with no qualifier

        context.refresh();

        // embedded tomcat server
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        WebServer webServer = factory.getWebServer(
                // servlet container init
                // functional interface to lambda
                servletContext -> {
                    servletContext.addServlet(
                            "dispatcherServlet", new DispatcherServlet(context)) // dispatcher servlet: front controller of spring mvc
                            .addMapping("/*"); // front controller
                }
        );
        webServer.start();

    }
}
