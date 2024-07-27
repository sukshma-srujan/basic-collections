package collections.basic.hashmap;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntHashMap;
import org.junit.jupiter.api.Test;

class IntHashMapGetTest {
  @Test
  void emptyMap_getAny_isNull() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    assertThat(intHashMap.get(-1)).isNull();
    assertThat(intHashMap.get(0)).isNull();
    assertThat(intHashMap.get(1)).isNull();
  }

  @Test
  void singletonMap_getContaining_isValue() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(-1, "ten");

    assertThat(intHashMap.get(-1)).isEqualTo("ten");
  }

  @Test
  void singletonMap_getNonContaining_isNull() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(1, "ten");

    assertThat(intHashMap.get(-1)).isNull();
  }

  @Test
  void nSizeMap_getContaining_isValue() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(1, "ten1");
    intHashMap.put(-1, "ten32");
    intHashMap.put(2, "ten4");
    intHashMap.put(-84, "ten5t");

    assertThat(intHashMap.get(2)).isEqualTo("ten4");
  }

  @Test
  void nSizeMap_getNonContaining_isNull() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(1, "ten1");
    intHashMap.put(-1, "ten32");
    intHashMap.put(2, "ten4");
    intHashMap.put(-84, "ten5t");

    assertThat(intHashMap.get(20)).isNull();
  }
}
