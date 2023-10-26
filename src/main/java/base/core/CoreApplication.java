package base.core;

import java.util.*;

import base.core.leetcode.frequency.TopKFrequentElements;

public class CoreApplication {
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.version"));

		int[] nums = {1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 5, 6, 6, 6, 6, 6, 6};

		List<Integer> list = new ArrayList<>(TopKFrequentElements.topKFrequent(nums, 4));

		System.out.println(list);
	}
}