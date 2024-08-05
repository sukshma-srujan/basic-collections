package collections.basic.hashset;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntHashSet;
import org.junit.jupiter.api.Test;

class IntHashSetToStringTest {
  @Test
  void emptySet_toString_justSquareBrackets() {
    IntHashSet intHashSet = new IntHashSet();
    assertThat(intHashSet.toString()).isEqualTo("[]");
  }

  @Test
  void singletonSet_toString_elementWithinSquareBrackets() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(47);
    assertThat(intHashSet.toString()).isEqualTo("[47]");
  }

  @Test
  void nSizeSet_toString_commaSpaceSeparatedElementsWithinSquareBrackets() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(47);
    intHashSet.add(-47);
    intHashSet.add(0);
    intHashSet.add(85);

    String toStr = intHashSet.toString();

    assertThat(toStr.startsWith("[")).isTrue();
    assertThat(toStr.endsWith("]")).isTrue();

    String[] strNumbers = toStr.substring(1, toStr.length() - 1).split(", ");
    int[] actualElements = new int[strNumbers.length];
    int i = 0;
    for (String strNumber : strNumbers) {
      actualElements[i++] = Integer.parseInt(strNumber);
    }
    assertThat(actualElements).containsExactlyInAnyOrder(intHashSet.toArray());
  }
}
