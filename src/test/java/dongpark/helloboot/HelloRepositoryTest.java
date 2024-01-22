package dongpark.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HelloBootTest
public class HelloRepositoryTest {

    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired HelloRepository helloRepository;

    @BeforeEach
    void init() {
        // 내장형 데이터베이스를 사용할때 는 테스트를 실행할때마다 초기화해주는 작업이 필요하다.
        jdbcTemplate.execute(
                "CREATE TABLE if not exists hello (name varchar(255) primary key, count int)");
    }

    @Test
    void findHelloFailed() {
        Assertions.assertThat(helloRepository.findHello("dongpark")).isNull();
    }

    @Test
    void increaseCount() {
        Assertions.assertThat(helloRepository.countOf("dongpark")).isEqualTo(0);
        helloRepository.increaseCount("dongpark");
        Assertions.assertThat(helloRepository.countOf("dongpark")).isEqualTo(1);
    }

}
