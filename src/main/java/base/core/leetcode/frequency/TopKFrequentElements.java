package base.core.leetcode.frequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TopKFrequentElements {
    public static List<Integer> topKFrequent(int[] nums, int k) {
        // 使用哈希表记录每个元素的频率
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1); //将num的频率加1（num是key，频率是value）
        }

        // 使用TreeMap，按照频率作为键，元素列表作为值
        TreeMap<Integer, List<Integer>> frequencyTreeMap = new TreeMap<>();
        for (int num : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(num);
            if (!frequencyTreeMap.containsKey(frequency)) {
                frequencyTreeMap.put(frequency, new ArrayList<>());
            }
            frequencyTreeMap.get(frequency).add(num);
        }

        // 从高频率到低频率遍历TreeMap，获取Top K频率元素
        List<Integer> result = new ArrayList<>();
        while (result.size() < k) {
            Map.Entry<Integer, List<Integer>> entry = frequencyTreeMap.pollLastEntry();
            result.addAll(entry.getValue());
        }

        return result;
    }

    // PriorityQueue解，本质上是被分离出来的TreeMap的KeySet，所以可以直接使用TreeMap
    // 解题的核心还是用哈希表记录每个元素的频率，然后使用TreeMap，按照频率作为键，元素列表作为值
}
