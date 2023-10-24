package base.core.leetcode.loop;

public class MoveZeroesToArrayEnd {
    public void moveZeroes(int[] nums) {
        int nonZeroIndex = 0;  // 指向下一个非零元素应该插入的位置
    
        // 将所有非零元素移到数组前面
        for (int i = 0; i < nums.length; i++) {

            // 如果当前元素非零，则将其插入到目前nonZeroIndex位置，然后nonZeroIndex++
            // 如何保证赋值语句不会覆盖掉未被读取的元素？
            if (nums[i] != 0) {
                // 如果当前第i个元素是零，则第nonZeroIndex个元素的赋值和index自增都会被跳过
                // 这样就出现了一种情况，nonZeroIndex必然比i小，而且nonZeroIndex之前的元素都是非零的
                // 同时也保证了赋值语句不会覆盖掉未被读取的元素
                nums[nonZeroIndex++] = nums[i];
            }
        }
    
        // 填充剩下的位置为0
        for (int i = nonZeroIndex; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
