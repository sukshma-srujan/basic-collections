package collections.basic.hashmap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import collections.basic.IntHashMap;
import org.junit.jupiter.api.Test;

class IntHashMapInstantiationTest {
  @Test
  void instantiate_noArg_succeeds() {
    IntHashMap<String> hashMap = new IntHashMap<>();
    assertThat(hashMap.size()).isZero();
  }

  @Test
  void instantiate_initialCapacityNegative_fails() {
    assertThatThrownBy(() -> new IntHashMap<>(-1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("initialCapacity must not be less than 1");
  }

  @Test
  void instantiate_initialCapacityZero_fails() {
    assertThatThrownBy(() -> new IntHashMap<>(0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("initialCapacity must not be less than 1");
  }

  @Test
  void instantiate_initialCapacityOne_succeeds() {
    IntHashMap<String> hashMap = new IntHashMap<>(1);
    assertThat(hashMap.size()).isZero();
  }

  @Test
  void instantiate_initialCapacityOne_createdMapIsUseful() {
    IntHashMap<String> hashMap = new IntHashMap<>(1);
    hashMap.put(1, "bharat");
    assertThat(hashMap.get(1)).isEqualTo("bharat");
    assertThat(hashMap.size()).isOne();
  }

  @Test
  void instantiate_initialCapacityOne_createdMapCanAccommodateAnyNumberOfElements() {
    IntHashMap<String> hashMap = new IntHashMap<>(1);
    for (int key = 0; key < 21; key++) {
      hashMap.put(key, "bharat_" + key);
    }

    for (int key = 0; key < 21; key++) {
      assertThat(hashMap.get(key)).isEqualTo("bharat_" + key);
    }
    assertThat(hashMap.size()).isEqualTo(21);
  }

  @Test
  void instantiate_initialCapacityMoreThanOne_succeeds() {
    IntHashMap<String> hashMap = new IntHashMap<>(21);
    assertThat(hashMap.size()).isZero();
  }

  @Test
  void instantiate_initialCapacityMoreThanOne_createdMapIsUsable() {
    IntHashMap<String> hashMap = new IntHashMap<>(21);

    for (int key = 0; key < 21; key++) {
      hashMap.put(key, "bharat_" + key);
    }

    for (int key = 0; key < 21; key++) {
      assertThat(hashMap.get(key)).isEqualTo("bharat_" + key);
    }
    assertThat(hashMap.size()).isEqualTo(21);
  }

  @Test
  void instantiate_initialCapacityMoreThanOne_createdMapCanAccommodateAnyNumberOfElements() {
    IntHashMap<String> hashMap = new IntHashMap<>(21);

    for (int key = 0; key < 41; key++) {
      hashMap.put(key, "bharat_" + key);
    }

    for (int key = 0; key < 41; key++) {
      assertThat(hashMap.get(key)).isEqualTo("bharat_" + key);
    }
    assertThat(hashMap.size()).isEqualTo(41);
  }

  @Test
  void instantiate_usingEmptyIntHashMap_succeeds() {
    IntHashMap<String> source = new IntHashMap<>();

    IntHashMap<String> intHashMap = new IntHashMap<>(source);
    assertThat(intHashMap.size()).isZero();
  }

  @Test
  void instantiate_usingEmptyIntHashMap_createdMapIsUsable() {
    IntHashMap<String> source = new IntHashMap<>();

    IntHashMap<String> intHashMap = new IntHashMap<>(source);
    intHashMap.put(1, "nepal");

    assertThat(intHashMap.get(1)).isEqualTo("nepal");
    assertThat(intHashMap.size()).isOne();
  }

  @Test
  void instantiate_usingEmptyIntHashMap_createdMapCanAccommodateAnyNumberOfElements() {
    IntHashMap<String> source = new IntHashMap<>();

    IntHashMap<String> intHashMap = new IntHashMap<>(source);

    for (int e = -15; e < 15; e++) {
      intHashMap.put(e, "nepal_" + e);
    }

    for (int e = -15; e < 15; e++) {
      assertThat(intHashMap.get(e)).isEqualTo("nepal_" + e);
    }
    assertThat(intHashMap.size()).isEqualTo(30);
  }

  @Test
  void instantiate_usingSingletonIntHashMap_succeeds() {
    IntHashMap<String> source = new IntHashMap<>();
    source.put(-1, "rather");

    IntHashMap<String> intHashMap = new IntHashMap<>(source);
    assertThat(intHashMap.get(-1)).isEqualTo("rather");
    assertThat(intHashMap.size()).isEqualTo(1);
  }

  @Test
  void instantiate_usingSingletonIntHashMap_createdMapIsUsable() {
    IntHashMap<String> source = new IntHashMap<>();
    source.put(-11, "another");

    IntHashMap<String> intHashMap = new IntHashMap<>(source);
    intHashMap.put(1, "nepal");

    assertThat(intHashMap.get(-11)).isEqualTo("another");
    assertThat(intHashMap.get(1)).isEqualTo("nepal");
    assertThat(intHashMap.size()).isEqualTo(2);
  }

  @Test
  void instantiate_usingSingletonIntHashMap_createdMapCanAccommodateAnyNumberOfElements() {
    IntHashMap<String> source = new IntHashMap<>();
    source.put(-1001, "another");

    IntHashMap<String> intHashMap = new IntHashMap<>(source);

    for (int e = -15; e < 15; e++) {
      intHashMap.put(e, "fifteen_" + e);
    }

    assertThat(intHashMap.get(-1001)).isEqualTo("another");
    for (int e = -15; e < 15; e++) {
      assertThat(intHashMap.get(e)).isEqualTo("fifteen_" + e);
    }
    assertThat(intHashMap.size()).isEqualTo(31);
  }

  @Test
  void instantiate_usingNEntryIntHashMap_succeeds() {
    IntHashMap<String> source = new IntHashMap<>();
    for (int e = -6; e < 6; e++) {
      source.put(e, "Rampur_" + e);
    }

    IntHashMap<String> intHashMap = new IntHashMap<>(source);

    for (int e = -6; e < 6; e++) {
      assertThat(intHashMap.get(e)).isEqualTo("Rampur_" + e);
    }
    assertThat(intHashMap.size()).isEqualTo(12);
  }

  @Test
  void instantiate_usingNEntryIntHashMap_createdMapIsUsable() {
    IntHashMap<String> source = new IntHashMap<>();
    for (int e = -11; e < 11; e++) {
      source.put(e, "Milak_" + e);
    }

    IntHashMap<String> intHashMap = new IntHashMap<>(source);
    intHashMap.put(10001, "Bilaspur");
    intHashMap.put(-10002, "Rudrapur");

    for (int e = -11; e < 11; e++) {
      assertThat(intHashMap.get(e)).isEqualTo("Milak_" + e);
    }
    assertThat(intHashMap.get(10001)).isEqualTo("Bilaspur");
    assertThat(intHashMap.get(-10002)).isEqualTo("Rudrapur");
    assertThat(intHashMap.size()).isEqualTo(24);
  }

  @Test
  void instantiate_usingNEntryIntHashMap_createdMapCanAccommodateAnyNumberOfElements() {
    IntHashMap<String> source = new IntHashMap<>();
    for (int e = -22; e < 0; e++) {
      source.put(e, "Haridwar_" + e);
    }

    IntHashMap<String> intHashMap = new IntHashMap<>(source);
    for (int e = 0; e < 22; e++) {
      intHashMap.put(e, "Kedarnath_" + e);
    }

    for (int e = -22; e < 0; e++) {
      assertThat(intHashMap.get(e)).isEqualTo("Haridwar_" + e);
    }
    for (int e = 0; e < 22; e++) {
      assertThat(intHashMap.get(e)).isEqualTo("Kedarnath_" + e);
    }
    assertThat(intHashMap.size()).isEqualTo(44);
  }
}
