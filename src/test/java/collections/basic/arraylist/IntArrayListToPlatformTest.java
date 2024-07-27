package collections.basic.arraylist;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntArrayList;
import org.junit.jupiter.api.Test;

class IntArrayListToPlatformTest {
  @Test
  void emptyList_toPlatform_succeeds() {
    IntArrayList list = new IntArrayList();

    assertThat(list.toPlatform()).isEmpty();
  }

  @Test
  void singleton_toPlatform_succeeds() {
    IntArrayList list = new IntArrayList();
    list.add(0);

    assertThat(list.toPlatform()).containsExactly(0);
  }

  @Test
  void nSizeList_toPlatform_succeeds() {
    IntArrayList list = new IntArrayList();
    list.add(0);
    list.add(3);
    list.add(-1);
    list.add(-36);
    list.add(746);

    assertThat(list.toPlatform()).containsExactly(0, 3, -1, -36, 746);
  }
}
