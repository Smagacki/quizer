package pl.edu.wszib.jwd.quizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@PropertySource("classpath:db.properties")
public class QuizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizerApplication.class, args);
    }

    DbInit dbInit = new DbInit();
}
