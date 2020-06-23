package ir.snapp.interview.config;

import java.io.IOException;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubConfiguration {

	@Value("${GITHUB_PERSONAL_ACCESS_TOKEN}")
	private String personalAccessToken;

	@Bean
	public GitHub githubClient() throws IOException {
		return new GitHubBuilder().withOAuthToken(personalAccessToken).build();
	}
	
}
