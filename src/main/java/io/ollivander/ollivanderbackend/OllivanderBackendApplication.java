package io.ollivander.ollivanderbackend;

import io.ollivander.ollivanderbackend.config.MySqlConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@Import({ MySqlConfig.class })
@SpringBootApplication
public class OllivanderBackendApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OllivanderBackendApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(OllivanderBackendApplication.class, args);
    }

}
