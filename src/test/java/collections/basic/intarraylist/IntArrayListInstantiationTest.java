package collections.basic.intarraylist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import collections.basic.IntArrayList;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class IntArrayListInstantiationTest {
  @Test
  void noArg_instantiation_createsEmptyList() {
    assertThat(new IntArrayList().size()).isZero();
  }

  @Test
  void someCapacity_instantiation_createsEmptyListWithSpecifiedCapacity() {
    IntArrayList intArrayList = new IntArrayList(3);

    assertThat(intArrayList.size()).isZero();
    assertThat(intArrayList).isNotNull().extracting("container").isEqualTo(new int[] {0, 0, 0});
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
  void emptyIntList_instantiation_createsEmptyList() {
    IntArrayList source = new IntArrayList();

    IntArrayList intArrayList = new IntArrayList(source);

    assertThat(intArrayList.size()).isZero();
  }

  @Test
  void emptyIntList_instantiation_createdEmptyListIsUsable() {
    IntArrayList source = new IntArrayList();

    IntArrayList intArrayList = new IntArrayList(source);
    for (int e = -7; e < 7; e++) {
      intArrayList.add(e);
    }

    assertThat(intArrayList.size()).isEqualTo(14);
    for (int e = -7; e < 7; e++) {
      assertThat(intArrayList.contains(e)).isTrue();
    }
  }

  @Test
  void singletonIntList_instantiation_createsSingletonList() {
    IntArrayList source = new IntArrayList();
    source.add(378);

    IntArrayList intArrayList = new IntArrayList(source);

    assertThat(intArrayList.size()).isOne();
    assertThat(intArrayList.contains(378)).isTrue();
  }

  @Test
  void nSizeIntList_instantiation_createsNSizeList() {
    IntArrayList source = new IntArrayList();
    for (int e = -11; e < 11; e++) {
      source.add(e);
    }

    IntArrayList intArrayList = new IntArrayList(source);

    assertThat(intArrayList.size()).isEqualTo(22);
    for (int e = -11; e < 11; e++) {
      assertThat(intArrayList.contains(e)).isTrue();
    }
  }

  @Test
  void nSizeIntListWithDuplicates_instantiation_createsNSizeListWithSameElements() {
    int[] elements = new int[] {6, -6, 4, 2, 0, 2};
    IntArrayList source = new IntArrayList();
    for (int e : elements) {
      source.add(e);
    }

    IntArrayList intArrayList = new IntArrayList(source);

    assertThat(intArrayList.size()).isEqualTo(6);
    assertThat(intArrayList.toArray()).isEqualTo(elements);
  }

  @Test
  void nSizeIntList_instantiation_createdListHasItsOwnContainer() throws Exception {
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
  void nullIterable_from_fails() {
    assertThatThrownBy(() -> IntArrayList.from(null))
        .isExactlyInstanceOf(NullPointerException.class);
  }

  @Test
  void emptyIterable_from_createsEmptyList() {
    ArrayList<Integer> source = new ArrayList<>();
    IntArrayList actual = IntArrayList.from(source);
    assertThat(actual.size()).isZero();
  }

  @Test
  void singletonIterable_from_createsSingletonList() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(873);
    IntArrayList actual = IntArrayList.from(source);
    assertThat(actual.toArray()).containsExactly(873);
  }

  @Test
  void singletonIterableContainingNull_from_fails() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(null);

    assertThatThrownBy(() -> IntArrayList.from(source)).isInstanceOf(NullPointerException.class);
  }

  @Test
  void nSizeIterable_from_succeeds() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(89487);
    source.add(873);
    source.add(42);

    IntArrayList actual = IntArrayList.from(source);

    assertThat(actual.toArray()).containsExactly(89487, 873, 42);
  }

  @Test
  void nSizeIterableContainingNull_from_fails() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(89487);
    source.add(873);
    source.add(null);
    source.add(42);

    assertThatThrownBy(() -> IntArrayList.from(source)).isInstanceOf(NullPointerException.class);
  }

  @Test
  void nullIterableWithCoerceNullToZero_from_fails() {
    assertThatThrownBy(() -> IntArrayList.from(null))
        .isExactlyInstanceOf(NullPointerException.class);
  }

  @Test
  void emptyIterableWithCoerceNullToZero_from_createsEmptyList() {
    ArrayList<Integer> source = new ArrayList<>();

    IntArrayList actual = IntArrayList.from(source, true);

    assertThat(actual.size()).isZero();
  }

  @Test
  void singletonIterableWithCoerceNullToZero_from_createsSingletonList() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(873);

    IntArrayList actual = IntArrayList.from(source);

    assertThat(actual.toArray()).containsExactly(873);
  }

  @Test
  void
      singletonIterableContainingNullWithCoerceNullToZero_from_createsSingletonListContainingZero() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(null);
    IntArrayList actual = IntArrayList.from(source, true);
    assertThat(actual.toArray()).containsExactly(0);
  }

  @Test
  void nSizeIterableContainingNullWithCoerceNullToZero_from_createsNSizeListContainingZero() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(89487);
    source.add(873);
    source.add(null);
    source.add(42);

    IntArrayList actual = IntArrayList.from(source, true);

    assertThat(actual.toArray()).containsExactly(89487, 873, 0, 42);
  }
}
