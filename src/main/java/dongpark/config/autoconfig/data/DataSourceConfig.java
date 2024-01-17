package dongpark.config.autoconfig.data;

import dongpark.config.ConditionalMyOnClass;
import dongpark.config.EnableMyConfigurationProperties;
import dongpark.config.MyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@MyAutoConfiguration
// @ConditionalOnClass은 해당 클래스가 존재할 때만 빈을 등록한다.
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties({MyDataSourceProperties.class})
public class DataSourceConfig {
    @Bean
    DataSource dataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
        // SimpleDriverDataSource는 DataSource의 구현체 중 하나이다. pool을 사용하지 않는다.
        // 운영 환경에서는 pool을 사용하는 DataSource를 사용해야 한다.
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }


}
