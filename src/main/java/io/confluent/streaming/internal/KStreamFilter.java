package io.confluent.streaming.internal;

<<<<<<< HEAD
<<<<<<< HEAD
import io.confluent.streaming.KStreamTopology;
=======
import io.confluent.streaming.KStreamInitializer;
>>>>>>> new api model
=======
import io.confluent.streaming.KStreamTopology;
>>>>>>> wip
import io.confluent.streaming.Predicate;

/**
 * Created by yasuhiro on 6/17/15.
 */
class KStreamFilter<K, V> extends KStreamImpl<K, V> {

  private final Predicate<K, V> predicate;

<<<<<<< HEAD
<<<<<<< HEAD
  KStreamFilter(Predicate<K, V> predicate, KStreamTopology topology) {
    super(topology);
=======
  KStreamFilter(Predicate<K, V> predicate, KStreamInitializer initializer) {
=======
  KStreamFilter(Predicate<K, V> predicate, KStreamTopology initializer) {
>>>>>>> wip
    super(initializer);
>>>>>>> new api model
    this.predicate = predicate;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void receive(Object key, Object value, long timestamp) {
    synchronized(this) {
      if (predicate.apply((K)key, (V)value)) {
        forward(key, value, timestamp);
      }
    }
  }

}
