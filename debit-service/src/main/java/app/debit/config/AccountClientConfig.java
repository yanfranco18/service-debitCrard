package app.debit.config;

import app.debit.config.properties.AppConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountClientConfig {
    @Bean
    @ConfigurationProperties(prefix = AppConfig.DEFAULT_ACCOUNT_PROPERTIES)
    public AppConfig appConfigAccountProperties() {
        return new AppConfig();
    }
}
