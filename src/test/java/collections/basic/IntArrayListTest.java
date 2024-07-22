package collections.basic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class IntArrayListTest {
  @Test
  void instantiation_succeeds() {
    assertThat(new IntArrayList()).isNotNull();
  }

  @Test
  void someCapacity_instantiation_succeeds() {
    assertThat(new IntArrayList(3))
        .isNotNull()
        .extracting("container")
        .isEqualTo(new int[] {0, 0, 0});
  }

  @Test
  void zeroCapacity_instantiation_succeeds() {
    assertThatThrownBy(() -> new IntArrayList(0))
        .isExactlyInstanceOf(IllegalArgumentException.class)
        .hasMessage("initialCapacity must be greater than 0");
  }

  @Test
  void negativeCapacity_instantiation_succeeds() {
    assertThatThrownBy(() -> new IntArrayList(-1))
        .isExactlyInstanceOf(IllegalArgumentException.class)
        .hasMessage("initialCapacity must be greater than 0");
  }

  @Test
  void newList_zeroSize() {
    IntArrayList list = new IntArrayList();
    assertThat(list.size()).isZero();
  }
}
