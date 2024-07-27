package collections.basic.arraylist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import collections.basic.IntArrayList;
import org.junit.jupiter.api.Test;

class IntArrayListIteratorTest {
  @Test
  void emptyList_iterator_succeeds() {
    IntArrayList list = new IntArrayList();
    list.forEach((e) -> fail("unexpected execution of block"));
  }

  @Test
  void singleton_iterator_succeeds() {
    IntArrayList list = new IntArrayList();
    list.add(-140);

    int[] curIndexContainer = new int[1];
    int[] elements = new int[1];
    list.forEach((e) -> elements[curIndexContainer[0]++] = e);

    assertThat(elements).isEqualTo(new int[] {-140});
  }

  @Test
  void nSize_iterator_succeeds() {
    IntArrayList list = new IntArrayList();
    list.add(140);
    list.add(-183);
    list.add(-330);
    list.add(894);

    int[] curIndexContainer = new int[1];
    int[] elements = new int[4];
    list.forEach((e) -> elements[curIndexContainer[0]++] = e);

    assertThat(elements).isEqualTo(new int[] {140, -183, -330, 894});
  }
}
