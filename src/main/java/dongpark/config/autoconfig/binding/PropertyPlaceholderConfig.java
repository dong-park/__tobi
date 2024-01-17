package dongpark.config.autoconfig.binding;

import dongpark.config.MyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * PropertyPlaceholderConfig 클래스는 Spring 프레임워크의 PropertySourcesPlaceholderConfigurer 빈을 제공합니다.
 *
 * 이 클래스는 @MyAutoConfiguration 어노테이션으로 표시되어, 동박(DongPark) 구성 설정의 일부로 자동 설정되는 컴포넌트임을 나타냅니다.
 * PropertySourcesPlaceholderConfigurer 빈은 프로퍼티 값들을 관리하고, Spring의 @Value 어노테이션을 통해
 * 프로퍼티 파일에 정의된 값을 Spring 빈의 필드에 주입하는 역할을 합니다.
 *
 * <p>이 클래스는 다음과 같은 주요 기능을 제공합니다:
 * <ul>
 *   <li>PropertySourcesPlaceholderConfigurer 객체를 스프링 컨텍스트에 빈으로 등록</li>
 *   <li>환경 변수 및 프로퍼티 파일에 정의된 값을 사용하여 애플리케이션 구성</li>
 * </ul>
 *
 * <p>사용 예:
 * <pre>
 *   &#64;Autowired
 *   private Environment env;
 *
 *   public void someMethod() {
 *     String someValue = env.getProperty("some.key");
 *   }
 * </pre>
 *
 * @author 동박
 */
@MyAutoConfiguration
public class PropertyPlaceholderConfig {

    @Bean
    PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
