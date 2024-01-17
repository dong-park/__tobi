package dongpark.config.autoconfig.binding;

import dongpark.config.MyAutoConfiguration;
import dongpark.config.MyConfigurationProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.Map;

import static org.springframework.core.annotation.AnnotationUtils.*;

/**
 * PropertyPostProcessorConfig 클래스는 Spring 빈의 post-processing을 위한 커스텀 BeanPostProcessor를 제공합니다.
 *
 * 이 클래스는 @MyAutoConfiguration 어노테이션으로 표시되어, 동박(DongPark) 구성 설정의 일부로 자동 설정되는 컴포넌트임을 나타냅니다.
 * 주요 기능은 @MyConfigurationProperties 어노테이션이 붙은 빈에 대해 특정 프로퍼티를 자동으로 바인딩하는 것입니다.
 *
 * <p>이 클래스는 다음과 같은 주요 기능을 제공합니다:
 * <ul>
 *   <li>BeanPostProcessor를 정의하여, 빈이 초기화된 후 특정 처리를 수행</li>
 *   <li>@MyConfigurationProperties 어노테이션을 사용하여, 지정된 프로퍼티의 prefix에 따라 빈에 프로퍼티를 바인딩</li>
 * </ul>
 *
 * <p>사용 방법:
 * <pre>
 *   &#64;MyConfigurationProperties(prefix = "some.prefix")
 *   public class SomeClass {
 *     // 필드 정의
 *   }
 * </pre>
 *
 * <p>주의: 이 BeanPostProcessor는 빈의 초기화 이후에 실행되므로, 초기화 중에는 프로퍼티가 바인딩되지 않을 수 있습니다.
 *
 * @author 동박
 */
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
