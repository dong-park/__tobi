package dongpark.config.autoconfig;

import dongpark.config.MyAutoConfiguration;
import dongpark.config.MyConfigurationProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.Map;

import static org.springframework.core.annotation.AnnotationUtils.*;

@MyAutoConfiguration
public class PropertyPostProcessorConfig {

    // Environment는 application.properties를 읽어서 key-value로 저장해놓은 객체
    // BeanPostProcessor는 bean이 생성되기 전후에 실행되는 메소드를 가진 객체
    @Bean
    BeanPostProcessor propertyPostProcessor(Environment env) {
        return new BeanPostProcessor() {

            // bean이 생성되기 전에 실행되는 메소드
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

                // annotation이 없으면 그냥 bean을 리턴
                MyConfigurationProperties annotation = findAnnotation(bean.getClass(), MyConfigurationProperties.class);
                if (annotation == null) return bean;

                // annotation이 있으면 prefix를 찾아서 bindOrCreate
                // bindOrCreate는 prefix로 시작하는 property를 찾아서 bean에 바인딩하거나 없으면 bean을 생성해서 바인딩
                Map<String, Object> attrs = getAnnotationAttributes(annotation);
                String prefix = (String) attrs.get("prefix");
                return Binder.get(env).bindOrCreate(prefix, bean.getClass());
            }
        };

    }

}
