package collections.basic.inthashset;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntHashSet;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class IntHashSetToPlatformTest {
  @Test
  void emptySet_toArray_emptyArray() {
    IntHashSet intHashSet = new IntHashSet();
    HashSet<Integer> platformSet = intHashSet.toPlatform();
    assertThat(platformSet).isEmpty();
  }

  @Test
  void singletonSet_toArray_arrayOfElement() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(-91);
    HashSet<Integer> platformSet = intHashSet.toPlatform();
    assertThat(platformSet).containsExactly(-91);
  }

  @Test
  void twoElementSet_toArray_arrayOfElements() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(-91);
    intHashSet.add(91);
    HashSet<Integer> platformSet = intHashSet.toPlatform();
    assertThat(platformSet).containsExactlyInAnyOrder(-91, 91);
  }

  @Test
  void nElementSet_toArray_arrayOfElements() {
    IntHashSet intHashSet = new IntHashSet();
    Integer[] expectedElements = new Integer[125];
    int idx = 0;
    for (int i = -21; i < 104; i++) {
      intHashSet.add(i);
      expectedElements[idx++] = i;
    }
    HashSet<Integer> platformSet = intHashSet.toPlatform();
    assertThat(platformSet).containsExactlyInAnyOrder(expectedElements);
  }
}
