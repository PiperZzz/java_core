package base.core.basic.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyMap {    
    public void mapMethods() {
        Map<Character, Integer> map = new HashMap<>();
        
        map.put('a', 1);
        map.put('b', 1);
        map.put('c', 1);

        Set<Character> keySet = map.keySet();
        
        Collection<Integer> values = map.values();

        // get()方法的作用是：如果Map中包含这个key，则返回key对应的value，否则返回null
        map.get('d');

        // getOrDefault()方法的作用是：如果Map中包含这个key，则返回key对应的value，否则返回指定的默认值,这里是0
        map.getOrDefault('d', 1);

        // containsKey()方法的作用是：如果Map中包含这个key，则返回true，否则返回false
        map.containsKey('d');

        // containsValue()方法的作用是：如果Map中包含这个value，则返回true，否则返回false
        map.containsValue(1);

        // size()方法的作用是：返回Map中键值对的数量
        map.size();

        // putIfAbsent()方法的作用是：如果Map中不包含这个key，则添加这个key和value，否则不做任何操作
        map.putIfAbsent('d', 1);

        map.computeIfAbsent(null, null);

        map.computeIfPresent(null, null);

        Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();

        for (Map.Entry<Character, Integer> entry : entrySet) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    Map<String, String> hashMap = new HashMap<>();

    public void entrySetTraverse() {
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
    
    public void keySetTraverse() {
        for (String key : hashMap.keySet()) {
            System.out.println(key + " " + hashMap.get(key));
        }
    }

    public void forEachTraverse() {
        hashMap.forEach((k, v) -> System.out.println((k + " " + v)));
    }
}