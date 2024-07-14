package org.collections.basic.map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.collections.basic.IntHashMap;
import org.junit.jupiter.api.Test;

class IntHashMapTest {

  @Test
  void mapCanBeCreatedUsingDefaultConstructor() {
    assertDoesNotThrow(() -> new IntHashMap<String>());
  }

  @Test
  void mapCanBeCreatedUsingInitialCapacityConstructor() {
    assertDoesNotThrow(() -> new IntHashMap<String>(5));
  }

  @Test
  void mapCreationThrowsErrorIfInitialCapacityIsNegative() {
    IllegalArgumentException ex =
        assertThrows(IllegalArgumentException.class, () -> new IntHashMap<String>(-1));
    assertEquals("Initial capacity must be greater than 0.", ex.getMessage());
  }

  @Test
  void mapCreationThrowsErrorIfInitialCapacityIsZero() {
    IllegalArgumentException ex =
        assertThrows(IllegalArgumentException.class, () -> new IntHashMap<String>(0));
    assertEquals("Initial capacity must be greater than 0.", ex.getMessage());
  }

  @Test
  void keyValueCanBePutInTheMap() {
    IntHashMap<String> map = new IntHashMap<>();
    map.put(1, "a");
  }

  @Test
  void sizeReturnsNumberOfEntriesInTheMap() {
    IntHashMap<String> map = new IntHashMap<>();
    map.put(1, "a");
    map.put(-1, "b");
    map.put(-3, "c");
    map.put(0, "d");
    map.put(99, "e");

    assertEquals(5, map.size());
  }

  @Test
  void addingSameKeyMoreThanOnceDoesNotAffectTheSizeOfTheMap() {
    IntHashMap<String> map = new IntHashMap<>();
    map.put(1, "a");
    map.put(-1, "b");
    map.put(-3, "c");
    map.put(0, "d");
    map.put(99, "e");

    assertEquals(5, map.size());

    map.put(99, "e");
    map.put(0, "f");
    map.put(1, "k");

    assertEquals(5, map.size());
  }

  @Test
  void puttingKeyWithNewValueReturnsOldValue() {
    IntHashMap<String> map = new IntHashMap<>();
    assertNull(map.put(1, "a"));
    assertEquals("a", map.put(1, "b"));
  }

  @Test
  void containsReturnsTrueIfMapContainsKey() {
    IntHashMap<String> map = new IntHashMap<>();
    map.put(1, "a");
    map.put(-1, "b");
    map.put(-3, "c");
    map.put(0, "d");
    map.put(99, "e");

    assertTrue(map.contains(1));
    assertTrue(map.contains(-1));
    assertTrue(map.contains(-3));
    assertTrue(map.contains(0));
    assertTrue(map.contains(99));
  }

  @Test
  void containsReturnsFalseIfMapDoesNotContainKey_whenMapIsEmpty() {
    IntHashMap<String> map = new IntHashMap<>();
    assertFalse(map.contains(0));
    assertFalse(map.contains(-2));
  }

  @Test
  void containsReturnsFalseIfMapDoesNotContainKey_whenMapIsNonEmpty() {
    IntHashMap<String> map = new IntHashMap<>();
    map.put(1, "a");
    map.put(-1, "b");
    map.put(-3, "c");
    map.put(0, "d");
    map.put(99, "e");

    assertFalse(map.contains(98));
    assertFalse(map.contains(-2));
  }

  @Test
  void keyRemovalReducesMapSize() {
    IntHashMap<String> map = new IntHashMap<>();
    map.put(1, "a");
    map.put(-1, "b");
    map.put(-3, "c");
    map.put(0, "d");
    map.put(99, "e");

    assertEquals(5, map.size());

    map.remove(1);
    map.remove(99);

    assertEquals(3, map.size());
  }

  @Test
  void removingKeyFromMapReturnsOldValue() {
    IntHashMap<String> map = new IntHashMap<>();
    map.put(1, "a");
    map.put(-1, "b");

    assertEquals("a", map.remove(1));
    assertEquals("b", map.remove(-1));
  }

  @Test
  void removingKeyThatDoesNotExistInMapHasNoEffect() {
    IntHashMap<String> map = new IntHashMap<>();
    map.put(1, "a");
    map.put(-1, "b");
    map.put(-3, "c");
    map.put(0, "d");
    map.put(99, "e");

    assertNull(map.remove(95));
    assertEquals(5, map.size());

    assertEquals("a", map.get(1));
    assertEquals("b", map.get(-1));
    assertEquals("c", map.get(-3));
    assertEquals("d", map.get(0));
    assertEquals("e", map.get(99));
  }
}
