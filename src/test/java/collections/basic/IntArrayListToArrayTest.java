package collections.basic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class IntArrayListToArrayTest {
  @Test
  void emptyList_toArray_succeeds() {
    IntArrayList list = new IntArrayList();
    assertThat(list.toArray()).isEmpty();
  }

  @Test
  void singleton_toArray_succeeds() {
    IntArrayList list = new IntArrayList();
    list.add(283);

    assertThat(list.toArray()).isEqualTo(new int[] {283});
  }

  @Test
  void nSizeList_toArray_succeeds() {
    IntArrayList list = new IntArrayList();
    list.add(283);
    list.add(8373);
    list.add(28374);
    list.add(696);

    assertThat(list.toArray()).isEqualTo(new int[] {283, 8373, 28374, 696});
  }
}
