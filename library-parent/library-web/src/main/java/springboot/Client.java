package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication(scanBasePackages={"repository","service", "springboot"})
public class Client {
    public static void main(String[] args){
        ApplicationContext ctx = SpringApplication.run(Client.class, args);
    }
}
