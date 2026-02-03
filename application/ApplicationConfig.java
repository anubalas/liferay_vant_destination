import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Application configuration class for defining beans in the IoT Monitoring Microservice.
 */
@Configuration
public class ApplicationConfig {

    /**
     * Bean for RestTemplate to be used for making REST API calls.
     * @return RestTemplate instance.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}