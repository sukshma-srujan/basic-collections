package collections.basic;

import java.util.ArrayList;

public class IteratorTestUtils {
  public static int[] toArray(IntIterator intIterator) {
    ArrayList<Integer> arrayList = new ArrayList<>();
    while (intIterator.hasNext()) {
      arrayList.add(intIterator.next());
    }

    int[] arr = new int[arrayList.size()];
    int idx = 0;
    for (Integer e : arrayList) {
      arr[idx++] = e;
    }
    return arr;
  }
}
