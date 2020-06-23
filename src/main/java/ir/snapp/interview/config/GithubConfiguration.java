package ir.snapp.interview.config;

import java.io.IOException;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubConfiguration {

	@Bean
	public GitHub githubClient() throws IOException {
//		GitHubBuilder.fromEnvironment().withConnector(new HttpConn)
		// TODO 
		return GitHub.connect();
	}
	
}
