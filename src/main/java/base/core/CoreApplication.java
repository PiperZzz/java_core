package base.core;

import base.core.leetcode.TwoSum;
import java.util.Arrays;

public class CoreApplication {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		int[] array = TwoSum.twoSum(new int[]{2, 11, 15, 7}, 9);
		System.out.println(Arrays.toString(array));
	}
}