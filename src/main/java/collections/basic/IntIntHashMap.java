package collections.basic;

import java.util.NoSuchElementException;

public class IntIntHashMap {
  private static final int INITIAL_SIZE = 10;
  private static final float DEFAULT_LOAD_FACTOR = 0.75f;

  private final float loadFactor;
  private Node[] buckets;
  private int count = 0;

  public IntIntHashMap() {
    this.buckets = new Node[INITIAL_SIZE];
    this.loadFactor = DEFAULT_LOAD_FACTOR;
  }

  public boolean add(int key, int value) {
    Node existing = find(key);
    if (existing != null) {
      Integer prev = existing.value;
      existing.value = value;
      return false;
    }

    if ((this.count + 1) / (float) buckets.length > loadFactor) {
      System.out.println(
          "add() Load factor reached - rehasing map. current count is " + this.count);
      rehash();
    }

    int idx = calcBucket(key);
    Node node = new Node(key, value);
    if (buckets[idx] == null) {
      buckets[idx] = node;
    } else {
      Node bucket = buckets[idx];
      node.next = bucket;
      buckets[idx] = node;
    }
    this.count++;
    return true;
  }

  public Integer addAndValue(int key, int value) {
    Node existing = find(key);
    if (existing != null) {
      Integer prev = existing.value;
      existing.value = value;
      return prev;
    }

    if ((this.count + 1) / (float) buckets.length > loadFactor) {
      System.out.println(
          "addAndValue() Load factor reached - rehasing map. current count is " + this.count);
      rehash();
    }

    int idx = calcBucket(key);
    Node node = new Node(key, value);
    if (buckets[idx] == null) {
      buckets[idx] = node;
    } else {
      Node bucket = buckets[idx];
      while (bucket.next != null) {
        bucket = bucket.next;
      }
      bucket.next = node;
    }
    this.count++;
    return null;
  }

  public boolean containsKey(int key) {
    return find(key) != null;
  }

  public int get(int key) {
    Node node = find(key);
    if (node == null) {
      throw new NoSuchElementException();
    }
    return node.value;
  }

  public Integer getValue(int key) {
    Node node = find(key);
    return node == null ? null : node.value;
  }

  public int getOrDefault(int key, int defaultValue) {
    Node node = find(key);
    return node == null ? defaultValue : node.value;
  }

  public int getOrInfinity(int key) {
    Node node = find(key);
    if (node == null) {
      return Integer.MIN_VALUE;
    }
    return node.value;
  }

  public int size() {
    return count;
  }

  public boolean remove(int key) {
    return removeNode(key) != null;
  }

  public Integer removeAndValue(int key) {
    Node node = removeNode(key);
    return node == null ? null : node.value;
  }

  private void rehash() {
    Node[] oldBuckets = this.buckets;
    this.buckets = new Node[oldBuckets.length << 1];

    for (Node bucket : buckets) {
      while (bucket != null) {
        add(bucket.key, bucket.value);
        bucket = bucket.next;
      }
    }
  }

  private Node removeNode(int key) {
    for (int bi = 0; bi < this.buckets.length; bi++) {
      Node bucket = this.buckets[bi];
      if (bucket == null) {
        continue;
      }

      if (bucket.key == key) {
        this.buckets[bi] = bucket.next;
        this.count--;
        return bucket;
      }

      Node node = bucket;
      while (node.next != null) {
        if (node.next.key == key) {
          node.next = node.next.next;
          this.count--;
          return node.next;
        }
        node = node.next;
      }
    }

    return null;
  }

  private Node find(int key) {
    for (Node bucket : this.buckets) {
      if (bucket == null) {
        continue;
      }
      Node node = bucket;
      while (node != null) {
        if (node.key == key) {
          return node;
        }
        node = node.next;
      }
    }
    return null;
  }

  private int calcBucket(int key) {
    return Math.abs(key ^ (key >>> 16)) % buckets.length;
  }

  private static class Node {
    int key;
    int value;
    Node next;

    Node(int key, int value) {
      this.key = key;
      this.value = value;
    }

    Node(int key, int value, Node next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }

  // public static class SimpleIntIntEntry implements IntIntEntry {
  //   private final int key;
  //   private final int value;

  //   public SimpleIntIntEntry(int key, int value) {
  //     this.key = key;
  //     this.value = value;
  //   }
  // }

  // public interface IntIntEntry {
  //   int getKey();

  //   int getValue();
  // }
}
