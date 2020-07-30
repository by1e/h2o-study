package cn.by1e.h2o.study.java.collection;

import cn.by1e.ox.core.constant.Constants;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bangquan.qian
 * @date 2020-07-28 11:18
 */
public class MyHashMap<K, V> extends HashMap<K, V> {
    private static final long serialVersionUID = 6993500316817662150L;

    public MyHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public MyHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    public MyHashMap() {

    }

    public MyHashMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    @Override
    public V put(K key, V value) {
        Map<String, Object> hook1 = hook();
        V v = super.put(key, value);
        Map<String, Object> hook2 = hook();
        return v;
    }

    private Map<String, Object> hook() {
        try {
            return doHook();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, Object> doHook() throws Throwable {
        Class<MyHashMap> myHashMapClass = MyHashMap.class;
        Class<? super MyHashMap> superclass = myHashMapClass.getSuperclass();
        Field[] fields = superclass.getDeclaredFields();
        Map<String, Object> fieldMapValue = new HashMap<>(fields.length);
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(this);
            fieldMapValue.put(field.getName(), value);
        }
        furtherHook(fieldMapValue);
        return fieldMapValue;
        //ConsoleUtils.sout("hook");
    }

    private void furtherHook(Map<String, Object> fieldMapValue) throws Throwable {
        furtherHook1(fieldMapValue);
    }

    @SuppressWarnings(Constants.UNCHECKED)
    private void furtherHook1(Map<String, Object> fieldMapValue) throws Throwable {
        Object object = fieldMapValue.get("table");
        Object[] table = (Object[]) object;

        List<Map<String, Object>> nodes = new ArrayList<>();

        if (table != null) {
            for (Object node : table) {
                Map<String, Object> fieldMapValue0 = new HashMap<>();
                if (node != null) {
                    Class<?> aClass = node.getClass();
                    Field[] fields = aClass.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Object value = field.get(node);
                        fieldMapValue0.put(field.getName(), value);
                    }
                    fieldMapValue0.put("_class", node.getClass().getCanonicalName());
                }
                nodes.add(fieldMapValue0);
            }
        }

        fieldMapValue.put("hook_table", nodes);
    }

}
