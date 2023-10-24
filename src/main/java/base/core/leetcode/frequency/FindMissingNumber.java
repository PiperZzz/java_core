package base.core.leetcode.frequency;

public class FindMissingNumber {
    // 如果题目中没有说明数字不会重复出现，用HashMap这种通解。还有一种XOR解法，但是太复杂
    // 如果数字不会出现重复，那么可以用求和公式，然后减去数组中所有元素的和，得到的就是缺失的数字
    public int findMissingNumber(int[] nums) {
        int sum = 0;
        int n = nums.length;

        for (int number : nums) {
            sum += number;
        }

        return (n + 1) * n / 2 - sum;
    }
}
