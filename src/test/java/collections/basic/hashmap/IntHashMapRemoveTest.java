package collections.basic.hashmap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import collections.basic.IntHashMap;
import org.junit.jupiter.api.Test;

class IntHashMapRemoveTest {
  @Test
  void emptyMap_remove_hasNoEffect() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    assertThat(intHashMap.remove(853)).isNull();
    assertThat(intHashMap.size()).isZero();
  }

  @Test
  void singletonMap_removeContaining_makesMapEmpty() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(846, "cpu");
    assertThat(intHashMap.remove(846)).isEqualTo("cpu");
    assertThat(intHashMap.size()).isZero();
    assertThat(intHashMap.get(846)).isNull();
  }

  @Test
  void singletonMap_removeNonContaining_hasNoEffect() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(846, "cpu");
    assertThat(intHashMap.remove(89474)).isNull();
    assertThat(intHashMap.size()).isOne();
    assertThat(intHashMap.get(846)).isEqualTo("cpu");
  }

  @Test
  void nSizeMap_removeContaining_makesMapSmaller() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(-1, "cpu");
    intHashMap.put(74, "memory");
    intHashMap.put(52, "disk");
    intHashMap.put(-74, "total");
    assertThat(intHashMap.remove(74)).isEqualTo("memory");
    assertThat(intHashMap.size()).isEqualTo(3);
    assertThat(intHashMap.get(74)).isNull();
    assertThat(intHashMap.get(-1)).isEqualTo("cpu");
    assertThat(intHashMap.get(52)).isEqualTo("disk");
    assertThat(intHashMap.get(-74)).isEqualTo("total");
  }

  @Test
  void nSizeMap_removeNonContaining_hasNoEffect() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(-11, "parrot");
    intHashMap.put(-231, "sparrow");
    intHashMap.put(231, "pigeon");
    intHashMap.put(11, "tiger");
    assertThat(intHashMap.remove(89474)).isNull();
    assertThat(intHashMap.size()).isEqualTo(4);
    assertThat(intHashMap.get(-11)).isEqualTo("parrot");
    assertThat(intHashMap.get(-231)).isEqualTo("sparrow");
    assertThat(intHashMap.get(231)).isEqualTo("pigeon");
    assertThat(intHashMap.get(11)).isEqualTo("tiger");
  }

  @Test
  void bigSizeMap_removeContaining_makesMapSmaller() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    for (int i = 0; i < 847; i++) {
      intHashMap.put(i, "some_prefix$484" + i);
    }

    assertThat(intHashMap.size()).isEqualTo(847);
    assertThat(intHashMap.remove(67)).isEqualTo("some_prefix$48467");
    assertThat(intHashMap.size()).isEqualTo(846);

    for (int key = 0; key < 847; key++) {
      if (key == 67) {
        assertThat(intHashMap.get(key)).withFailMessage("expected null for key 67").isNull();
      } else {
        String val = intHashMap.get(key);
        String expected = "some_prefix$484" + key;
        if (!val.equals(expected)) {
          fail("expected " + expected + "  for key " + key);
        }
      }
    }
  }
}
