package dongpark.config.autoconfig.data;

import com.zaxxer.hikari.HikariDataSource;
import dongpark.config.ConditionalMyOnClass;
import dongpark.config.EnableMyConfigurationProperties;
import dongpark.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDataSourceProperties.class)
public class DataSourceConfig {

    @Bean
    @ConditionalMyOnClass("com.zaxxer.hikari")
    @ConditionalOnMissingBean
    DataSource hikariDataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
        // SimpleDriverDataSource는 DataSource의 구현체 중 하나이다. pool을 사용하지 않는다.
        // 운영 환경에서는 pool을 사용하는 DataSource를 사용해야 한다.
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }

    @Bean
    @ConditionalOnMissingBean
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
