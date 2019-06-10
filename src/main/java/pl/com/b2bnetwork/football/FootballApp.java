package pl.com.b2bnetwork.football;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Hello world!
 * <p>
 * Login: admin
 * Password: admin
 * <p>
 * Login: user
 * Password: user
 */

@SpringBootApplication
@EnableAsync
public class FootballApp {
    public static void main(final String[] args) {
        SpringApplication.run(FootballApp.class, args);
    }
}