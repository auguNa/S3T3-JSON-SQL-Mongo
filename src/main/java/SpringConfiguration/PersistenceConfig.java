package SpringConfiguration;

import PersistenceInterface.FloristRepository;
import PersistenceInterface.JsonFloristRepository;
import PersistenceInterface.MongoFloristRepository;
import PersistenceInterface.MysqlFloristRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class PersistenceConfig {

    @Bean
    @Profile("json")
    public FloristRepository jsonFloristRepository() {
        return new JsonFloristRepository();
    }

    @Bean
    @Profile("mongodb")
    public FloristRepository mongoFloristRepository() {
        return new MongoFloristRepository();
    }

    @Bean
    @Profile("mysql")
    public FloristRepository mysqlFloristRepository() {
        return new MysqlFloristRepository();
    }
}