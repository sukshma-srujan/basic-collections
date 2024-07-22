package collections.basic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;

class IntArrayListTest {
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
  void zeroCapacity_instantiation_succeeds() {
    assertThatThrownBy(() -> new IntArrayList(0))
        .isExactlyInstanceOf(IllegalArgumentException.class)
        .hasMessage("initialCapacity must be greater than 0");
  }

  @Test
  void negativeCapacity_instantiation_succeeds() {
    assertThatThrownBy(() -> new IntArrayList(-1))
        .isExactlyInstanceOf(IllegalArgumentException.class)
        .hasMessage("initialCapacity must be greater than 0");
  }

  @Test
  void nullIntList_instantiation_fails() {
    assertThatThrownBy(() -> new IntArrayList((IntArrayList) null))
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
    assertThat(actual.size()).isOne();
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
    assertThat(actual.size()).isEqualTo(4);
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
}
