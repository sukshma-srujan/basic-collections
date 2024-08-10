package collections.basic.intarraylist;

import static collections.basic.IteratorTestUtils.toArray;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import collections.basic.IntArrayList;
import collections.basic.IntIterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class IntArrayListIteratorTest {
  @Test
  void emptyList_iterator_hasNothing() {
    IntArrayList intArrayList = new IntArrayList();
    IntIterator intIterator = intArrayList.iterator();

    assertThat(intIterator.hasNext()).isFalse();
    assertThatThrownBy(intIterator::next).isInstanceOf(NoSuchElementException.class);
  }

  @Test
  void singleton_iterator_succeeds() {
    IntArrayList intArrayList = new IntArrayList();
    intArrayList.add(-140);

    IntIterator intIterator = intArrayList.iterator();
    int[] iteratorContents = toArray(intIterator);

    assertThat(iteratorContents).containsExactly(-140);
    assertThat(intIterator.hasNext()).isFalse();
    assertThatThrownBy(intIterator::next).isInstanceOf(NoSuchElementException.class);
  }

  @Test
  void nSize_iterator_succeeds() {
    IntArrayList intArrayList = new IntArrayList();
    int[] expectedElements = new int[120];
    int idx = 0;
    for (int i = -60; i < 60; i++) {
      intArrayList.add(i);
      expectedElements[idx++] = i;
    }

    IntIterator intIterator = intArrayList.iterator();
    int[] iteratorContents = toArray(intIterator);

    assertThat(iteratorContents).containsExactlyInAnyOrder(expectedElements);
    assertThat(intIterator.hasNext()).isFalse();
    assertThatThrownBy(intIterator::next).isInstanceOf(NoSuchElementException.class);
  }
}
