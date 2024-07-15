package collections.basic;

import java.util.Arrays;

public class IntArraylist {
  private static final int CONTAINER_INITIAL_SIZE = 10;
  private int[] container;
  private int size;

  public IntArraylist() {
    this.container = new int[CONTAINER_INITIAL_SIZE];
  }

  public void add(int e) {
    if (this.size == this.container.length) {
      increaseCapacity();
    }
    this.container[this.size] = e;
    this.size++;
  }

  public void addAt(int pos, int e) {
    if (pos >= this.size || pos < 0) {
      throw new IndexOutOfBoundsException(pos);
    }
    if (this.size == this.container.length) {
      increaseCapacity();
    }
    shiftRight(pos);
    this.container[pos] = e;
    this.size++;
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

  private void increaseCapacity() {
    this.container = Arrays.copyOf(this.container, this.container.length * 2);
  }
}
