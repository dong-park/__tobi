package dongpark.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {

    @Test
    void configuration() {
        // common() 메소드는 두 개의 Bean1, Bean2에서 공통으로 사용되는 Bean이다.
        // Bean1, Bean2는 각각 다른 인스턴스를 사용한다.
        // but! common을 bean으로 등록하면 Bean1, Bean2에서 공통으로 사용되는 Bean이 되고, Bean1, Bean2는 같은 인스턴스를 사용한다.

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfiguration.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);

        //
    }

    @Configuration
    // proxyBeanMethods = false: @Configuration이 붙은 클래스의 @Bean 메소드들은 모두 프록시로 감싸지지 않고 직접 호출된다.
    // 최근들어 복수의 bean을 호출하는게 아니라면 proxyBeanMethods = false를 사용하는 것이 좋다고 권장되고 있다.ㄴ
    static class MyConfiguration {

        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }

    }

    @Test
    void proxyCommonMethod() {
        MyConfigProxy myConfigProxy = new MyConfigProxy();

        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    static class MyConfigProxy extends MyConfiguration {

        private Common common;

        @Override
        Common common() {
            // 하나에 빈에서 common() 메소드를 호출하면 common이라는 필드에 저장해두고, 다음에 또 호출되면 저장해둔 common을 리턴한다.
            if (common == null) this.common = super.common();

            return common;
        }
    }

    static class Bean1 {
        private final Common common;

        Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        Bean2(Common common) {
            this.common = common;
        }
    }

    private static class Common {
    }
}
