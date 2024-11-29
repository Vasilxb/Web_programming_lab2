package mk.ukim.finki.wp.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class WebProgrammingLab1Application {

    public static void main(String[] args) {
        SpringApplication.run(WebProgrammingLab1Application.class, args);
    }

}
