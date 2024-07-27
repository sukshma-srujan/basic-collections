package collections.basic.hashmap;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntHashMap;
import org.junit.jupiter.api.Test;

class IntHashMapPutTest {
  @Test
  void emptyMap_putOne_succeeds() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    assertThat(intHashMap.put(10, "ten")).isNull();
  }

  @Test
  void emptyMap_putTwo_succeeds() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    assertThat(intHashMap.put(10, "ten")).isNull();
    assertThat(intHashMap.put(20, "twenty")).isNull();
  }

  @Test
  void emptyMap_putMany_succeeds() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    for (int i = 0; i < 100; i++) {
      assertThat(intHashMap.put(i, "v" + i)).isNull();
    }
  }

  @Test
  void emptyMap_putTwice_returnsPrevious() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    assertThat(intHashMap.put(11, "hundred")).isNull();
    assertThat(intHashMap.put(11, "twenty")).isEqualTo("hundred");
  }

  @Test
  void singletonMap_putTwice_returnsPrevious() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(16, "ninety");
    assertThat(intHashMap.put(84, "hundred")).isNull();
    assertThat(intHashMap.put(84, "twenty")).isEqualTo("hundred");
  }

  @Test
  void nSizeMap_putTwice_returnsPrevious() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(16, "ninety");
    intHashMap.put(164, "eighty");
    intHashMap.put(383, "fifteen");
    assertThat(intHashMap.put(-34, "seventeen")).isNull();
    assertThat(intHashMap.put(-34, "forty")).isEqualTo("seventeen");
  }
}
