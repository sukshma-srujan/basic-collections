package collections.basic.hashset;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntHashMap;
import collections.basic.IntHashSet;
import org.junit.jupiter.api.Test;

class IntHashMapKeySetTest {
  @Test
  void emptyMap_keySet_isEmpty() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    IntHashSet intHashSet = intHashMap.keySet();
    assertThat(intHashSet.isEmpty()).isTrue();
  }

  @Test
  void singletonMap_keySet_isEmpty() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(-7, "ha ha ha");

    IntHashSet intHashSet = intHashMap.keySet();

    assertThat(intHashSet.toArray()).containsExactly(-7);
  }

  @Test
  void twoElementMap_keySet_isEmpty() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(-7, "ha ha ha");
    intHashMap.put(17, "he he he");

    IntHashSet intHashSet = intHashMap.keySet();

    assertThat(intHashSet.toArray()).containsExactlyInAnyOrder(-7, 17);
  }

  @Test
  void nElementMap_keySet_isEmpty() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    int[] expectedElements = new int[62];
    int idx = 0;
    for (int i = -31; i < 31; i++) {
      intHashMap.put(i, "847hj373_" + i);
      expectedElements[idx++] = i;
    }

    IntHashSet intHashSet = intHashMap.keySet();

    assertThat(intHashSet.toArray()).containsExactlyInAnyOrder(expectedElements);
  }
}
