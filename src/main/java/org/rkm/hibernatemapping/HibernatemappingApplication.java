package org.rkm.hibernatemapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HibernatemappingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernatemappingApplication.class, args);
    }

}
