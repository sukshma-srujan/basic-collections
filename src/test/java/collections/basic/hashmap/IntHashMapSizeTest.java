package collections.basic.hashmap;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntHashMap;
import org.junit.jupiter.api.Test;

class IntHashMapSizeTest {
  @Test
  void emptyMap_size_isZero() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    assertThat(intHashMap.size()).isZero();
  }

  @Test
  void singletonMap_size_isOne() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(784, "837309588");
    assertThat(intHashMap.size()).isOne();
  }

  @Test
  void nSizeMap_size_isN() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(784, "837309588");
    intHashMap.put(-1, "hunger");
    intHashMap.put(0, "satisfied");
    intHashMap.put(1, "unsatisfied");
    assertThat(intHashMap.size()).isEqualTo(4);
  }

  @Test
  void nSizeMap_sizeOnRepeatedKeyInsertion_isN() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(784, "837309588");
    intHashMap.put(-1, "hunger");
    intHashMap.put(0, "satisfied");
    intHashMap.put(1, "unsatisfied");
    intHashMap.put(-1, "hunger");
    intHashMap.put(0, "distressed");
    intHashMap.put(1, "disappointed");
    intHashMap.put(1, "pointless");
    assertThat(intHashMap.size()).isEqualTo(4);
  }
}
