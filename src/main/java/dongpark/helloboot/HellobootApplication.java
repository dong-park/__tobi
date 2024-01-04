package dongpark.helloboot;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

@SpringBootApplication
public class HellobootApplication {

    public static void main(String[] args) {
        // tomcat server start
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
                                        resp.setStatus(HttpStatus.OK.value());
                                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                                        resp.getWriter().println("Hello, Spring Boot! " + name);
                                    } else if (req.getRequestURI().equals("/user")) {
                                        resp.setStatus(HttpStatus.OK.value());
                                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                                        resp.getWriter().println("user");
                                    } else {
                                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                                        resp.getWriter().println("404 Not Found");
                                    }

                                }
//                            }).addMapping("/hello");
                            }).addMapping("/*"); // front controller
                }
        );
        webServer.start();

    }

}
