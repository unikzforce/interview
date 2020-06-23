package ir.snapp.interview.etl;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.fasterxml.jackson.databind.JsonNode;

@EnableBinding(ContactSink.class)
public class ContactEventsProcessor {
	
	@StreamListener
	public void processContactEvents(@Input(ContactSink.CONTACTS_TOPIC_NAME) final KStream<JsonNode, JsonNode> stream) {
		stream.foreach((key, value) -> {
			
		});
	}

}
