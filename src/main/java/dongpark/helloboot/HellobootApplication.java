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

import java.io.IOException;

@SpringBootApplication
public class HellobootApplication {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("helloController", HelloController.class);
        context.refresh();

        // embedded tomcat server
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        WebServer webServer = factory.getWebServer(
                // servlet container init
                // functional interface to lambda
                servletContext -> {
                    servletContext.addServlet(
                            // servlet container add servlet
                            "frontcontroller", new HttpServlet() {
                                @Override
                                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                                    // auth, security, logging, ...

                                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                                        String name = req.getParameter("name");
                                        HelloController helloController = context.getBean("helloController", HelloController.class);

                                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                                        resp.getWriter().println(helloController.hello(name));
                                    } else {
                                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                                    }

                                }
//                            }).addMapping("/hello");
                            }).addMapping("/*"); // front controller
                }
        );
        webServer.start();

    }
}
