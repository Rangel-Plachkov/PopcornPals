package bg.fmi.popcornpals;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PopcornPalsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PopcornPalsApplication.class, args);
        log.info("Application started successfully");
        System.out.println("Hello, PopcornPals!");
    }

}
