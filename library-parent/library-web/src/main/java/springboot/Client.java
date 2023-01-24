package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;

@ServletComponentScan
@SpringBootApplication
public class Client {
    public static void main(String[] args){
        ApplicationContext ctx = SpringApplication.run(Client.class, args);
    }
}
