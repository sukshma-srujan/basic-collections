package collections.basic.arraylist;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntArrayList;
import org.junit.jupiter.api.Test;

class IntArrayListEqualsAndHashCodeTest {
  @Test
  void sameEmptyIntList_hashCode_same() {
    IntArrayList intArrayList = new IntArrayList();
    assertThat(intArrayList.hashCode()).isEqualTo(intArrayList.hashCode());
  }

  @Test
  void sameSingletonIntList_hashCode_same() {
    IntArrayList intArrayList = new IntArrayList();
    intArrayList.add(13);
    assertThat(intArrayList.hashCode()).isEqualTo(intArrayList.hashCode());
  }

  @Test
  void sameNSizeList_hashCode_same() {
    IntArrayList intArrayList = new IntArrayList();
    intArrayList.add(14);
    intArrayList.add(74);
    intArrayList.add(673);
    assertThat(intArrayList.hashCode()).isEqualTo(intArrayList.hashCode());
  }

  @Test
  void twoEmptyIntList_equals_true() {
    IntArrayList first = new IntArrayList();
    IntArrayList second = new IntArrayList();

    assertThat(first).isEqualTo(second);
  }

  @Test
  void twoSimilarSingletonIntList_equals_true() {
    IntArrayList first = new IntArrayList();
    first.add(10);

    IntArrayList second = new IntArrayList();
    second.add(10);

    assertThat(first).isEqualTo(second);
  }

  @Test
  void twoSimilarNSizeIntList_equals_true() {
    IntArrayList first = new IntArrayList();
    first.add(10);
    first.add(48);
    first.add(-1);

    IntArrayList second = new IntArrayList();
    second.add(10);
    second.add(48);
    second.add(-1);

    assertThat(first).isEqualTo(second);
  }

  @Test
  void twoDifferentSingletonIntList_equals_true() {
    IntArrayList first = new IntArrayList();
    first.add(10);

    IntArrayList second = new IntArrayList();
    second.add(11);

    assertThat(first).isNotEqualTo(second);
  }

  @Test
  void twoDifferentNSizeIntList_equals_true() {
    IntArrayList first = new IntArrayList();
    first.add(10);
    first.add(48);
    first.add(-1);

    IntArrayList second = new IntArrayList();
    second.add(10);
    second.add(48);
    second.add(-1);
    second.add(48);

    assertThat(first).isNotEqualTo(second);
  }

  @Test
  void twoDifferentNSizeIntList2_equals_true() {
    IntArrayList first = new IntArrayList();
    first.add(10);
    first.add(48);
    first.add(-1);

    IntArrayList second = new IntArrayList();
    second.add(10);
    second.add(48);
    second.add(-2);

    assertThat(first).isNotEqualTo(second);
  }
}
