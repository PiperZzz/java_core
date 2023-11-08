package base.core.basic.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyMap {    
    public void mapMethods() {

        /* Map不是Collection内的接口 */
        
        Map<Character, Integer> hashMap = new HashMap<>();
        
        hashMap.put('a', 1);
        hashMap.put('b', 1);
        hashMap.put('c', 1);

        Set<Character> keySet = hashMap.keySet();
        
        Collection<Integer> values = hashMap.values();

        // get()方法的作用是：如果Map中包含这个key，则返回key对应的value，否则返回null
        hashMap.get('d');

        // getOrDefault()方法的作用是：如果Map中包含这个key，则返回key对应的value，否则返回指定的默认值,这里是0
        hashMap.getOrDefault('d', 1);

        // containsKey()方法的作用是：如果Map中包含这个key，则返回true，否则返回false
        hashMap.containsKey('d');

        // containsValue()方法的作用是：如果Map中包含这个value，则返回true，否则返回false
        hashMap.containsValue(1);

        // size()方法的作用是：返回Map中键值对的数量
        hashMap.size();

        // putIfAbsent()方法的作用是：如果Map中不包含这个key，则添加这个key和value，否则不做任何操作
        hashMap.putIfAbsent('d', 1);

        hashMap.computeIfAbsent(null, null);

        hashMap.computeIfPresent(null, null);
    }

    public void mapTraverse() {
        Map<String, Integer> hashMap = new HashMap<>();

        /* Java 8 以后就不再推荐除Entry<K, V>以外的任何传统遍Map历方式了 */
        hashMap.forEach((k, v) -> System.out.println((k + " " + v)));

        /* Entry<K, V>是Map内部类，这种遍历方式在以下两种情况下是必须的：
         * 1. 需要遍历的同时修改键值对，Stream API无法完成这种操作
         * 2. 需要按某种特定顺序遍历
         */
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}