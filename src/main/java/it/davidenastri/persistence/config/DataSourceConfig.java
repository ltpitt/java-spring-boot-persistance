package it.davidenastri.persistence.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix="it.davidenastri.datasource")
    public DataSource getDatasource(DataSourceProperties properties) {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        // Another way to set Username and Password programmatically, currently
        // it loads it.davidenastri.datasource properties
        // dataSourceBuilder.username("plant_sa");
        // dataSourceBuilder.password(securePasswordService());
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/plantdb?serverTimezone=UTC");
        return dataSourceBuilder.build();
    }

    // Simply mocking a securePasswordService
    private String securePasswordService() {
        return "plant_sa_password";
    }

}
