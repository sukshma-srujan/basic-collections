package collections.basic.intarraylist;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntArrayList;
import org.junit.jupiter.api.Test;

class IntArrayListLastIndexOfTest {
  @Test
  void emptyList_lastIndexOf_minusOne() {
    IntArrayList list = new IntArrayList();
    assertThat(list.lastIndexOf(10)).isEqualTo(-1);
  }

  @Test
  void singleton_lastIndexOf_familiar_index() {
    IntArrayList list = new IntArrayList();
    list.add(-1);
    assertThat(list.lastIndexOf(-1)).isEqualTo(0);
  }

  @Test
  void singleton_lastIndexOf_unfamiliar_minusOne() {
    IntArrayList list = new IntArrayList();
    list.add(-2);
    assertThat(list.lastIndexOf(-1)).isEqualTo(-1);
  }

  @Test
  void nSize_lastIndexOf_familiar_index() {
    IntArrayList list = new IntArrayList();
    list.add(847);
    list.add(284);
    list.add(182);
    list.add(186);
    list.add(384);

    assertThat(list.lastIndexOf(182)).isEqualTo(2);
  }

  @Test
  void nSize_lastIndexOf_manyFamiliar_lastIndex() {
    IntArrayList list = new IntArrayList();
    list.add(-1);
    list.add(-2);
    list.add(-182);
    list.add(-182);
    list.add(-384);

    assertThat(list.lastIndexOf(-182)).isEqualTo(3);
  }

  @Test
  void nSize_lastIndexOf_unfamiliar_minusOne() {
    IntArrayList list = new IntArrayList();
    list.add(-847);
    list.add(-284);
    list.add(-284);
    list.add(-182);
    list.add(-182);

    assertThat(list.lastIndexOf(-285)).isEqualTo(-1);
  }
}
