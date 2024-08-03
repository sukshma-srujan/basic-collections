package collections.basic;

import java.util.HashSet;
import java.util.NoSuchElementException;

public class IntHashSet implements IntIterable {
  private static final int[] EMPTY_ARR = new int[0];
  private static final int INITIAL_CAPACITY = 8;

  private IntSetNode[] buckets;
  private int size;

  public IntHashSet() {
    this.buckets = new IntSetNode[INITIAL_CAPACITY];
  }

  private int bucketIdx(int key) {
    return Math.abs(key ^ (key >>> 16)) % this.buckets.length;
  }

  public boolean add(int e) {
    IntSetNode intSetNode = find(e);
    if (intSetNode != null) {
      return false;
    }

    int bucketIdx = bucketIdx(e);
    IntSetNode newNode = new IntSetNode(e, this.buckets[bucketIdx]);
    this.buckets[bucketIdx] = newNode;
    this.size++;
    return true;
  }

  public boolean contains(int e) {
    return find(e) != null;
  }

  public boolean remove(int e) {
    int bucketIdx = bucketIdx(e);
    IntSetNode node = this.buckets[bucketIdx];
    IntSetNode previous = null;
    while ((node != null)) {
      if (node.key == e) {
        break;
      }
      previous = node;
      node = node.next;
    }

    if (node == null) {
      return false;
    }

    if (previous == null) {
      this.buckets[bucketIdx] = node.next;
    } else {
      previous.next = node.next;
    }
    this.size--;

    return true;
  }

  public int size() {
    return this.size;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public int[] toArray() {
    if (isEmpty()) {
      return EMPTY_ARR;
    }

    int[] arr = new int[this.size];
    int idx = 0;
    for (IntSetNode bucket : buckets) {
      IntSetNode node = bucket;
      while ((node != null)) {
        arr[idx++] = node.key;
        node = node.next;
      }
    }
    return arr;
  }

  public HashSet<Integer> toPlatform() {
    if (isEmpty()) {
      return new HashSet<>(0);
    }
    HashSet<Integer> platformSet = new HashSet<>(this.size);
    for (IntSetNode bucket : buckets) {
      IntSetNode node = bucket;
      while ((node != null)) {
        platformSet.add(node.key);
        node = node.next;
      }
    }
    return platformSet;
  }

  private IntSetNode find(int e) {
    int bucketIdx = bucketIdx(e);
    IntSetNode node = this.buckets[bucketIdx];
    while ((node != null)) {
      if (node.key == e) {
        return node;
      }
      node = node.next;
    }
    return null;
  }

  @Override
  public IntIterator iterator() {
    return new IntHashSetIterator();
  }

  private class IntHashSetIterator implements IntIterator {
    int[] elements = toArray();
    int pos = 0;

    @Override
    public boolean hasNext() {
      return (this.pos < this.elements.length);
    }

    @Override
    public int next() {
      if (this.pos < this.elements.length) {
        return this.elements[pos++];
      }
      throw new NoSuchElementException();
    }
  }

  private static class IntSetNode {
    int key;
    IntSetNode next;

    IntSetNode(int key, IntSetNode next) {
      this.key = key;
      this.next = next;
    }
  }
}
