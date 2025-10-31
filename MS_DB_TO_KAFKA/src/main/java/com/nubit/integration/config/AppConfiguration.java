package com.nubit.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@EnableIntegration
public class AppConfiguration {
    public AppConfiguration() {
    }

    @Bean(name = "eventDBDataSource")
    public DataSource getEventDBDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("classpath:cdcLogTable.sql", "classpath:txTable.sql", "classpath:data.sql")
                .build();
    }

}