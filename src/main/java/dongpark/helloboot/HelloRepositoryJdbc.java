package dongpark.helloboot;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepositoryJdbc implements HelloRepository {

    private final JdbcTemplate jdbcTemplate;

    public HelloRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Hello findHello(String name) {
        try {
            Hello hello = jdbcTemplate.queryForObject(
                    "SELECT * FROM hello WHERE name = '" + name + "'",
                    (rs, rowNum) -> new Hello(rs.getString("name"), rs.getInt("count"))
            );
            return hello;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void increaseCount(String name) {
        Hello hello = findHello(name);
        if (hello == null) {
            jdbcTemplate.update("insert into hello values (?, ?)", name, 1);
        } else {
            jdbcTemplate.update("update hello set count = ? where name = ?", hello.getCount() + 1, name);
        }
    }
}
