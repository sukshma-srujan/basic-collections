package collections.basic;

import static collections.basic.TestHelperIntArrayList.assertSizeAndElements;

import org.junit.jupiter.api.Test;

class IntArrayListAddTest {
  @Test
  void newList_add_succeeds() {
    IntArrayList list = new IntArrayList();
    list.add(73838);
  }

  @Test
  void list_add_correctOrder() {
    IntArrayList list = new IntArrayList();
    final int n = 19;
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      int e = 10373 - i;
      arr[i] = e;
      list.add(e);
    }

    assertSizeAndElements(arr, list);
  }

  @Test
  void list_add_listGrows() {
    IntArrayList list = new IntArrayList();
    int[] elements = new int[153];
    for (int i = 0; i < 153; i++) {
      elements[i] = i;
      list.add(i);
    }

    assertSizeAndElements(elements, list);
  }
}
