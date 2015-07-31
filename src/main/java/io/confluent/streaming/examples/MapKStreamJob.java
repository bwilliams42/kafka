package io.confluent.streaming.examples;

import io.confluent.streaming.KStream;
<<<<<<< HEAD
<<<<<<< HEAD
import io.confluent.streaming.KStreamTopology;
=======
import io.confluent.streaming.KStreamInitializer;
import io.confluent.streaming.KStreamJob;
>>>>>>> new api model
=======
import io.confluent.streaming.KStreamTopology;
>>>>>>> wip
import io.confluent.streaming.KafkaStreaming;
import io.confluent.streaming.KeyValue;
import io.confluent.streaming.KeyValueMapper;
import io.confluent.streaming.Predicate;
import io.confluent.streaming.StreamingConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

/**
 * Created by guozhang on 7/14/15.
 */
public class MapKStreamJob extends KStreamTopology {

  @SuppressWarnings("unchecked")
  @Override
<<<<<<< HEAD
<<<<<<< HEAD
  public void topology() {
=======
  public void init(KStreamInitializer context) {
>>>>>>> new api model
=======
  public void topology() {
>>>>>>> wip

    // With overriden de-serializer
    KStream stream1 = from(new StringDeserializer(), new StringDeserializer(), "topic1");

    stream1.map(new KeyValueMapper<String, Integer, String, String>() {
      @Override
      public KeyValue<String, Integer> apply(String key, String value) {
        return new KeyValue<>(key, new Integer(value));
      }
    }).filter(new Predicate<String, Integer>() {
      @Override
      public boolean apply(String key, Integer value) {
        return true;
      }
    }).sendTo("topic2");

    // Without overriden de-serialzier
    KStream<String, Integer> stream2 = (KStream<String, Integer>)from("topic2");

    KStream<String, Integer>[] streams = stream2.branch(
        new Predicate<String, Integer>() {
          @Override
          public boolean apply(String key, Integer value) {
            return true;
          }
        },
        new Predicate<String, Integer>() {
          @Override
          public boolean apply(String key, Integer value) {
            return true;
          }
        }
    );

    streams[0].sendTo("topic3");
    streams[1].sendTo("topic4");
  }

  public static void main(String[] args) {
    KafkaStreaming kstream = new KafkaStreaming(new MapKStreamJob(), new StreamingConfig(new Properties()));
    kstream.run();
  }
}
