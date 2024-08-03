package collections.basic.hashset;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntHashSet;
import org.junit.jupiter.api.Test;

class IntHashSetRemoveTest {
  @Test
  void emptyMap_remove_hasNoEffect() {
    IntHashSet intHashSet = new IntHashSet();

    assertThat(intHashSet.remove(-51)).isFalse();
    assertThat(intHashSet.remove(-16)).isFalse();
    assertThat(intHashSet.remove(0)).isFalse();
    assertThat(intHashSet.remove(16)).isFalse();
    assertThat(intHashSet.remove(-51)).isFalse();
  }

  @Test
  void singletonMap_remove_containingElement_isRemoved() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(18);

    assertThat(intHashSet.remove(18)).isTrue();
    assertThat(intHashSet.contains(18)).isFalse();
    assertThat(intHashSet.size()).isZero();
  }

  @Test
  void singletonMap_remove_nonContainingElement_hasNoEffect() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(6);

    assertThat(intHashSet.remove(18)).isFalse();
    assertThat(intHashSet.size()).isOne();
  }

  @Test
  void twoElementMap_remove_containingElement_isRemoved() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(8);
    intHashSet.add(10);

    assertThat(intHashSet.remove(10)).isTrue();
    assertThat(intHashSet.contains(10)).isFalse();
    assertThat(intHashSet.size()).isOne();
  }

  @Test
  void twoElementMap_remove_nonContainingElement_hasNoEffect() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(-6);
    intHashSet.add(-8);

    assertThat(intHashSet.remove(-7)).isFalse();
    assertThat(intHashSet.size()).isEqualTo(2);
  }

  @Test
  void nElementMap_remove_containingElement_isRemoved() {
    IntHashSet intHashSet = new IntHashSet();
    for (int i = -29; i < 29; i++) {
      intHashSet.add(i);
    }

    assertThat(intHashSet.remove(-21)).isTrue();
    assertThat(intHashSet.contains(-21)).isFalse();
    assertThat(intHashSet.size()).isEqualTo(57);
  }

  @Test
  void nElementMap_remove_nonContainingElement_hasNoEffect() {
    IntHashSet intHashSet = new IntHashSet();
    for (int i = -29; i < 29; i++) {
      intHashSet.add(i);
    }

    assertThat(intHashSet.remove(-30)).isFalse();
    assertThat(intHashSet.size()).isEqualTo(58);
  }
}
