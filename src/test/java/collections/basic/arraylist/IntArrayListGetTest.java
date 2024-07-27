package collections.basic.arraylist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import collections.basic.IntArrayList;
import org.junit.jupiter.api.Test;

class IntArrayListGetTest {
  /*
   * - empty list
   *   - get at zero
   *   - get at negative
   *   - get at one
   * - singleton list
   *   - get at zero
   *   - get at negative
   *   - get at farther than size
   * - nSize list
   *   - get at zero
   *   - get at middle
   *   - get at last
   *   - get at negative
   *   - get at farther than size
   */

  @Test
  void emptyList_getAtZero_fails() {
    IntArrayList list = new IntArrayList();
    assertThatThrownBy(() -> list.get(0))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: 0");
  }

  @Test
  void emptyList_getAtOne_fails() {
    IntArrayList list = new IntArrayList();
    assertThatThrownBy(() -> list.get(1))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: 1");
  }

  @Test
  void emptyList_getAtNegative_fails() {
    IntArrayList list = new IntArrayList();
    assertThatThrownBy(() -> list.get(-1))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: -1");
  }

  @Test
  void singletonList_getAtZero_succeeds() {
    IntArrayList list = new IntArrayList();
    list.add(1474);
    assertThat(list.get(0)).isEqualTo(1474);
  }

  @Test
  void singletonList_getAtOne_fails() {
    IntArrayList list = new IntArrayList();
    list.add(0);
    assertThatThrownBy(() -> list.get(1))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: 1");
  }

  @Test
  void singletonList_getAtFarther_fails() {
    IntArrayList list = new IntArrayList();
    list.add(1);
    assertThatThrownBy(() -> list.get(15))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: 15");
  }

  @Test
  void singletonList_getAtNegative_fails() {
    IntArrayList list = new IntArrayList();
    list.add(2);
    assertThatThrownBy(() -> list.get(-2))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: -2");
  }

  @Test
  void nSizeList_getAtZero_succeeds() {
    IntArrayList list = new IntArrayList();
    list.add(0);
    list.add(184);
    list.add(24);

    assertThat(list.get(0)).isEqualTo(0);
  }

  @Test
  void nSizeList_getAtMiddle_succeeds() {
    IntArrayList list = new IntArrayList();
    list.add(3475);
    list.add(78461);
    list.add(9444);
    list.add(90424);

    assertThat(list.get(1)).isEqualTo(78461);
    assertThat(list.get(2)).isEqualTo(9444);
  }

  @Test
  void nSizeList_getAtEnd_succeeds() {
    IntArrayList list = new IntArrayList();
    list.add(94);
    list.add(85);
    list.add(6);

    assertThat(list.get(2)).isEqualTo(6);
  }

  @Test
  void nSizeList_getAtFarther_fails() {
    IntArrayList list = new IntArrayList();
    list.add(8948);
    list.add(646);
    list.add(42);
    list.add(8547);

    assertThatThrownBy(() -> list.get(4))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: 4");
    assertThatThrownBy(() -> list.get(5))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: 5");
    assertThatThrownBy(() -> list.get(47))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: 47");
  }

  @Test
  void nSizeList_getAtNegative_fails() {
    IntArrayList list = new IntArrayList();
    list.add(8948);
    list.add(646);
    list.add(42);
    list.add(8547);

    assertThatThrownBy(() -> list.get(-2))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: -2");
  }
}
