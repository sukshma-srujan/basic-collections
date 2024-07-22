package collections.basic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class IntArrayListContainsTest {
  @Test
  void emptyList_contains_false() {
    IntArrayList list = new IntArrayList();
    assertThat(list.contains(3834)).isFalse();
  }

  @Test
  void list_contains_familiar_true() {
    IntArrayList list = new IntArrayList();
    list.add(83734);
    list.add(3834);
    list.add(27374);
    list.add(39314);

    assertThat(list.contains(3834)).isTrue();
  }

  @Test
  void list_contains_unfamiliar_false() {
    IntArrayList list = new IntArrayList();
    list.add(252);
    list.add(152);
    list.add(30);
    list.add(187);

    assertThat(list.contains(7834)).isFalse();
  }
}
