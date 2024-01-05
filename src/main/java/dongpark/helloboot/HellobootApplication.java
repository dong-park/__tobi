package dongpark.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan // 이 클래스의 하위 패키지를 스캔해서 @Component가 붙은 클래스를 찾아서 스프링 컨테이너에 등록한다.
public class HellobootApplication {

    @Bean
    public ServletWebServerFactory serverFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                // get bean from spring container
                ServletWebServerFactory factory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet servlet = this.getBean(DispatcherServlet.class);

                // 스프링 컨테이너에web application context가 refresh되면
                // servlet container를 생성하고, servlet container에 servlet을 추가한다.
                // embedded tomcat server
                WebServer webServer = factory.getWebServer(
                        // servlet container init
                        // functional interface to lambda
                        servletContext -> {
                            // dispatcher servlet: front controller of spring mvc
                            servletContext.addServlet("dispatcherServlet", servlet).addMapping("/*");
                        }
                );
                webServer.start();
            }
        };
        context.register(HellobootApplication.class);
        context.refresh();
    }
}
