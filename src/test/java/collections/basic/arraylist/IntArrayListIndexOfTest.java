package collections.basic.arraylist;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntArrayList;
import org.junit.jupiter.api.Test;

class IntArrayListIndexOfTest {
  @Test
  void emptyList_indexOf_MinusOne() {
    IntArrayList list = new IntArrayList();
    assertThat(list.indexOf(12)).isEqualTo(-1);
  }

  @Test
  void singleton_indexOf_familiar_zero() {
    IntArrayList list = new IntArrayList();
    list.add(10);

    assertThat(list.indexOf(10)).isEqualTo(0);
  }

  @Test
  void singleton_indexOf_unfamiliar_minusOne() {
    IntArrayList list = new IntArrayList();
    list.add(8);

    assertThat(list.indexOf(10)).isEqualTo(-1);
  }

  @Test
  void nSize_indexOf_familiar_index() {
    IntArrayList list = new IntArrayList();
    list.add(0);
    list.add(-1);
    list.add(-12);
    list.add(-3);

    assertThat(list.indexOf(-1)).isEqualTo(1);
    assertThat(list.indexOf(-12)).isEqualTo(2);
  }

  @Test
  void nSize_indexOf_manyFamiliar_firstIndex() {
    IntArrayList list = new IntArrayList();
    list.add(0);
    list.add(31);
    list.add(73);
    list.add(31);
    list.add(378);

    assertThat(list.indexOf(31)).isEqualTo(1);
  }

  @Test
  void nSize_indexOf_unfamiliar_minusOne() {
    IntArrayList list = new IntArrayList();
    list.add(10);
    list.add(12);
    list.add(12);
    list.add(13);

    assertThat(list.indexOf(-2)).isEqualTo(-1);
    assertThat(list.indexOf(11)).isEqualTo(-1);
  }
}
