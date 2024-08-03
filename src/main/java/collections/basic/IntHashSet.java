package collections.basic;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;

public class IntHashSet implements IntIterable {
  private static final int[] EMPTY_ARR = new int[0];
  private static final int INITIAL_CAPACITY = 8;
  private static final float DEFAULT_LOAD_FACTOR = 2.75f;

  private final float loadThreshold;
  private IntSetNode[] buckets;
  private int count;

  public IntHashSet() {
    this(INITIAL_CAPACITY);
  }

  public IntHashSet(int initialCapacity) {
    if (initialCapacity < 1) {
      throw new IllegalArgumentException("initialCapacity must not be less than 1");
    }
    this.loadThreshold = DEFAULT_LOAD_FACTOR;
    this.buckets = new IntSetNode[determineBucketCount(initialCapacity, this.loadThreshold)];
  }

  public IntHashSet(IntHashSet source) {
    Objects.requireNonNull(source);
    this.loadThreshold = source.loadThreshold;
    this.buckets = new IntSetNode[source.buckets.length];
    this.count = 0;
    source.forEach(this::add);
  }

  private int determineBucketCount(int capacity, float threshold) {
    int bucketCount = (int) Math.ceil(capacity / threshold);
    int bucketCountPowerOfTwo = 1;

    while (bucketCountPowerOfTwo < bucketCount) {
      bucketCountPowerOfTwo <<= 1;
    }

    return bucketCountPowerOfTwo;
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
    this.count++;
    rehashSetIfNeeded();
    return true;
  }

  private void rehashSetIfNeeded() {
    float currentLoad = (float) this.count / (float) this.buckets.length;
    if (currentLoad > this.loadThreshold) {
      rehashSet();
    }
  }

  private void rehashSet() {
    IntSetNode[] oldBuckets = this.buckets;
    this.buckets = new IntSetNode[oldBuckets.length << 1];

    for (IntSetNode oldBucket : oldBuckets) {
      IntSetNode node = oldBucket;
      while (node != null) {
        IntSetNode curNode = node;
        node = node.next;
        int bucketIdx = bucketIdx(curNode.key);
        curNode.next = this.buckets[bucketIdx];
        this.buckets[bucketIdx] = curNode;
      }
    }
  }

  @SuppressWarnings("unused")
  private void showBucketContent() {
    int maxLen = Integer.toString(this.buckets.length).length();

    int bucketIdx = 0;
    for (IntSetNode oldBucket : this.buckets) {
      IntSetNode node = oldBucket;
      StringBuilder sb = new StringBuilder();
      sb.append('[')
          .append(leftPad("" + bucketIdx, maxLen))
          .append(']')
          .append(' ')
          .append('-')
          .append('-')
          .append('>')
          .append(' ');
      sb.append('(');
      boolean hasEntry = false;
      while (node != null) {
        if (hasEntry) {
          sb.append(',').append(' ');
        }
        sb.append(node.key);
        node = node.next;
        hasEntry = true;
      }
      sb.append(')');
      System.out.println(sb);
      bucketIdx++;
    }
  }

  private String leftPad(String s, int len) {
    if (s.length() >= len) {
      return s;
    }
    return " ".repeat(len - s.length()) + s;
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
    this.count--;

    return true;
  }

  public int size() {
    return this.count;
  }

  public boolean isEmpty() {
    return this.count == 0;
  }

  public int[] toArray() {
    if (isEmpty()) {
      return EMPTY_ARR;
    }

    int[] arr = new int[this.count];
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
    HashSet<Integer> platformSet = new HashSet<>(this.count);
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
    private final int[] elements = toArray();
    private int pos = 0;

    @Override
    public boolean hasNext() {
      return (this.pos < this.elements.length);
    }

    @Override
    public int next() {
      if (this.pos < this.elements.length) {
        return this.elements[this.pos++];
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
