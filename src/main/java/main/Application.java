package main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("dbmanagement")
@ComponentScan({"view", "kafkamanager", "kafka_random_producer", "domain", "dbmanagement","main","statisticsCalculator"})
public class Application {

    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);

    }
}