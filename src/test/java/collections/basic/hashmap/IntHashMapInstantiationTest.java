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
}
