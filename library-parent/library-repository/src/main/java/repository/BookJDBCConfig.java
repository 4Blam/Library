package repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("repository.jdbc")
public class BookJDBCConfig {
    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:h2:./library-parent/library-web/src/main/resources/database");
        dataSource.setUsername("ablam");
        dataSource.setPassword("delamland");

        return dataSource;
    }
}