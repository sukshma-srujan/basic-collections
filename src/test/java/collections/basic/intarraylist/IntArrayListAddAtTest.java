package collections.basic.intarraylist;

import static collections.basic.intarraylist.TestHelperIntArrayList.assertSizeAndElements;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import collections.basic.IntArrayList;
import org.junit.jupiter.api.Test;

class IntArrayListAddAtTest {
  /*
   * - empty list
   *   - at zero
   *   - at one
   *   - at negative
   * - singleton list
   *   - at zero
   *   - at one
   *   - at two
   *   - at negative
   * - nSizeList
   *   - at zero
   *   - at one
   *   - at middle
   *   - at n
   *   - at n + 1
   *   - at negative
   */

  @Test
  void emptyList_addAtZero_fails() {
    IntArrayList list = new IntArrayList();

    assertThatThrownBy(() -> list.addAt(0, 1))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: 0");
  }

  @Test
  void emptyList_addAtOne_fails() {
    IntArrayList list = new IntArrayList();

    assertThatThrownBy(() -> list.addAt(1, 146))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: 1");
  }

  @Test
  void emptyList_addAtNegative_fails() {
    IntArrayList list = new IntArrayList();

    assertThatThrownBy(() -> list.addAt(-1, 1))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: -1");
  }

  @Test
  void singleton_addAtZero_succeeds() {
    IntArrayList list = new IntArrayList();
    list.add(37);

    list.addAt(0, 2);

    assertSizeAndElements(new int[] {2, 37}, list);
  }

  @Test
  void singleton_addAtOne_fails() {
    IntArrayList list = new IntArrayList();
    list.add(37);

    assertThatThrownBy(() -> list.addAt(1, 174))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: 1");
  }

  @Test
  void singleton_addAtTwo_fails() {
    IntArrayList list = new IntArrayList();
    list.add(0);

    assertThatThrownBy(() -> list.addAt(2, 174))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: 2");
  }

  @Test
  void singleton_addAtNegative_fails() {
    IntArrayList list = new IntArrayList();
    list.add(-1);

    assertThatThrownBy(() -> list.addAt(-1, 84))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: -1");
  }

  @Test
  void nSizeList_addAtZero_succeeds() {
    IntArrayList list = new IntArrayList();
    list.add(84);
    list.add(937);
    list.add(394374);
    list.add(8374);

    list.addAt(0, 484);

    int[] elements = {484, 84, 937, 394374, 8374};

    assertSizeAndElements(elements, list);
  }

  @Test
  void nSizeList_addAtOne_succeeds() {
    IntArrayList list = new IntArrayList();
    list.add(0);
    list.add(11);

    list.addAt(1, 3);

    assertSizeAndElements(new int[] {0, 3, 11}, list);
  }

  @Test
  void nSizeList_addAtMiddle_succeeds() {
    IntArrayList list = new IntArrayList();
    list.add(84);
    list.add(173);
    list.add(26);
    list.add(74);

    list.addAt(1, 214);
    list.addAt(3, 93);

    assertSizeAndElements(new int[] {84, 214, 173, 93, 26, 74}, list);
  }

  @Test
  void nSizeList_addAtEnd_succeeds() {
    IntArrayList list = new IntArrayList();
    list.add(10);
    list.add(20);
    list.add(283);
    list.add(-1);
    list.add(-64);

    list.addAt(4, -8367);

    assertSizeAndElements(new int[] {10, 20, 283, -1, -8367, -64}, list);
  }

  @Test
  void nSizeList_addAtEndPlusOne_fails() {
    IntArrayList list = new IntArrayList();
    list.add(10);
    list.add(20);
    list.add(47);

    assertThatThrownBy(() -> list.addAt(3, 1))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: 3");
  }

  @Test
  void nSizeList_addAtFarther_fails() {
    IntArrayList list = new IntArrayList();
    list.add(10);
    list.add(20);

    assertThatThrownBy(() -> list.addAt(4, 1))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: 4");
  }

  @Test
  void nSizeList_addAtNegative_fails() {
    IntArrayList list = new IntArrayList();
    list.add(0);
    list.add(10);
    list.add(20);
    list.add(836);
    list.add(387);

    assertThatThrownBy(() -> list.addAt(-2, 1))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: -2");
  }
}
