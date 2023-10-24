package base.core.leetcode.loop;

public class LargestNumber {
    public int findMax(int[] intArray) {
        int maxNumber = Integer.MIN_VALUE;

        for (int number : intArray) {
            maxNumber = Math.max(maxNumber, number);
        }

        return maxNumber;
    }
}
