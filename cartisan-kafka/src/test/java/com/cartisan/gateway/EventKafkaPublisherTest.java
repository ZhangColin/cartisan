package com.cartisan.gateway;

import com.cartisan.event.ApplicationEvent;
import com.google.gson.Gson;
import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author colin
 */
public class EventKafkaPublisherTest {
    private final MockProducer<String, String> mockProducer =
            new MockProducer<>(true, new StringSerializer(), new StringSerializer());
    private final Gson gson = new Gson();

    private class CandidateNominated extends ApplicationEvent {
        private final String notification;
        private final String candidateId;
        private final String nominatorId;

        public CandidateNominated(String notification, String candidateId, String nominatorId) {
            this.notification = notification;
            this.candidateId = candidateId;
            this.nominatorId = nominatorId;
        }
    }

    private class EventKafkaMockPublisher extends EventKafkaPublisher<CandidateNominated> {
        public EventKafkaMockPublisher(Destination destination) {
            super(destination);
        }

        @Override
        protected Producer<String, String> createProducer() {
            return mockProducer;
        }
    }

    @Test
    public void should_send_CandidateNominated_application_events() {
        final Destination destination = new Destination("localhost", 9012, "test");

        final EventKafkaMockPublisher mockPublisher = new EventKafkaMockPublisher(destination);

        final CandidateNominated candidateNominated1 = new CandidateNominated("nominate ticket", "201912101000", "201001010110");
        final CandidateNominated candidateNominated2 = new CandidateNominated("nominate ticket again", "201912101000", "201001010110");

        mockPublisher.publish(candidateNominated1);
        mockPublisher.publish(candidateNominated2);

        final List<ProducerRecord<String, String>> actualHistory = mockProducer.history();
        final List<ProducerRecord<Object, String>> expectedHistory = asList(
                new ProducerRecord<>("test", gson.toJson(candidateNominated1)),
                new ProducerRecord<>("test", gson.toJson(candidateNominated2))
        );

        assertThat(actualHistory).isEqualTo(expectedHistory);
    }
}
