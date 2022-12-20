package common;

import java.util.ArrayList;
import java.util.List;

public class Pair<K, V> {
    private K key;
    private V value;

    public Pair() {
    }

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    private final List<Pair<K, V>> pairList = new ArrayList<>();

    public void put(K k, V v) {
        pairList.add(new Pair<>(k, v));
    }

    public String getQueryBuilder() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (Pair<K, V> pair : pairList) {
            stringBuilder.append(pair.key);
            stringBuilder.append(pair.value);
            stringBuilder.append(", ");
        }
        String s = stringBuilder.toString();
        return s.substring(0, s.length() - 2);
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
