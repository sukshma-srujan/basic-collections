package collections.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class IntArrayList implements IntIterable {
  private static final int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;
  private static final int CONTAINER_INITIAL_SIZE = 10;
  private int[] container;
  private int size;

  public IntArrayList() {
    this.container = new int[CONTAINER_INITIAL_SIZE];
  }

  public IntArrayList(int initialCapacity) {
    if (initialCapacity < 1) {
      throw new IllegalArgumentException("initialCapacity must be greater than 0");
    }
    this.container = new int[initialCapacity];
  }

  public IntArrayList(IntArrayList source) {
    Objects.requireNonNull(source);
    this.container = Arrays.copyOf(source.container, source.size);
    this.size = source.size;
  }

  public IntArrayList(List<Integer> source) {
    Objects.requireNonNull(source);
    this.container = new int[source.size()];
    int i = 0;
    for (Integer e : source) {
      this.container[i++] = Objects.requireNonNull(e);
    }
    this.size = source.size();
  }

  public void add(int e) {
    ensureCapacity();
    this.container[this.size] = e;
    this.size++;
  }

  public void addAt(int pos, int e) {
    if (pos >= this.size || pos < 0) {
      throw new IndexOutOfBoundsException(pos);
    }
    ensureCapacity();
    shiftRight(pos);
    this.container[pos] = e;
    this.size++;
  }

  private void ensureCapacity() {
    if (this.size == this.container.length) {
      increaseCapacity();
    }
  }

  private void shiftRight(int pos) {
    int k = pos - 1;
    for (int i = this.size - 1; i > k; i--) {
      this.container[i + 1] = this.container[i];
    }
  }

  public int size() {
    return this.size;
  }

  public int get(int i) {
    if (i >= this.size || i < 0) {
      throw new IndexOutOfBoundsException(i);
    }
    return this.container[i];
  }

  public boolean contains(int e) {
    for (int i = 0; i < this.size; i++) {
      if (this.container[i] == e) {
        return true;
      }
    }
    return false;
  }

  public int indexOf(int e) {
    for (int i = 0; i < this.size; i++) {
      if (this.container[i] == e) {
        return i;
      }
    }
    return -1;
  }

  public int lastIndexOf(int e) {
    for (int i = this.size - 1; i > -1; i--) {
      if (this.container[i] == e) {
        return i;
      }
    }
    return -1;
  }

  private void increaseCapacity() {
    int newLength = this.container.length << 1;
    if (newLength < 0) {
      newLength = MAX_ARRAY_LENGTH;
    } else if (newLength == 0) {
      newLength = CONTAINER_INITIAL_SIZE;
    }
    this.container = Arrays.copyOf(this.container, newLength);
  }

  public int[] toArray() {
    return Arrays.copyOf(this.container, this.size);
  }

  public ArrayList<Integer> toPlatform() {
    ArrayList<Integer> platformList = new ArrayList<>();
    for (int i = 0; i < this.size; i++) {
      platformList.add(this.container[i]);
    }
    return platformList;
  }

  @Override
  public IntIterator iterator() {
    return new IntIteratorImpl();
  }

  private class IntIteratorImpl implements IntIterator {
    private int pos = 0;

    @Override
    public boolean hasNext() {
      return pos < size;
    }

    @Override
    public int next() {
      return container[pos++];
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
    if (this.size != other.size) {
      return false;
    }
    return Arrays.equals(this.container, 0, this.size, other.container, 0, this.size);
  }
}
