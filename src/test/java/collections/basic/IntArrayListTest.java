package collections.basic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class IntArrayListTest {
  @Test
  void listCanBeInstantiatedUsingDefaultConstructor() {
    assertThat(new IntArraylist()).isNotNull();
  }

  @Test
  void listCreatedFromDefaultConstructorIsOfZeroSize() {
    IntArraylist list = new IntArraylist();
    assertThat(list.size()).isZero();
  }

  @Test
  void addingAnElementToNewlyCreatedListSucceeds() {
    IntArraylist list = new IntArraylist();
    list.add(73838);
  }

  @Test
  void listReturnsTheAddedElement() {
    IntArraylist list = new IntArraylist();
    list.add(8373);
    assertThat(list.get(0)).isEqualTo(8373);
  }

  @Test
  void listGrowsAutomaticallyWhenTheElementsAreAdded() {
    IntArraylist list = new IntArraylist();
    for (int i = 0; i < 153; i++) {
      list.add(10373 + i);
    }
  }

  @Test
  void listAddsElementsInTheRightOrder() {
    IntArraylist list = new IntArraylist();
    final int n = 736;
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = 10373 + i;
      list.add(10373 + i);
    }

    matchElements(arr, list);
  }

  @Test
  void whenAccessingElementAtIndexGreaterThanSizeThenException() {
    IntArraylist list = new IntArraylist();
    list.add(1283);
    list.add(583);
    list.add(283);

    assertThatThrownBy(() -> list.get(3))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: " + 3);
  }

  @Test
  void elementCanBeAddedAtTheStartOfANonEmptyList() {
    IntArraylist list = new IntArraylist();
    list.add(84);
    list.add(937);
    list.add(394374);
    list.add(8374);

    list.addAt(0, 484);

    int[] elements = {484, 84, 937, 394374, 8374};

    matchElements(elements, list);
  }

  @Test
  void elementCanBeAddedInTheMiddleOfANonEmptyList() {
    IntArraylist list = new IntArraylist();
    list.add(0);
    list.add(11);

    list.addAt(1, 3);

    matchElements(new int[] {0, 3, 11}, list);
  }

  @Test
  void elementCanBeAddedInTheEndOfANonEmptyList() {
    IntArraylist list = new IntArraylist();
    list.add(0);
    list.add(1);

    list.addAt(1, 2);

    matchElements(new int[] {0, 2, 1}, list);
  }

  @Test
  void elementCanNotBeAddedAtNegativePositionInANonEmptyList() {
    IntArraylist list = new IntArraylist();
    list.add(10);
    list.add(20);

    assertThatThrownBy(() -> list.addAt(-1, 1))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: -1");
  }

  @Test
  void elementCanNotBeAddedAtPositionInAnEmptyList() {
    IntArraylist list = new IntArraylist();

    assertThatThrownBy(() -> list.addAt(0, 1))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: 0");
  }

  @Test
  void elementCanNotBeAddedAtNegativePositionInAnEmptyList() {
    IntArraylist list = new IntArraylist();

    assertThatThrownBy(() -> list.addAt(-1, 1))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: -1");
  }

  @Test
  void ifListContainsAnElementsThenExpectTrue() {
    IntArraylist list = new IntArraylist();
    list.add(83734);
    list.add(3834);
    list.add(27374);
    list.add(39314);

    assertThat(list.contains(3834)).isTrue();
  }

  @Test
  void ifListDoesNotContainAnElementsThenExpectFalse() {
    IntArraylist list = new IntArraylist();
    list.add(252);
    list.add(152);
    list.add(30);
    list.add(187);

    assertThat(list.contains(7834)).isFalse();
  }

  static void matchElements(int[] expected, IntArraylist list) {
    assertThat(list.size())
        .withFailMessage("Expected size of the list <%d> but found <%d>")
        .isEqualTo(expected.length);

    for (int i = 0; i < expected.length; i++) {
      int k;
      if (expected[i] != (k = list.get(i))) {
        fail("Expected " + expected[i] + " but found " + k + " at index " + i);
      }
    }
  }
}
