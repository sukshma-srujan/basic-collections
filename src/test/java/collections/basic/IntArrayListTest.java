package collections.basic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class IntArrayListTest {
  @Test
  void instantiation_succeeds() {
    assertThat(new IntArrayList()).isNotNull();
  }

  @Test
  void newList_zeroSize() {
    IntArrayList list = new IntArrayList();
    assertThat(list.size()).isZero();
  }
}
