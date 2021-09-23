package io.ollivander.ollivanderbackend.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan("db.migration")
public class DatabaseMigrationConfig {

    public static final String FLYWAY = "flyway";

    @Autowired
    private DataSource dataSource;

    @Value("${flyway.enabled}")
    private boolean enabled;

    @Bean(name = FLYWAY, initMethod = "migrate")
    public Flyway flyway() throws Exception {
        if (!enabled) {
            return null;
        }
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("classpath:db/migration");
        flyway.setDataSource(dataSource);
        flyway.repair();
        flyway.setValidateOnMigrate(false);
        flyway.setPlaceholderReplacement(false);
        return flyway;
    }
}
