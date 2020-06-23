package ir.snapp.interview.etl;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface ContactSink {
	
  String CONTACTS_TOPIC_NAME = "contacts";
  
  @Input(ContactSink.CONTACTS_TOPIC_NAME)
  KStream<?, ?> contact();



}
