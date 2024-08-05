package collections.basic.arraylist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import collections.basic.IntArrayList;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class IntArrayListInstantiationTest {
  @Test
  void instantiation_succeeds() {
    assertThat(new IntArrayList()).isNotNull();
  }

  @Test
  void instantiation_zeroSize() {
    IntArrayList list = new IntArrayList();
    assertThat(list.size()).isZero();
  }

  @Test
  void someCapacity_instantiation_succeeds() {
    assertThat(new IntArrayList(3))
        .isNotNull()
        .extracting("container")
        .isEqualTo(new int[] {0, 0, 0});
  }

  @Test
  void zeroCapacity_instantiation_fails() {
    assertThatThrownBy(() -> new IntArrayList(0))
        .isExactlyInstanceOf(IllegalArgumentException.class)
        .hasMessage("initialCapacity must not be less than 1");
  }

  @Test
  void negativeCapacity_instantiation_fails() {
    assertThatThrownBy(() -> new IntArrayList(-1))
        .isExactlyInstanceOf(IllegalArgumentException.class)
        .hasMessage("initialCapacity must not be less than 1");
  }

  @Test
  void nullIntList_instantiation_fails() {
    assertThatThrownBy(() -> new IntArrayList(null))
        .isExactlyInstanceOf(NullPointerException.class);
  }

  @Test
  void emptyIntList_instantiation_succeeds() {
    IntArrayList source = new IntArrayList();
    IntArrayList actual = new IntArrayList(source);
    assertThat(actual.size()).isZero();
  }

  @Test
  void singletonIntList_instantiation_succeeds() {
    IntArrayList source = new IntArrayList();
    source.add(378);
    IntArrayList actual = new IntArrayList(source);
    assertThat(actual.toArray()).isEqualTo(new int[] {378});
  }

  @Test
  void nSizeIntList_instantiation_succeeds() {
    IntArrayList source = new IntArrayList();
    source.add(378);
    source.add(-103);
    source.add(-1);
    source.add(0);
    IntArrayList actual = new IntArrayList(source);
    assertThat(actual.toArray()).isEqualTo(new int[] {378, -103, -1, 0});
  }

  @Test
  void nSizeIntList_instantiation_createsItsOwnArray() throws Exception {
    IntArrayList source = new IntArrayList();
    source.add(378);
    source.add(-103);
    source.add(-1);
    source.add(0);

    IntArrayList actual = new IntArrayList(source);
    Field containerField = IntArrayList.class.getDeclaredField("container");
    containerField.setAccessible(true);

    Object containerSource = containerField.get(source);
    Object containerActual = containerField.get(actual);

    assertThat(containerActual).isNotSameAs(containerSource);
  }

  @Test
  void null_from_fails() {
    assertThatThrownBy(() -> IntArrayList.from(null))
        .isExactlyInstanceOf(NullPointerException.class);
  }

  @Test
  void emptyList_from_createsEmptyList() {
    ArrayList<Integer> source = new ArrayList<>();
    IntArrayList actual = IntArrayList.from(source);
    assertThat(actual.size()).isZero();
  }

  @Test
  void singletonList_from_createsSingletonList() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(873);
    IntArrayList actual = IntArrayList.from(source);
    assertThat(actual.toArray()).containsExactly(873);
  }

  @Test
  void singletonList_from_whenContainsNull_fails() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(null);

    assertThatThrownBy(() -> IntArrayList.from(source)).isInstanceOf(NullPointerException.class);
  }

  @Test
  void nSizeList_from_succeeds() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(89487);
    source.add(873);
    source.add(42);
    IntArrayList actual = IntArrayList.from(source);
    assertThat(actual.toArray()).containsExactly(89487, 873, 42);
  }

  @Test
  void nSizeList_from_whenContainsAtLeastOneNull_fails() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(89487);
    source.add(873);
    source.add(null);
    source.add(42);

    assertThatThrownBy(() -> IntArrayList.from(source)).isInstanceOf(NullPointerException.class);
  }

  @Test
  void null_from_withCoerceNullToZero_fails() {
    assertThatThrownBy(() -> IntArrayList.from(null))
        .isExactlyInstanceOf(NullPointerException.class);
  }

  @Test
  void emptyList_from_withCoerceNullToZero_createsEmptyList() {
    ArrayList<Integer> source = new ArrayList<>();
    IntArrayList actual = IntArrayList.from(source, true);
    assertThat(actual.size()).isZero();
  }

  @Test
  void singletonList_from_withCoerceNullToZero_createsSingletonList() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(873);
    IntArrayList actual = IntArrayList.from(source);
    assertThat(actual.toArray()).containsExactly(873);
  }

  @Test
  void singletonList_from_withNullElementAndCoerceNullToZero_createsSingletonList() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(null);
    IntArrayList actual = IntArrayList.from(source, true);
    assertThat(actual.toArray()).containsExactly(0);
  }

  @Test
  void nSizeList_from_withAtLeastOneNullElementAndCoerceNullToZero_succeeds() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(89487);
    source.add(873);
    source.add(null);
    source.add(42);

    IntArrayList actual = IntArrayList.from(source, true);
    assertThat(actual.toArray()).containsExactly(89487, 873, 0, 42);
  }
}
