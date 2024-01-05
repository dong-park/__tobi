package dongpark.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class HellobootApplication {

    @Bean
    public HelloController helloController(HelloService helloService) {
        return new HelloController(helloService);
    }

    @Bean
    public HelloService helloService() {
        return new SimpleHelloService();
    }

    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                // 스프링 컨테이너에web application context가 refresh되면
                // servlet container를 생성하고, servlet container에 servlet을 추가한다.
                // embedded tomcat server
                ServletWebServerFactory factory = new TomcatServletWebServerFactory();
                WebServer webServer = factory.getWebServer(
                        // servlet container init
                        // functional interface to lambda
                        servletContext -> {
                            servletContext.addServlet(
                                            "dispatcherServlet", new DispatcherServlet(this)) // dispatcher servlet: front controller of spring mvc
                                    .addMapping("/*"); // front controller
                        }
                );
                webServer.start();
            }
        };
        context.register(HellobootApplication.class);
        context.refresh();
    }
}
