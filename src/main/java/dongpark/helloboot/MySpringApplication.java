package dongpark.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {

    public static void run(Class<?> applicationClass, String... args) {
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
        context.register(applicationClass);
        context.refresh();
    }

}
