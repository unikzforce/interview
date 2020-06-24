package ir.snapp.interview;

import ir.snapp.interview.service.dto.ContactDTO;
import ir.snapp.interview.web.rest.ContactResource;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class InterviewProjectApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private ContactResource contactResource;

	@Container
	public static DockerComposeContainer environment =
			new DockerComposeContainer(new File("src/main/docker/infra.yml"))
					.withExposedService("postgres", 5432)
					.withExposedService("kafka", 9092)
					.withLocalCompose(true);

	private final static String CONTACT_1_NAME = "PASHMAK";
	private final static String CONTACT_1_GITHUB = "PASHMAKIAN";

	@Test
	public void contexLoads() throws Exception {
		assertNotNull(contactResource);
	}


	@Test
	void testSave() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String contactsUrl = "http://localhost:"+ port +"/contacts/";
		URI uri = new URI(contactsUrl);

		ContactDTO contactDTO = new ContactDTO();
		contactDTO.setName(CONTACT_1_NAME);
		contactDTO.setPhoneNumber("+9893");
		contactDTO.setGithub(CONTACT_1_GITHUB);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<ContactDTO> request = new HttpEntity<>(contactDTO, headers);

		ResponseEntity<ContactDTO> response = restTemplate.postForEntity(uri, request, ContactDTO.class);

		assertEquals(response.getBody().getName(), CONTACT_1_NAME);
		assertEquals(response.getBody().getGithub(), CONTACT_1_GITHUB);


	}

}
