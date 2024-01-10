package dongpark.config.autoconfig;

import dongpark.config.MyAutoConfiguration;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

@MyAutoConfiguration
@Conditional(JettyServletWebServerConfig.JettyCondition.class)
public class JettyServletWebServerConfig {

    @Bean("jettyServerFactory")
    public ServletWebServerFactory serverFactory() {
        return new JettyServletWebServerFactory();
    }

    static class JettyCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            // Jetty를 사용할 수 있는지 여부를 체크하는 로직을 작성한다.
            // 같은 serverFactory() 메소드를 가진 TomcatServletWebServerConfig 클래스의 TomcatCondition 클래스와 비교해보자.
            // TomcatCondition 클래스는 matches() 메소드가 false를 리턴하도록 작성되어 있다.
            // 두개가 다 true를 리턴하도록 작성하면 어떻게 될까? 에러가 발생한다.
            // 두개의 같은 형태의 빈이 존재하는 경우 matches() 메소드가 여러개의 true가 발생하기 때문이다.
            return true;
        }
    }
}
