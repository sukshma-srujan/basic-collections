package collections.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class IntArrayList implements IntIterable {
  private static final int CONTAINER_MAX_LENGTH = Integer.MAX_VALUE - 8;
  private static final int CONTAINER_INITIAL_LENGTH = 8;

  private int[] container;
  private int count;

  public IntArrayList() {
    this.container = new int[CONTAINER_INITIAL_LENGTH];
  }

  public IntArrayList(int initialCapacity) {
    if (initialCapacity < 1) {
      throw new IllegalArgumentException("initialCapacity must not be less than 1");
    }
    this.container = new int[initialCapacity];
  }

  public IntArrayList(IntArrayList source) {
    Objects.requireNonNull(source);
    this.container = Arrays.copyOf(source.container, source.count);
    this.count = source.count;
  }

  public IntArrayList(List<Integer> source) {
    Objects.requireNonNull(source);
    this.container = new int[source.size()];
    int i = 0;
    for (Integer e : source) {
      this.container[i++] = Objects.requireNonNull(e);
    }
    this.count = source.size();
  }

  public void add(int e) {
    ensureCapacity();
    this.container[this.count] = e;
    this.count++;
  }

  public void addAt(int pos, int e) {
    if (pos >= this.count || pos < 0) {
      throw new IndexOutOfBoundsException(pos);
    }
    ensureCapacity();
    shiftRight(pos);
    this.container[pos] = e;
    this.count++;
  }

  private void ensureCapacity() {
    if (this.count == this.container.length) {
      increaseCapacity();
    }
  }

  private void shiftRight(int pos) {
    int k = pos - 1;
    for (int i = this.count - 1; i > k; i--) {
      this.container[i + 1] = this.container[i];
    }
  }

  public int size() {
    return this.count;
  }

  public int get(int i) {
    if (i >= this.count || i < 0) {
      throw new IndexOutOfBoundsException(i);
    }
    return this.container[i];
  }

  public boolean contains(int e) {
    for (int i = 0; i < this.count; i++) {
      if (this.container[i] == e) {
        return true;
      }
    }
    return false;
  }

  public int indexOf(int e) {
    for (int i = 0; i < this.count; i++) {
      if (this.container[i] == e) {
        return i;
      }
    }
    return -1;
  }

  public int lastIndexOf(int e) {
    for (int i = this.count - 1; i > -1; i--) {
      if (this.container[i] == e) {
        return i;
      }
    }
    return -1;
  }

  private void increaseCapacity() {
    if (this.container.length == CONTAINER_MAX_LENGTH) {
      throw new IllegalStateException("Array size reached to maximum");
    }
    int newLength = this.container.length << 1;
    if (newLength < 0) {
      newLength = CONTAINER_MAX_LENGTH;
    } else if (newLength == 0) {
      newLength = CONTAINER_INITIAL_LENGTH;
    }
    this.container = Arrays.copyOf(this.container, newLength);
  }

  public int[] toArray() {
    return Arrays.copyOf(this.container, this.count);
  }

  public ArrayList<Integer> toPlatform() {
    ArrayList<Integer> platformList = new ArrayList<>();
    for (int i = 0; i < this.count; i++) {
      platformList.add(this.container[i]);
    }
    return platformList;
  }

  @Override
  public IntIterator iterator() {
    return new IntIteratorImpl();
  }

  private class IntIteratorImpl implements IntIterator {
    private final int size = IntArrayList.this.count;
    private final int[] elements = IntArrayList.this.container;
    private int pos = 0;

    @Override
    public boolean hasNext() {
      return this.pos < this.size;
    }

    @Override
    public int next() {
      if (this.pos < this.size) {
        return this.elements[pos++];
      }
      throw new NoSuchElementException();
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof IntArrayList other)) {
      return false;
    }
    if (this.count != other.count) {
      return false;
    }
    return Arrays.equals(this.container, 0, this.count, other.container, 0, this.count);
  }

  @Override
  public String toString() {
    if (this.count == 0) {
      return "[]";
    }
    StringBuilder sb = new StringBuilder();
    sb.append('[');
    for (int i = 0; i < this.count; i++) {
      if (i > 0) {
        sb.append(',').append(' ');
      }
      sb.append(this.container[i]);
    }
    sb.append(']');
    return sb.toString();
  }
}
