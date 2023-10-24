package base.core.leetcode.frequency;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharater {
    public char firstUniqueChar(String str) {
        // 创建一个HashMap来存储字符和它们出现的次数
        Map<Character, Integer> charCount = new HashMap<>();

        // 把字符串转换成字符数组
        char[] chars = str.toCharArray();
        
        // 遍历字符串并统计每个字符的出现次数
        for (char c : chars) {
            // 把char c作为key，把出现次数作为value，存入HashMap
            // getOrDefault()方法的作用是：如果Map中包含这个key，则返回key对应的value，否则返回指定的默认值,这里是0
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // 再次遍历字符串，找到第一个出现次数为1的字符
        for (char c : chars) {
            // 如果key为c的value为1，说明c只出现了一次，那么c就是第一个不重复的字符
            if (charCount.get(c) == 1) {
                return c;
            }
        }
        
        // 如果没有找到非重复字符，返回空字符或者其他指定的默认值
        return ' '; // 或者你可以根据需求返回其他值
    }
}
