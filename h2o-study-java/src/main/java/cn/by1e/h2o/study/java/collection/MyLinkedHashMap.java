package cn.by1e.h2o.study.java.collection;

import java.util.*;

/**
 * @author bangquan.qian
 * @date 2020-07-30 19:49
 */
public class MyLinkedHashMap<K, V> implements Map<K, V> {

    private HashMap<K, V> map;

    private LinkedList<K> link;

    public MyLinkedHashMap() {
        this.map = new HashMap<>();
        this.link = new LinkedList<>();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(K key, V value) {
        V v = map.put(key, value);
        link.addLast(key);
        return v;
    }

    @Override
    public V remove(Object key) {
        V v = map.remove(key);
        link.remove(key);
        return v;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            link.addLast(e.getKey());
        }
    }

    @Override
    public void clear() {
        map.clear();
        link.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

}
