package collections.basic;

import java.util.HashMap;

@SuppressWarnings("unused")
public class IntHashMap<V> {
  private static final int INITIAL_CAPACITY = 8;

  private final IntNode<V>[] buckets;
  private int count = 0;

  @SuppressWarnings("unchecked")
  public IntHashMap() {
    buckets = new IntNode[INITIAL_CAPACITY];
  }

  private int hash(int key) {
    return Math.abs(key ^ (key >>> 16)) % buckets.length;
  }

  public V put(int key, V value) {
    IntNode<V> byKey;
    if ((byKey = findByKey(key)) != null) {
      V old = byKey.value;
      byKey.value = value;
      return old;
    }

    int bucketIdx = hash(key);
    IntNode<V> newNode = new IntNode<>(key, value);
    newNode.next = buckets[bucketIdx];
    buckets[bucketIdx] = newNode;
    count++;
    return null;
  }

  public boolean contains(int key) {
    return findByKey(key) != null;
  }

  public V get(int key) {
    IntNode<V> node = findByKey(key);
    return node != null ? node.value : null;
  }

  public int size() {
    return count;
  }

  public V remove(int key) {
    int bucketIdx = hash(key);
    IntNode<V> previous = null;
    IntNode<V> current = buckets[bucketIdx];
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
    for (IntNode<V> bucket : buckets) {
      IntNode<V> node = bucket;
      while (node != null) {
        platformMap.put(node.key, node.value);
        node = node.next;
      }
    }
    return platformMap;
  }

  private IntNode<V> findByKey(int key) {
    int hash = hash(key);
    IntNode<V> node = buckets[hash];
    while (node != null) {
      if (node.key == key) {
        return node;
      }
      node = node.next;
    }
    return null;
  }

  private static class IntNode<V> {
    int key;
    V value;
    IntNode<V> next;

    private IntNode(int key, V value) {
      this.key = key;
      this.value = value;
    }
  }
}
