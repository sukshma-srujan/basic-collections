package collections.basic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class IntArrayListTest {
  @Test
  void testList_canBeInstantiatedUsingDefaultConstructor() {
    assertThat(new IntArraylist()).isNotNull();
  }

  @Test
  void testList_whenCreatedFromDefaultConstructorThenHasSizeZero() {
    IntArraylist list = new IntArraylist();
    assertThat(list.size()).isZero();
  }

  @Test
  void testAdd_addingAnElementToNewlyCreatedListSucceeds() {
    IntArraylist list = new IntArraylist();
    list.add(73838);
  }

  @Test
  void testAdd_listAddsElementsInTheRightOrder() {
    IntArraylist list = new IntArraylist();
    final int n = 736;
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = 10373 + i;
      list.add(10373 + i);
    }

    assertSizeAndElements(arr, list);
  }

  @Test
  void testAdd_listGrowsAutomaticallyWhenTheElementsAreAdded() {
    IntArraylist list = new IntArraylist();
    int[] elements = new int[153];
    for (int i = 0; i < 153; i++) {
      int e = 10373 + i;
      elements[i] = e;
      list.add(e);
    }

    assertSizeAndElements(elements, list);
  }

  @Test
  void testGet_whenAccessingFirstElementInNonEmptyListThenExpectElement() {
    IntArraylist list = new IntArraylist();
    list.add(8373);
    list.add(3837);

    assertThat(list.get(0)).isEqualTo(8373);
  }

  @Test
  void testGet_whenAccessingLastElementInNonEmptyListThenExpectElement() {
    IntArraylist list = new IntArraylist();
    list.add(8373);
    list.add(3837);

    assertThat(list.get(1)).isEqualTo(3837);
  }

  @Test
  void testGet_whenAccessingElementFromTheMiddleInNonEmptyListThenExpectElement() {
    IntArraylist list = new IntArraylist();
    list.add(8373);
    list.add(3837);
    list.add(301);
    list.add(20983);

    assertThat(list.get(2)).isEqualTo(301);
  }

  @Test
  void testGet_whenAccessingElementAtIndexGreaterThanSizeThenException() {
    IntArraylist list = new IntArraylist();
    list.add(1283);
    list.add(583);
    list.add(283);

    assertThatThrownBy(() -> list.get(3))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: " + 3);
  }

  @Test
  void testGet_whenAccessingElementInEmptyListThenException() {
    IntArraylist list = new IntArraylist();

    assertThatThrownBy(() -> list.get(0))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: " + 0);
  }

  @Test
  void testAddAt_elementCanBeAddedAtTheStartOfANonEmptyList() {
    IntArraylist list = new IntArraylist();
    list.add(84);
    list.add(937);
    list.add(394374);
    list.add(8374);

    list.addAt(0, 484);

    int[] elements = {484, 84, 937, 394374, 8374};

    assertSizeAndElements(elements, list);
  }

  @Test
  void testAddAt_elementCanBeAddedInTheMiddleOfANonEmptyList() {
    IntArraylist list = new IntArraylist();
    list.add(0);
    list.add(11);

    list.addAt(1, 3);

    assertSizeAndElements(new int[] {0, 3, 11}, list);
  }

  @Test
  void testAddAt_elementCanBeAddedInTheEndOfANonEmptyList() {
    IntArraylist list = new IntArraylist();
    list.add(0);
    list.add(1);

    list.addAt(1, 2);

    assertSizeAndElements(new int[] {0, 2, 1}, list);
  }

  @Test
  void testAddAt_elementCanBeAddedAtZeroInSizeOneList() {
    IntArraylist list = new IntArraylist();
    list.add(37);

    list.addAt(0, 2);

    assertSizeAndElements(new int[] {2, 37}, list);
  }

  @Test
  void testAddAt_elementCanNotBeAddedAtNegativePositionInANonEmptyList() {
    IntArraylist list = new IntArraylist();
    list.add(10);
    list.add(20);

    assertThatThrownBy(() -> list.addAt(-1, 1))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: -1");
  }

  @Test
  void testAddAt_elementCanNotBeAddedAtPositionInAnEmptyList() {
    IntArraylist list = new IntArraylist();

    assertThatThrownBy(() -> list.addAt(0, 1))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: 0");
  }

  @Test
  void testAddAt_elementCanNotBeAddedAtNegativePositionInAnEmptyList() {
    IntArraylist list = new IntArraylist();

    assertThatThrownBy(() -> list.addAt(-1, 1))
        .isExactlyInstanceOf(IndexOutOfBoundsException.class)
        .hasMessage("Index out of range: -1");
  }

  @Test
  void testContains_whenListContainsAnElementsThenExpectTrue() {
    IntArraylist list = new IntArraylist();
    list.add(83734);
    list.add(3834);
    list.add(27374);
    list.add(39314);

    assertThat(list.contains(3834)).isTrue();
  }

  @Test
  void testContains_whenListDoesNotContainAnElementsThenExpectFalse() {
    IntArraylist list = new IntArraylist();
    list.add(252);
    list.add(152);
    list.add(30);
    list.add(187);

    assertThat(list.contains(7834)).isFalse();
  }

  @Test
  void testIndexOf_whenElementIsNonRepeatingInTheListThenReturnsFirstOccurrenceOfTheElement() {
    IntArraylist list = new IntArraylist();
    list.add(10);
    list.add(14);
    list.add(12);
    list.add(13);

    assertThat(list.indexOf(14)).isEqualTo(1);
  }

  @Test
  void testIndexOf_whenElementIsRepeatingInTheListThenReturnsFirstOccurrenceOfTheElement() {
    IntArraylist list = new IntArraylist();
    list.add(10);
    list.add(12);
    list.add(12);
    list.add(13);

    assertThat(list.indexOf(12)).isEqualTo(1);
  }

  @Test
  void testIndexOf_whenElementIsNotInTheListThenReturnsNegativeOne() {
    IntArraylist list = new IntArraylist();
    list.add(10);
    list.add(12);
    list.add(12);
    list.add(13);

    assertThat(list.indexOf(15)).isEqualTo(-1);
  }

  @Test
  void testIndexOf_whenListIsEmptyThenReturnsNegativeOne() {
    IntArraylist list = new IntArraylist();

    assertThat(list.indexOf(12)).isEqualTo(-1);
  }

  @Test
  void testLastIndexOf_whenElementIsNonRepeatingThenReturnsFirstOccurrenceOfTheElement() {
    IntArraylist list = new IntArraylist();
    list.add(847);
    list.add(284);
    list.add(2);
    list.add(182);
    list.add(384);

    assertThat(list.lastIndexOf(384)).isEqualTo(4);
  }

  @Test
  void testLastIndexOf_whenElementIsRepeatingThenReturnsLastOccurrenceOfTheElement() {
    IntArraylist list = new IntArraylist();
    list.add(847);
    list.add(284);
    list.add(182);
    list.add(182);
    list.add(384);

    assertThat(list.lastIndexOf(182)).isEqualTo(3);
  }

  @Test
  void testLastIndexOf_whenListIsEmptyThenReturnsNegativeOne() {
    IntArraylist list = new IntArraylist();

    assertThat(list.lastIndexOf(0)).isEqualTo(-1);
  }

  static void assertSizeAndElements(int[] expected, IntArraylist list) {
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
