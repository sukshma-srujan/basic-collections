package collections.basic.hashmap;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntHashMap;
import org.junit.jupiter.api.Test;

class IntHashMapContainsKeyTest {
  @Test
  void emptyMap_containsKey_alwaysFalse() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    assertThat(intHashMap.containsKey(-1)).isFalse();
    assertThat(intHashMap.containsKey(0)).isFalse();
    assertThat(intHashMap.containsKey(1)).isFalse();
  }

  @Test
  void singletonMap_containsKeyOnContaining_isTrue() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(-858, "blue");

    assertThat(intHashMap.containsKey(-858)).isTrue();
  }

  @Test
  void singletonMap_containsKeyOnNonContaining_isFalse() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(-8478, "blue");

    assertThat(intHashMap.containsKey(-8479)).isFalse();
  }

  @Test
  void nSizeMap_containsKeyOnContaining_isTrue() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(-8478, "blue");
    intHashMap.put(3847, "red");
    intHashMap.put(262, "black");

    assertThat(intHashMap.containsKey(3847)).isTrue();
  }

  @Test
  void nSizeMap_containsKeyOnNonContaining_isFalse() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(-1, "blue");
    intHashMap.put(0, "red");
    intHashMap.put(1, "black");

    assertThat(intHashMap.containsKey(2)).isFalse();
  }
}
