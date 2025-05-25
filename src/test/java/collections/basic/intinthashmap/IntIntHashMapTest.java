import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntIntHashMap;
import org.junit.jupiter.api.Test;

class IntIntHashMapTest {
  @Test
  void canBeInstantiated() {
    var map = new IntIntHashMap();
    assertThat(map).isNotNull();
  }

  @Test
  void newlyCreatedMapHasSizeZero() {
    var map = new IntIntHashMap();
    assertThat(map.size()).isZero();
  }

  @Test
  void oneEntryCanBeAdded() {
    var map = new IntIntHashMap();
    assertThat(map.add(2, 4)).isTrue();
  }

  @Test
  void afterAddingOneEntry_mapSizeIsOne() {
    var map = new IntIntHashMap();
    map.add(2, 4);
    assertThat(map.size()).isOne();
  }

  @Test
  void twoEntriesCanBeAdded() {
    var map = new IntIntHashMap();
    assertThat(map.add(1, 3)).isTrue();
    assertThat(map.add(2, 4)).isTrue();
  }

  @Test
  void afterAddingTwoEntries_mapSizeIsTwo() {
    var map = new IntIntHashMap();
    map.add(0, 3);
    map.add(3, 0);
    assertThat(map.size()).isEqualTo(2);
  }

  @Test
  void multipleEntriesCanBeAdded() {
    int[][] pairs = {
      {2, 5},
      {6, 9},
      {-1, 67},
      {0, 474},
      {867, 92},
      {-5454, 7464},
      {7846746, -478474},
      {89478478, -84736},
      {245, 41},
      {-7623, 8714},
      {1452, 4764}
    };
    var map = new IntIntHashMap();
    for (int i = 0; i < pairs.length; i++) {
      assertThat(map.add(pairs[i][0], pairs[i][1])).isTrue();
    }
  }

  @Test
  void afterAddingMultipleEntries_mapSizeIsN() {
    int[][] pairs = {
      {2, 5},
      {6, 9},
      {-1, 67},
      {0, 474},
      {867, 92},
      {-5454, 7464},
      {7846746, -478474},
      {89478478, -84736},
      {245, 41},
      {-7623, 8714},
      {1452, 4764}
    };
    var map = new IntIntHashMap();
    for (int i = 0; i < pairs.length; i++) {
      map.add(pairs[i][0], pairs[i][1]);
    }

    assertThat(map.size()).isEqualTo(pairs.length);
  }

  @Test
  void addingTheKeySecondTimeDoesNotAddNewEntry() {
    var map = new IntIntHashMap();
    assertThat(map.add(736, 837)).isTrue();
    assertThat(map.add(736, 524)).isFalse();
  }

  @Test
  void addingTheKeySecondTimeDoesNotChangeMapSize() {
    var map = new IntIntHashMap();
    map.add(736, 837);
    map.add(736, 524);
    assertThat(map.size()).isEqualTo(1);
  }

  @Test
  void addingTheKeySecondTimeReturnsPreviousValue() {
    var map = new IntIntHashMap();
    map.add(736, 837);
    assertThat(map.addAndValue(736, 524)).isEqualTo(837);
  }

  @Test
  void canTellIfTheKeyExists() {
    int[][] pairs = {
      {2, 5},
      {6, 9},
      {-1, 67},
      {0, 474},
      {867, 92}
    };
    var map = new IntIntHashMap();
    for (int i = 0; i < pairs.length; i++) {
      map.add(pairs[i][0], pairs[i][1]);
    }

    assertThat(map.containsKey(-1)).isTrue();
  }

  @Test
  void canTellIfTheKeyDoesNotExist() {
    int[][] pairs = {
      {2, 5},
      {6, 9},
      {-1, 67},
      {0, 474},
      {867, 92},
      {-5454, 7464},
      {7846746, -478474},
      {89478478, -84736},
      {245, 41},
      {-7623, 8714},
      {1452, 4764}
    };
    var map = new IntIntHashMap();
    for (int i = 0; i < pairs.length; i++) {
      map.add(pairs[i][0], pairs[i][1]);
    }
    assertThat(map.containsKey(7464)).isFalse();
  }

  // todo for get
  // todo for remove
}
