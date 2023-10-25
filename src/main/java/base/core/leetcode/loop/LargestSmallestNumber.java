package base.core.leetcode.loop;

public class LargestSmallestNumber {
    public int findMax(int[] intArray) {
        // Boundary Condition Check
        if (intArray == null || intArray.length == 0) {
            return Integer.MIN_VALUE;
        }

        int maxNumber = Integer.MIN_VALUE;

        for (int number : intArray) {
            maxNumber = Math.max(maxNumber, number);
        }

        return maxNumber;
    }

    public int findMin(int[] intArray) {
        // Boundary Condition Check
        if (intArray == null || intArray.length == 0) {
            return Integer.MAX_VALUE;
        }

        int minNumber = Integer.MAX_VALUE;

        for (int number : intArray) {
            minNumber = Math.min(minNumber, number);
        }

        return minNumber;
    }
}
