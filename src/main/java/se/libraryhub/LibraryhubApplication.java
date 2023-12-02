package se.libraryhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LibraryhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryhubApplication.class, args);
	}

}
