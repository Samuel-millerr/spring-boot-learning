package com.springbootlearning.quickstart.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer;
import org.springframework.boot.sql.init.DatabaseInitializationMode;
import org.springframework.boot.sql.init.DatabaseInitializationSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class JdbcSchemaConfig {

    @Bean
    public DataSourceScriptDatabaseInitializer jdbcDatabaseInitializer(
            @Qualifier("jdbcDataSource") final DataSource dataSource
    ) {
        var settings = new DatabaseInitializationSettings();
        settings.setSchemaLocations(
                List.of("classpath:/schema-jdbc.sql")
        );
        settings.setMode(DatabaseInitializationMode.ALWAYS);

        return new DataSourceScriptDatabaseInitializer(dataSource, settings);
    }
}
