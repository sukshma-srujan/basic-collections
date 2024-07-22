package collections.basic;

import java.util.ArrayList;
import java.util.Arrays;

public class IntArrayList implements IntIterable {
  private static final int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;
  private static final int CONTAINER_INITIAL_SIZE = 10;
  private int[] container;
  private int size;

  public IntArrayList() {
    this.container = new int[CONTAINER_INITIAL_SIZE];
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
}
