package base.core.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharater {
    public char firstUniqueChar(String s) {
        // 创建一个HashMap来存储字符和它们出现的次数
        Map<Character, Integer> charCount = new HashMap<>();
        
        // 遍历字符串并统计每个字符的出现次数
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // 再次遍历字符串，找到第一个出现次数为1的字符
        for (char c : s.toCharArray()) {
            if (charCount.get(c) == 1) {
                return c;
            }
        }
        
        // 如果没有找到非重复字符，返回空字符或者其他指定的默认值
        return ' '; // 或者你可以根据需求返回其他值
    }
}
