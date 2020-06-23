package ir.snapp.interview.etl;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface ContactSink {
	
  String CONTACTS_BINDING = "contactsBinding";
  
  @Input(ContactSink.CONTACTS_BINDING)
  KStream<?, ?> contact();



}
