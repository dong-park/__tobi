package dongpark.helloboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@HelloBootTest
public class JdbcTemplateTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        // 내장형 데이터베이스를 사용할때는 테스트를 실행할때마다 초기화해주는 작업이 필요하다.
        jdbcTemplate.execute("CREATE TABLE if not exists hello (name varchar(255) primary key, count int)");
    }

    @Test
    void insert() {
        jdbcTemplate.update("INSERT INTO hello values (?, ?)", "dongpark", 1);
        jdbcTemplate.update("INSERT INTO hello values (?, ?)", "spring", 2);

        Long count = jdbcTemplate.queryForObject("SELECT count(*) FROM hello", Long.class);
        assertThat(count).isEqualTo(2);
    }

    @Test
    void insert2() {
        jdbcTemplate.update("INSERT INTO hello values (?, ?)", "dongpark", 1);
        jdbcTemplate.update("INSERT INTO hello values (?, ?)", "spring", 2);

        Long count = jdbcTemplate.queryForObject("SELECT count(*) FROM hello", Long.class);
        assertThat(count).isEqualTo(2);
    }

}
