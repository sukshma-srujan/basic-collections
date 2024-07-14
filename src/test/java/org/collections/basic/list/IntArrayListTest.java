package org.collections.basic.list;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.collections.basic.IntArrayList;
import org.junit.jupiter.api.Test;

class IntArrayListTest {

  @Test
  void listCanBeInstantiatedUsingDefaultConstructor() {
    assertDoesNotThrow(() -> new IntArrayList());
  }

  @Test
  void listCanBeInstantiatedUsingConstructorWithInitialCapacity() {
    assertDoesNotThrow(() -> new IntArrayList(20));
  }

  @Test
  void newlyCreatedListWithDefaultConstructorHasSizeZero() {
    assertEquals(0, new IntArrayList().size());
  }

  @Test
  void newlyCreatedListWithInitialCapacityConstructorHasSizeZero() {
    assertEquals(0, new IntArrayList(15).size());
  }

  @Test
  void elementCanBeAddedToList() {
    IntArrayList list = new IntArrayList();
    assertDoesNotThrow(() -> list.add(171));
  }

  @Test
  void elementsAddedToListCanBeRetrievedInTheSameOrder() {
    IntArrayList list = new IntArrayList();
    list.add(11);
    list.add(2);
    list.add(-1);

    assertEquals(-1, list.get(2));
    assertEquals(2, list.get(1));
    assertEquals(11, list.get(0));
  }

  @Test
  void setChangesElementAtPosition() {
    IntArrayList list = new IntArrayList();
    list.add(10);
    list.add(-100001);
    list.add(0);

    int oldVal = list.set(2, 5);
    assertEquals(0, oldVal);
    assertEquals(5, list.get(2));
  }

  @Test
  void setThrowsExceptionIfIndexIsNegative() {
    IntArrayList list = new IntArrayList();
    IndexOutOfBoundsException ex =
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 51));
    assertEquals("Index -1 is out of bounds.", ex.getMessage());
  }

  @Test
  void setThrowsExceptionIfIndexIsEqualToSize() {
    IntArrayList list = new IntArrayList();
    list.add(71);
    IndexOutOfBoundsException ex =
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, 51));
    assertEquals("Index 1 is out of bounds.", ex.getMessage());
  }

  @Test
  void setThrowsExceptionIfIndexIsGreaterThanSize() {
    IntArrayList list = new IntArrayList();
    list.add(71);
    IndexOutOfBoundsException ex =
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(2, 51));
    assertEquals("Index 2 is out of bounds.", ex.getMessage());
  }

  @Test
  void addThrowsExceptionIfIndexIsNegative_whenListIsEmpty() {
    IntArrayList list = new IntArrayList();

    IndexOutOfBoundsException ex =
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 51));
    assertEquals("Index -1 is out of bounds.", ex.getMessage());
  }

  @Test
  void addThrowsExceptionIfIndexIsNegative_whenListIsNonEmpty() {
    IntArrayList list = new IntArrayList();
    list.add(0);

    IndexOutOfBoundsException ex =
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 1));
    assertEquals("Index -1 is out of bounds.", ex.getMessage());
  }

  @Test
  void addThrowsExceptionIfIndexIsGreaterThanSize_whenListIsEmpty() {
    IntArrayList list = new IntArrayList();
    IndexOutOfBoundsException ex =
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 1));
    assertEquals("Index 1 is out of bounds.", ex.getMessage());
  }

  @Test
  void addThrowsExceptionIfIndexIsGreaterThanSize_whenListIsNonEmpty() {
    IntArrayList list = new IntArrayList();
    list.add(3);
    list.add(0);
    IndexOutOfBoundsException ex =
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(3, 1));
    assertEquals("Index 3 is out of bounds.", ex.getMessage());
  }

  @Test
  void addAddsElementAtZeroPosition_whenListIsEmpty() {
    IntArrayList list = new IntArrayList();
    list.add(3);
    assertEquals(3, list.get(0));
  }

  @Test
  void addAddsElementToPositionEqualToListSize_whenListIsNonEmpty() {
    IntArrayList list = new IntArrayList();
    list.add(3);
    list.add(6);
    assertEquals(6, list.get(1));
  }

  @Test
  void addAtIndexAddsTheElementAtZeroPosition_whenListIsNonEmpty() {
    IntArrayList list = new IntArrayList();
    list.add(0, -1);
    assertEquals(-1, list.get(0));
  }

  @Test
  void addAtIndexAddsTheElementAtSpecifiedPosition_whenListIsNonEmpty() {
    IntArrayList list = new IntArrayList();
    list.add(10);
    list.add(4);
    list.add(1);
    list.add(5);
    list.add(2, -1);
    assertEquals(-1, list.get(2));
    assertEquals(1, list.get(3));
  }

  @Test
  void addAtIndexAddsTheElementAtTheEnd_whenListIsNonEmpty() {
    IntArrayList list = new IntArrayList();
    list.add(-5);
    list.add(-6);
    list.add(10);
    list.add(5);
    list.add(4, 9);
    assertEquals(5, list.get(3));
    assertEquals(9, list.get(4));
  }

  @Test
  void whenTwoListsContainIdenticalElementsThenHashCodeIsSame() {
    IntArrayList list1 = new IntArrayList();
    list1.add(1);
    list1.add(4);
    list1.add(-1);
    list1.add(-5);
    IntArrayList list2 = new IntArrayList();
    list2.add(1);
    list2.add(4);
    list2.add(-1);
    list2.add(-5);

    assertEquals(list1.hashCode(), list2.hashCode());
  }

  @Test
  void whenTwoListsContainDifferentElementsThenHashCodeIsNotSame() {
    IntArrayList list1 = new IntArrayList();
    list1.add(1);
    list1.add(4);
    list1.add(-1);
    list1.add(-5);
    IntArrayList list2 = new IntArrayList();
    list2.add(1);
    list2.add(4);
    list2.add(-1);
    list2.add(-5);
    list2.add(6);
    assertNotEquals(list1.hashCode(), list2.hashCode());
  }

  @Test
  void twoListsAreEqualIfTheyContainIdenticalElementsInTheSameOrder() {
    IntArrayList list1 = new IntArrayList();
    list1.add(1);
    list1.add(4);
    list1.add(-1);
    list1.add(-5);
    IntArrayList list2 = new IntArrayList();
    list2.add(1);
    list2.add(4);
    list2.add(-1);
    list2.add(-5);

    assertEquals(list1, list2);
  }

  @Test
  void twoListsAreEqualIfTheyContainIdenticalElementsInTheDifferentOrder() {
    IntArrayList list1 = new IntArrayList();
    list1.add(1);
    list1.add(4);
    list1.add(-1);
    list1.add(-5);
    IntArrayList list2 = new IntArrayList();
    list2.add(1);
    list2.add(4);
    list2.add(-5);
    list2.add(-1);

    assertNotEquals(list1, list2);
  }

  @Test
  void twoListsAreEqualIfTheyDifferentNumberOfElements() {
    IntArrayList list1 = new IntArrayList();
    list1.add(1);
    list1.add(4);
    list1.add(-1);
    list1.add(-5);
    IntArrayList list2 = new IntArrayList();
    list2.add(1);
    list2.add(4);
    list2.add(-1);

    assertNotEquals(list1, list2);
  }
}
