package pl.com.b2bnetwork.football.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateCfg {

    @Bean
    public RestTemplate create() {
        RestTemplate result = new RestTemplate();
        return result;
    }
}
