package collections.basic.hashmap;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntHashMap;
import org.junit.jupiter.api.Test;

class IntHashMapContainsTest {
  @Test
  void emptyMap_contains_alwaysFalse() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    assertThat(intHashMap.contains(-1)).isFalse();
    assertThat(intHashMap.contains(0)).isFalse();
    assertThat(intHashMap.contains(1)).isFalse();
  }

  @Test
  void singletonMap_containsOnContaining_isTrue() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(-858, "blue");

    assertThat(intHashMap.contains(-858)).isTrue();
  }

  @Test
  void singletonMap_containsOnNonContaining_isFalse() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(-8478, "blue");

    assertThat(intHashMap.contains(-8479)).isFalse();
  }

  @Test
  void nSizeMap_containsOnContaining_isTrue() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(-8478, "blue");
    intHashMap.put(3847, "red");
    intHashMap.put(262, "black");

    assertThat(intHashMap.contains(3847)).isTrue();
  }

  @Test
  void nSizeMap_containsOnNonContaining_isFalse() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(-1, "blue");
    intHashMap.put(0, "red");
    intHashMap.put(1, "black");

    assertThat(intHashMap.contains(2)).isFalse();
  }
}
