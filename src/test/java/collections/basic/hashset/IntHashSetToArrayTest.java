package collections.basic.hashset;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntHashSet;
import org.junit.jupiter.api.Test;

class IntHashSetToArrayTest {
  @Test
  void emptySet_toArray_emptyArray() {
    IntHashSet intHashSet = new IntHashSet();
    assertThat(intHashSet.toArray()).isEmpty();
  }

  @Test
  void singletonSet_toArray_arrayOfElement() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(-91);
    assertThat(intHashSet.toArray()).containsExactly(-91);
  }

  @Test
  void twoElementSet_toArray_arrayOfElements() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(-91);
    intHashSet.add(91);
    assertThat(intHashSet.toArray()).containsExactlyInAnyOrder(-91, 91);
  }

  @Test
  void nElementSet_toArray_arrayOfElements() {
    IntHashSet intHashSet = new IntHashSet();
    int[] expectedElements = new int[125];
    int idx = 0;
    for (int i = -21; i < 104; i++) {
      intHashSet.add(i);
      expectedElements[idx++] = i;
    }
    assertThat(intHashSet.toArray()).containsExactlyInAnyOrder(expectedElements);
  }
}
