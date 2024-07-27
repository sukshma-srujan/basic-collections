package collections.basic.arraylist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import collections.basic.IntArrayList;

class TestHelperIntArrayList {
  static void assertSizeAndElements(int[] expected, IntArrayList list) {
    assertThat(list.size())
        .withFailMessage("Expected size of the list <%d> but found <%d>")
        .isEqualTo(expected.length);

    for (int i = 0; i < expected.length; i++) {
      int k;
      if (expected[i] != (k = list.get(i))) {
        fail("Expected " + expected[i] + " but found " + k + " at index " + i);
      }
    }
  }
}
