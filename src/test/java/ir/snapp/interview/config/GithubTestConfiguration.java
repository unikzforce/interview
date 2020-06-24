package ir.snapp.interview.config;

import org.kohsuke.github.GitHub;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class GithubTestConfiguration {
    @Bean
    @Primary
    public GitHub nameService() {
        return Mockito.mock(GitHub.class);
    }
}
