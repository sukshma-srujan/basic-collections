package collections.basic.arraylist;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntArrayList;
import org.junit.jupiter.api.Test;

class IntArrayListToStringTest {
  @Test
  void emptyList_toString_justSquareBrackets() {
    IntArrayList intArrayList = new IntArrayList();
    assertThat(intArrayList.toString()).isEqualTo("[]");
  }

  @Test
  void singletonList_toString_elementInSquareBrackets() {
    IntArrayList intArrayList = new IntArrayList();
    intArrayList.add(-1);
    assertThat(intArrayList.toString()).isEqualTo("[-1]");
  }

  @Test
  void nSizeList_toString_commaSeparatedElementsInSquareBrackets() {
    IntArrayList intArrayList = new IntArrayList();
    intArrayList.add(-1);
    intArrayList.add(1);
    intArrayList.add(0);
    assertThat(intArrayList.toString()).isEqualTo("[-1, 1, 0]");
  }
}
