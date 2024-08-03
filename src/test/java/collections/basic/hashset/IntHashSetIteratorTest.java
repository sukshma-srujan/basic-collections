package collections.basic.hashset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import collections.basic.IntArrayList;
import collections.basic.IntHashSet;
import collections.basic.IntIterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class IntHashSetIteratorTest {
  @Test
  void emptySet_iterator_hasNothing() {
    IntHashSet intHashSet = new IntHashSet();

    IntIterator intIterator = intHashSet.iterator();

    assertThat(intIterator.hasNext()).isFalse();
    assertThatThrownBy(intIterator::next).isInstanceOf(NoSuchElementException.class);
  }

  @Test
  void singletonSet_iterator_hasElement() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(1991);

    IntIterator intIterator = intHashSet.iterator();
    int[] iteratorContents = toArray(intIterator);

    assertThat(iteratorContents).containsExactly(1991);
    assertThat(intIterator.hasNext()).isFalse();
    assertThatThrownBy(intIterator::next).isInstanceOf(NoSuchElementException.class);
  }

  @Test
  void twoElementSet_iterator_hasElements() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(1991);
    intHashSet.add(-1032);

    IntIterator intIterator = intHashSet.iterator();
    int[] iteratorContents = toArray(intIterator);

    assertThat(iteratorContents).containsExactlyInAnyOrder(1991, -1032);
    assertThat(intIterator.hasNext()).isFalse();
    assertThatThrownBy(intIterator::next).isInstanceOf(NoSuchElementException.class);
  }

  @Test
  void nElementSet_iterator_hasElements() {
    IntHashSet intHashSet = new IntHashSet();
    int[] expectedElements = new int[120];
    int idx = 0;
    for (int i = -60; i < 60; i++) {
      intHashSet.add(i);
      expectedElements[idx++] = i;
    }

    IntIterator intIterator = intHashSet.iterator();
    int[] iteratorContents = toArray(intIterator);

    assertThat(iteratorContents).containsExactlyInAnyOrder(expectedElements);
    assertThat(intIterator.hasNext()).isFalse();
    assertThatThrownBy(intIterator::next).isInstanceOf(NoSuchElementException.class);
  }

  static int[] toArray(IntIterator intIterator) {
    IntArrayList intArrayList = new IntArrayList();
    while (intIterator.hasNext()) {
      intArrayList.add(intIterator.next());
    }
    return intArrayList.toArray();
  }
}
