package ir.snapp.interview.service;

import java.io.IOException;
import java.util.List;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Service;

@Service
public class GithubService {
	
	private final GitHub githubClient;
	
	public GithubService(GitHub githubClient) {
		// TODO Auto-generated constructor stub
		this.githubClient = githubClient;
	}
	
	public List<GHRepository> getRepositioryUrlsByUsername(String githubUsername) throws IOException {
		return this.githubClient.getUser(githubUsername).listRepositories().toList();
	}

}
