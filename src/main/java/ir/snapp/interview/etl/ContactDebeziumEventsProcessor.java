package ir.snapp.interview.etl;

import java.io.IOException;
import java.io.UncheckedIOException;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ir.snapp.interview.model.Contact;
import ir.snapp.interview.service.ContactService;

@EnableBinding(ContactSink.class)
public class ContactDebeziumEventsProcessor {

    private final ObjectMapper objectMapper;

    private final ContactService contactService;

    public ContactDebeziumEventsProcessor(
            ObjectMapper objectMapper,
            ContactService contactService
    ) {
        this.objectMapper = objectMapper;
        this.contactService = contactService;
    }

    @StreamListener
    public void processContactEvents(@Input(ContactSink.CONTACTS_BINDING) final KStream<JsonNode, JsonNode> stream) {
        stream.foreach((key, value) -> {
            // Each delete events will be followed by a Tombstone message with null value, For the sake of kafka log compaction.
            // We need to ignore it, because we've done whatever that is needed on the arrival of delete message, right before
            // this tombstone message.
            if (key != null && value == null) {
                return;
            }

            if (key != null) {
                final String op = value.get("payload").get("op").asText();

                // We handle just the cases for snapshot (r) or create (c) events
                if ("r".equalsIgnoreCase(op) || "c".equalsIgnoreCase(op)) {
                        Contact after = objectMapper.convertValue(value.get("payload").get("after"), Contact.class);

					try {
						contactService.enrichContactWithGitReposIdempotently(after);
					} catch (IOException e) {
						e.printStackTrace();
						throw new RuntimeException("Parameter error");
					}
				}

            }

        });
    }

}
