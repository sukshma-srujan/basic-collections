package collections.basic.hashmap;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntHashMap;
import collections.basic.IntHashMap.IntEntry;
import collections.basic.IntHashMap.SimpleIntEntry;
import java.util.Set;
import org.junit.jupiter.api.Test;

class IntHashMapEntrySetTest {
  @Test
  void emptyMap_entrySet_isEmpty() {
    IntHashMap<String> intHashMap = new IntHashMap<>();

    Set<IntEntry<String>> entries = intHashMap.entrySet();

    assertThat(entries).isEmpty();
  }

  @Test
  void singletonMap_entrySet_oneEntry() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(3306, "mysql default port");

    Set<IntEntry<String>> entries = intHashMap.entrySet();

    assertThat(entries).hasSize(1);
    assertThat(entries).containsExactly(new SimpleIntEntry<>(3306, "mysql default port"));
  }

  @Test
  void twoEntryMap_entrySet_twoEntries() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    intHashMap.put(3306, "mysql default port");
    intHashMap.put(5432, "postgres default port");

    Set<IntEntry<String>> entries = intHashMap.entrySet();

    assertThat(entries).hasSize(2);
    assertThat(entries)
        .containsExactlyInAnyOrder(
            new SimpleIntEntry<>(3306, "mysql default port"),
            new SimpleIntEntry<>(5432, "postgres default port"));
  }

  @Test
  void nEntryMap_entrySet_nEntries() {
    IntHashMap<String> intHashMap = new IntHashMap<>();
    @SuppressWarnings("unchecked")
    IntEntry<String>[] expectedEntries = new IntEntry[214];
    int idx = 0;
    for (int i = -107; i < 107; i++) {
      String val = "8947jde8373_" + i;
      intHashMap.put(i, val);
      expectedEntries[idx++] = new SimpleIntEntry<>(i, val);
    }

    Set<IntEntry<String>> entries = intHashMap.entrySet();

    assertThat(entries).hasSize(214);
    assertThat(entries).containsExactlyInAnyOrder(expectedEntries);
  }
}
