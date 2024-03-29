package base.core.leetcode.frequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TopKFrequentElements {
    public static List<Integer> topKFrequent(int[] nums, int k) {
        // Boundary Condition Check
        if (nums == null || nums.length == 0 || k <= 0) {
            return new ArrayList<>();
        }

        // 使用哈希表记录每个元素的频率
        Map<Integer, Integer> numToFrequencyMap = new HashMap<>();
        for (int num : nums) {
            /* getOrDefault()方法的作用是：如果Map中包含这个key，则返回key对应的value，否则返回指定的默认值,这里是0
            put()方法的作用是：如果Map中包含这个key，则更新key对应的value，否则添加这个key和value
            所以这里组合起来就是：如果Map中已经存在num这个key，则它的value即num出现的频率加1，否则它的value就是从1开始 */
            numToFrequencyMap.put(num, numToFrequencyMap.getOrDefault(num, 0) + 1);
        }

        // 使用TreeMap，以frequencyMap中num出现的频率作为键，具有相同频率的num放入一个列表作为值
        TreeMap<Integer, List<Integer>> frequencyToListOfNumMap = new TreeMap<>();
        
        for (Map.Entry<Integer, Integer> entry : numToFrequencyMap.entrySet()) {
            int frequency = entry.getValue();
            int num = entry.getKey();
            /* computeIfAbsent()方法的作用是：如果Map中不包含这个key，则添加这个key和value，否则不做任何操作
            所以这里，如果frequency不存在，则添加这个frequency为key和一个空列表为value，然后将num添加到这个空列表中
            如果frequency已经存在，则直接将num添加到这个frequency对应的列表中 */
            frequencyToListOfNumMap.computeIfAbsent(frequency, key -> new ArrayList<>()).add(num);
            /* 上面的代码等价于下面的代码
            frequencyToListOfNumMap.put(frequency, frequencyToListOfNumMap.getOrDefault(frequency, new ArrayList<>()));
            frequencyToListOfNumMap.get(frequency).add(num); */

        }

        // 从高频率到低频率遍历TreeMap，获取Top K频率元素
        List<Integer> result = new ArrayList<>();
        while (result.size() < k) {
            /* pollLastEntry()方法的作用是：返回并删除TreeMap中最后一个Entry，即最大的Entry
            TreeMap默认是按照Key的自然升序排序的，所以最后一个Entry就是Key最大的Entry */
            Map.Entry<Integer, List<Integer>> entryOfLargestKey = frequencyToListOfNumMap.pollLastEntry();
            result.addAll(entryOfLargestKey.getValue());
        }

        return result;
    }

    // PriorityQueue解，本质上是被分离出来的TreeMap的KeySet，所以可以直接使用TreeMap
    // 解题的核心还是用哈希表记录每个元素的频率，然后使用TreeMap，按照频率作为键，元素列表作为值
}
