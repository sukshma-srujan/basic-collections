package collections.basic.hashmap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import collections.basic.IntHashMap;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

class IntHashMapToPlatformTest {
  @Test
  void emptyMap_toPlatform_givesEmptyMap() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    HashMap<Integer, String> platformMap = intHashMap.toPlatform();
    assertThat(platformMap.size()).isZero();
  }

  @Test
  void singletonMap_toPlatform_givesSingletonMap() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(7, "single");
    HashMap<Integer, String> platformMap = intHashMap.toPlatform();
    assertThat(platformMap.size()).isOne();
    assertThat(platformMap.get(7)).isEqualTo("single");
  }

  @Test
  void nSizeMap_toPlatform_givesNSizeMap() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    for (int i = 0; i < 777; i++) {
      intHashMap.put(i, "RAKT-RANJIT-" + i);
    }

    HashMap<Integer, String> platformMap = intHashMap.toPlatform();
    assertThat(platformMap.size()).isEqualTo(777);

    for (int key = 0; key < 777; key++) {
      String val = platformMap.get(key);
      String expected = "RAKT-RANJIT-" + key;

      if (!val.equals(expected)) {
        fail("expected [" + expected + "] but found [" + val + "] for key " + key);
      }
    }
  }
}
