package collections.basic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public class IntHashMap<V> {
  private static final int INITIAL_CAPACITY = 8;

  private final IntMapNode<V>[] buckets;
  private int count = 0;

  @SuppressWarnings("unchecked")
  public IntHashMap() {
    buckets = new IntMapNode[INITIAL_CAPACITY];
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
    newNode.next = buckets[bucketIdx];
    buckets[bucketIdx] = newNode;
    count++;
    return null;
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
    HashMap<?, ?> hashMap = new HashMap<>();
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
