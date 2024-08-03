package collections.basic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class IntHashMap<V> {
  private static final int INITIAL_BUCKET_COUNT = 8;
  private static final float DEFAULT_LOAD_THRESHOLD = 2.75f;

  private final float loadThreshold;
  private IntMapNode<V>[] buckets;
  private int count = 0;

  public IntHashMap() {
    this.loadThreshold = DEFAULT_LOAD_THRESHOLD;
    this.buckets = nodeArrayOfSize(INITIAL_BUCKET_COUNT);
  }

  public IntHashMap(int initialCapacity) {
    if (initialCapacity < 1) {
      throw new IllegalArgumentException("initialCapacity must not be less than 1");
    }
    this.loadThreshold = DEFAULT_LOAD_THRESHOLD;
    this.buckets = nodeArrayOfSize(determineBucketCount(initialCapacity, this.loadThreshold));
  }

  public IntHashMap(IntHashMap<V> source) {
    Objects.requireNonNull(source);
    this.loadThreshold = source.loadThreshold;
    this.buckets = nodeArrayOfSize(source.buckets.length);
    this.count = 0;
    source.entrySet().forEach((entry) -> this.put(entry.key(), entry.value()));
  }

  @SuppressWarnings("unchecked")
  private IntMapNode<V>[] nodeArrayOfSize(int size) {
    return new IntMapNode[size];
  }

  private int determineBucketCount(int capacity, float threshold) {
    int bucketCount = (int) Math.ceil(capacity / threshold);
    int bucketCountPowerOfTwo = 1;

    while (bucketCountPowerOfTwo < bucketCount) {
      bucketCountPowerOfTwo <<= 1;
    }

    return bucketCountPowerOfTwo;
  }

  private int hash(int key) {
    return Math.abs(key ^ (key >>> 16)) % buckets.length;
  }

  public V put(int key, V value) {
    IntMapNode<V> byKey;
    if ((byKey = findByKey(key)) != null) {
      V old = byKey.value;
      byKey.value = value;
      return old;
    }

    int bucketIdx = hash(key);
    IntMapNode<V> newNode = new IntMapNode<>(key, value);
    newNode.next = this.buckets[bucketIdx];
    this.buckets[bucketIdx] = newNode;
    this.count++;
    rehashIfNeeded();
    return null;
  }

  private void rehashIfNeeded() {
    float loadOnMap = (float) this.count / (float) this.buckets.length;
    if (loadOnMap > loadThreshold) {
      rehash();
    }
  }

  // private void showBucketContent() {
  //   int maxLen = Integer.toString(this.buckets.length).length();
  //
  //   int bucketIdx = 0;
  //   for (IntMapNode<V> oldBucket : this.buckets) {
  //     IntMapNode<V> node = oldBucket;
  //     StringBuilder sb = new StringBuilder();
  //     sb.append('[')
  //         .append(leftPad("" + bucketIdx, maxLen))
  //         .append(']')
  //         .append(' ')
  //         .append('-')
  //         .append('-')
  //         .append('>')
  //         .append(' ');
  //     sb.append('(');
  //     boolean hasEntry = false;
  //     while (node != null) {
  //       if (hasEntry) {
  //         sb.append(',').append(' ');
  //       }
  //       sb.append(node.key);
  //       node = node.next;
  //       hasEntry = true;
  //     }
  //     sb.append(')');
  //     System.out.println(sb);
  //     bucketIdx++;
  //   }
  // }
  //
  // private String leftPad(String s, int len) {
  //   if (s.length() >= len) {
  //     return s;
  //   }
  //   return " ".repeat(len - s.length()) + s;
  // }

  private void rehash() {
    int newBucketCount = this.buckets.length << 1;
    IntMapNode<V>[] oldBuckets = this.buckets;
    this.buckets = nodeArrayOfSize(newBucketCount);

    for (IntMapNode<V> oldBucket : oldBuckets) {
      IntMapNode<V> node = oldBucket;
      while (node != null) {
        IntMapNode<V> currentNode = node;
        node = node.next;
        int bucketIdx = hash(currentNode.key);
        currentNode.next = this.buckets[bucketIdx];
        this.buckets[bucketIdx] = currentNode;
      }
    }
  }

  public boolean contains(int key) {
    return findByKey(key) != null;
  }

  public V get(int key) {
    IntMapNode<V> node = findByKey(key);
    return node != null ? node.value : null;
  }

  public int size() {
    return count;
  }

  public V remove(int key) {
    int bucketIdx = hash(key);
    IntMapNode<V> previous = null;
    IntMapNode<V> current = buckets[bucketIdx];
    while ((current != null && current.key != key)) {
      previous = current;
      current = current.next;
    }

    if (current == null) {
      return null;
    }

    if (previous != null) {
      previous.next = current.next;
    } else {
      buckets[bucketIdx] = current.next;
    }
    count--;
    return current.value;
  }

  public HashMap<Integer, V> toPlatform() {
    HashMap<Integer, V> platformMap = new HashMap<>(size());
    for (IntMapNode<V> bucket : buckets) {
      IntMapNode<V> node = bucket;
      while (node != null) {
        platformMap.put(node.key, node.value);
        node = node.next;
      }
    }
    return platformMap;
  }

  public IntHashSet keySet() {
    IntHashSet intHashSet = new IntHashSet();
    for (IntMapNode<V> bucket : buckets) {
      IntMapNode<V> node = bucket;
      while (node != null) {
        intHashSet.add(node.key);
        node = node.next;
      }
    }
    return intHashSet;
  }

  public Set<IntEntry<V>> entrySet() {
    HashSet<IntEntry<V>> entrySet = new HashSet<>(this.size());
    for (IntMapNode<V> bucket : buckets) {
      IntMapNode<V> node = bucket;
      while (node != null) {
        entrySet.add(new SimpleIntEntry<>(node.key, node.value));
        node = node.next;
      }
    }
    return entrySet;
  }

  private IntMapNode<V> findByKey(int key) {
    int hash = hash(key);
    IntMapNode<V> node = buckets[hash];
    while (node != null) {
      if (node.key == key) {
        return node;
      }
      node = node.next;
    }
    return null;
  }

  public interface IntEntry<V> {
    int key();

    V value();
  }

  private static class IntMapNode<V> {
    int key;
    V value;
    IntMapNode<V> next;

    private IntMapNode(int key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  public record SimpleIntEntry<V>(int key, V value) implements IntEntry<V> {}
}
