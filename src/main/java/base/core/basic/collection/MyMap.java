package base.core.basic.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyMap {    
    public void mapMethods() {

        /* Map不是Collection内的接口 */
        
        Map<String, Integer> hashMap = new HashMap<>();

        hashMap.put("a", 1);
        /* 如果Map中不包含这个key, 则添加这对key和value，并返回null
         * 如果Map中包含这个key，则覆盖原有的value，并返回旧值（原有的value）
         */
        hashMap.put("b", 1);
        hashMap.put("c", 1);

        /*  */
        Set<String> keySet = hashMap.keySet();
        
        /*  */
        Collection<Integer> values = hashMap.values();

        /* 如果Map中包含这个key，则返回key对应的value，否则返回指定的默认值（这里是1）
         * 注意：这个默认值并不会对Map中的键值对产生任何影响
         * 如果默认值是null，则这个方法的作用和get()完全方法一样
         */
        hashMap.getOrDefault("d", 1);


        /* 如果Map中包含这个key，则返回key对应的value，否则返回null */
        hashMap.get("d");


        /* 如果Map中不包含这个key，则添加这对key和value，并返回null
         * 如果Map中包含这个key，则不对Map做任何操作，并直接返回key对应的value
         * 它和put()方法的区别是：put()方法会覆盖原有的value，而putIfAbsent()不会
         * 它们在返回值的逻辑上是一致的，都是key存在返回旧值，key不存在返回null
         */
        hashMap.putIfAbsent("d", 1);


        /* 该方法和putIfAbsent()相似的地方是：都在key不存在时添加key和value
         * 但不同是：1、第二个参数允许函数式接口，执行一个和Key相关的操作，返回一个Value
         * 2、key不存在则添加key和计算过的value，这里和putIfAbsent不同，它会返回新值（计算后的value），而不是旧值null
         * 如果key存在则不会有任何操作，也不返回任何值，注意这点也和putIfAbsent()不同，putIfAbsent()在key存在时会返回旧值
         */
        hashMap.computeIfAbsent("d", key-> key.length());

        /*  */
        hashMap.computeIfPresent(null, null);

        // containsKey()方法的作用是：如果Map中包含这个key，则返回true，否则返回false
        hashMap.containsKey("d");

        // containsValue()方法的作用是：如果Map中包含这个value，则返回true，否则返回false
        hashMap.containsValue(1);

        // 返回Map中键值对（Entry）的数量
        hashMap.size();        
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