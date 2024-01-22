package dongpark.helloboot;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
// @ExtendWith는 JUnit5에서 제공하는 어노테이션으로, 테스트 확장팩을 등록할 때 사용한다.
@ExtendWith(SpringExtension.class)
// @ContextConfiguration은 테스트에 사용할 ApplicationContext를 설정한다.
@ContextConfiguration(classes = HellobootApplication.class)
@TestPropertySource("classpath:/application.properties")
@Transactional
public @interface HelloBootTest {
}
