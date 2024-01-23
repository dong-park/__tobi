package dongpark.helloboot;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class HellobootApplication {

    private final JdbcTemplate jdbcTemplate;

    public HellobootApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void init() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS HELLO (NAME VARCHAR(255), COUNT INT)");
    }

    public static void main(String[] args) {

        SpringApplication.run(HellobootApplication.class, args); // 결국엔 원형으로 돌아갔다..
    }

}
/**
 * standalone으로 실행하여 자동구성 빈에 대해서 살펴보자
 */
//@SpringBootApplication
//public class SprintBootAutoApplication {
//
//    @Bean
//    ApplicationRunner applicationRunner(ConditionEvaluationReport report) {
//        return args -> {
//            System.out.println("ConditionEvaluationReport");
//            long jmxCount = report.getConditionAndOutcomesBySource().entrySet().stream()
//                    .filter(co -> co.getValue().isFullMatch())
//                    .filter(co -> co.getKey().indexOf("Jmx") < 0)
//                    .map(co -> {
//                        System.out.println(co.getKey());
//                        co.getValue().forEach(c ->
//                                System.out.println("\t" + c.getOutcome()));
//                        return co;
//                    }).count();
//
//            System.out.println("Jmx Count : " + jmxCount);
//        };
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(SprintBootAutoApplication.class, args);
//    }
//
//}

