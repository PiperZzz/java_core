package base.core;

import java.util.*;

import base.core.leetcode.ListNode;

public class CoreApplication {
	public int minCoins(int[] coins, int amount) {
        // Boundary Condition Check
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }

        /* 创建一个整数数组dp，用于存储每个金额所需的最小硬币数量
        dp[i] - index i表示当目标金额是i的时候，而dp[i]的值是目前找到所需的最小硬币数量，dp[i]的值会在遍历每个面值的硬币后更新一次
        dp[amount] 当index i达到目标amount的时候，且所有面值的硬币全部被遍历完，此时的dp[amount]值才是我们要求的结果
        dp数组的长度为amount + 1，因为dp[0]表示金额为0时所需的最小硬币数量 */
        int[] dp = new int[amount + 1];
    
        // 将dp数组的所有元素初始化为整数的最大值，表示未更新过的状态，如果这个状态维持到最后，说明以此处index i为目标金额的时候，无法找零
        Arrays.fill(dp, Integer.MAX_VALUE);
    
        // 初始化dp数组的第一个元素为0，因为凑成金额0不需要任何硬币
        dp[0] = 0;
    
        // 开始遍历硬币数组coins中的每一种硬币面值，即外层循环，DP算法的硬币面值不需要排序
        // 在遍历Coin的面值这第一层循环中，一开始会出现某些位置的MAX_VALUE无法被已知的最小组合硬币数量+1取代的情况
        for (int coin : coins) {
            // 在内层循环中，dp的index从当前硬币面值coin开始，遍历dp数组，直到index到达目标金额amount
            for (int i = coin; i <= amount; i++) {
                // 当前硬币面值为coin，随着dp的index i不断增加，dp[i - coin]的值是当少一个coin面值的硬币时，所需的最小硬币数量
                // 如果dp[i - coin]的值是无穷大，说明少一个coin面值的硬币时，无法找零，那么dp[i]的值也是无穷大
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    // 如果dp[i - coin]的值不是无穷大，说明少一个coin面值的硬币时，可以找零，那么dp[i]的值就是dp[i - coin]的值加1
                    // 在遍历第一个面值的硬币时，dp[i]的值一定会被初始化为dp[i - coin]的值加1
                    // 但是在后面其它面值遍历的过程中，dp[i]的值可能会被更小的dp[i - coin‘]+1、[i - coin‘’]+1取代
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
    
        // 最后，返回dp[amount]的值，如果它仍然是无穷大，则表示无法找零，返回-1，否则返回最小硬币数量
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /* 如果面值组合是1和任意质数，那么就可以用贪心算法
    硬币面值包含必须1，且其余面值都是质数，两个条件缺一不可
    不然就会出现coins={2, 5} amount=6，却无法找零的情况
    或者coins={1, 3，4} amount=6，却无法得到最优解的情况 */
    public static Integer minCoinsGreedy(Integer[] coins, Integer amount) {
        // Boundary Condition Check
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }

        // 从大到小排序
        Arrays.sort(coins, (a, b) -> b - a );

        // 初始化硬币数量
        Integer coinCount = 0;
        
        // 初始化剩余金额
        Integer remainingAmount = amount;

        // 从大到小遍历硬币面值
        for (int coin : coins) {
            // 如果剩余金额大于等于当前硬币面值
            if (remainingAmount >= coin) {
                // 剩余金额除以当前硬币面值，得到的商就是所需当前硬币的数量
                coinCount += remainingAmount / coin;
                // 余数就是剩余金额
                remainingAmount %= coin;
                // 如果剩余金额为0，说明已经找零完成，返回硬币数量
                if (remainingAmount == 0) {
                    return coinCount;
                }
            }
        }
        
        // 由于硬币面值包含必须1，所以必然能找零，所以这个return永远不会被执行
        // 在coin的for循环内执行返回，是为节省时间
        return 0;
    }
    //DP算法的时间复杂度是O(n*amount)，空间复杂度是O(amount)，n是硬币面值的数量
    //贪心算法的时间复杂度是O(n)+O(nlog n)=O(nlog n)，空间复杂度是O(1)，n是硬币面值的数量，O(nlog n)是排序的时间复杂度

	public static int rob(int[] nums) {
        // Boundary Condition Check
        if (nums == null || nums.length < 1) {
            return 0;
        }
        
        int n = nums.length;
        if (n == 1) {
            return nums[0];  // If there's only one house, return its value as the maximum we can rob.
        }
        
        int[] dp = new int[n];  // Create an array to store the maximum amount we can rob at each house.
        dp[0] = nums[0];  // Initialize the first element of the array with the value of the first house.
        dp[1] = Math.max(nums[0], nums[1]);  // Initialize the second element with the maximum of the first two houses.
        
        for (int i = 2; i < n; i++) {
            // For each subsequent house, calculate the maximum amount we can rob considering whether to rob the current house or skip it.
            // We take the maximum of two options:
            // 1. The maximum amount we could rob from the previous house (dp[i-1]).
            // 2. The maximum amount we could rob from two houses before plus the current house's value (dp[i-2] + nums[i]).
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        
        return dp[n - 1];  // Return the maximum amount we can rob, which is stored in the last element of the dp array.
    }  

	// 前提：数组中的数字是从0到n，且只有一个数字缺失
    // 如果数字不会出现重复，那么可以直接用求和公式，然后减去数组中所有元素的和，得到的就是缺失的数字
    public int findMissingNumber(int[] nums) {
        // Boundary Condition Check
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

    // 如果题目中没有说明数字不会重复出现，需要先过滤掉重复的数字
    public int findMissingNumber2(int[] nums) {
        Set<Integer> setSortedNums = new TreeSet<>();
        
        for (int num : nums) {
            setSortedNums.add(num);
        }

        for (int i = 0; i <= setSortedNums.size() + 1; i++) {
            if (!setSortedNums.contains(i)) {
                return i;
            }
        }
        
        return -1;
    }

    // 还有一种XOR解法，但逻辑不太好理解
    public int findMissingNumber3(int[] nums) {
        int xorSum = 0;
        for (int i = 0; i < nums.length; i++) {
            xorSum ^= i;
            xorSum ^= nums[i];
        }
        xorSum ^= nums.length;
        return xorSum;
    }

	public char firstUniqueChar(String str) {
        // Boundary Condition Check
        if (str == null || str.length() == 0) {
            return ' '; // 或者你可以根据需求返回其他值
        }
        
        // 创建一个HashMap来存储字符和它们出现的次数
        Map<Character, Integer> charCount = new HashMap<>();

        // 把字符串转换成字符数组
        char[] chars = str.toCharArray();
        
        // 遍历字符串并统计每个字符的出现次数
        for (char c : chars) {
            /* 把char c作为key，把出现次数作为value，存入HashMap
            getOrDefault()方法的作用是：如果Map中包含这个key，则返回key对应的value，否则返回指定的默认值,这里是0 */
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

	public static int[] twoSum(int[] nums, int target) {
        // Boundary Condition Check
        if (nums == null || nums.length <= 1) {
            throw new IllegalArgumentException("Invalid input array");
        }

        // 用一个HashMap来存储数组中的元素，num是Key，num的index是Value
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                // 如果差值complement存在于map的key中，则说明这个complement之前已经以某一个num的身份出现过
                return new int[]{map.get(complement), i}; // 返回答案数组

            }

            // 如果差值complement不存在于map的key中，则将当前num添加到map中
            map.put(nums[i], i);
        }
        
        throw new IllegalArgumentException("No two sum solution");
    }

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

	public void moveZeroes(int[] nums) {
        // Boundary Condition Check
        if (nums == null || nums.length == 0) {
            return;
        }

        int nonZeroIndex = 0;  // 指向下一个非零元素应该插入的位置
    
        // 将所有非零元素移到数组前面
        for (int i = 0; i < nums.length; i++) {

            /* 如果当前元素非零，则将其插入到目前nonZeroIndex位置，然后nonZeroIndex++
            如何保证赋值语句不会覆盖掉未被读取的元素？ */
            if (nums[i] != 0) {
                /* 如果当前第i个元素是零，则第nonZeroIndex个元素的赋值和index自增都会被跳过
                这样就出现了一种情况，nonZeroIndex必然比i小，而且nonZeroIndex之前的元素都是非零的
                同时也保证了赋值语句不会覆盖掉未被读取的元素 */
                nums[nonZeroIndex++] = nums[i];
            }
        }
    
        // 填充剩下的位置为0
        for (int i = nonZeroIndex; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

	public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid; // 找到目标值
            } else if (nums[mid] < target) {
                left = mid + 1; // 缩小搜索范围到右半部分
            } else {
                right = mid - 1; // 缩小搜索范围到左半部分
            }
        }
        
        return -1; // 目标值不存在
    }    

	public static int lengthOfLongestSubstring(String str) {
        // Boundary Condition Check
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int[] charCounts = new int[256];
        int left = 0;
        int right = 0;
        int maxLength = 0;

        while (right < str.length()) {
            char rightChar = str.charAt(right);
            ++charCounts[rightChar];

            while (charCounts[rightChar] > 1) {
                char leftChar = str.charAt(left);
                --charCounts[leftChar];
                ++left;
            }

            maxLength = Math.max(maxLength, right - left + 1);
            ++right;
        }

        return maxLength;
    }

	public static int maxProfit(int[] prices) {
        // Boundary Condition Check
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        // 初始化最低股价为正无穷大
        int minPrice = Integer.MAX_VALUE;
        // 初始化最大利润为0
        int maxProfit = 0;
        
        /* 这种遍历方式维持了每一个价格出现的时间顺序，这点非常重要
        因为只有这样才能保证最低股价在最大利润之前出现 */
        for (int price : prices) {
            // 更新最低股价
            minPrice = Math.min(minPrice, price);
            // 更新最大利润 
            maxProfit = Math.max(maxProfit, price - minPrice);
            // 更新最低股价并不会立即影响最大利润
        }
            
        return maxProfit;
    }

	public static int maxSubArray(int[] nums) {
        // Boundary Condition Check
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        
        // 初始化两个变量：maxSum用于跟踪到目前为止找到的最大和，currentSum用于跟踪当前子数组的和。
        int maxSum = nums[0];
        int currentSum = nums[0];
        
        // 从数组的第二个元素（索引1）开始遍历。
        for (int i = 1; i < nums.length; i++) {
            // 对于每个元素，计算新的currentSum，取当前元素本身或当前元素与前一个currentSum之和的较大值。
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            
            // 更新maxSum，取当前maxSum和新的currentSum之间的较大值。
            maxSum = Math.max(maxSum, currentSum);
        }
        
        // 返回maxSum，其中包含了输入数组中最大连续子数组的和。
        return maxSum;
    }

	public static boolean areAnagrams(String str1, String str2) {
        // Boundary Condition Check
        if (str1 == null || str2 == null) {
            return false;
        }

        // If the lengths of the strings are not same, they can't be anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Remove non-alphabet characters from the strings and convert them to lowercase
        str1 = str1.replaceAll("[^a-zA-Z]", "").toLowerCase();
        str2 = str2.replaceAll("[^a-zA-Z]", "").toLowerCase();
                
        // Convert the strings to character arrays and sort them
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);
        
        // Compare the sorted character arrays
        return Arrays.equals(charArray1, charArray2);
    }

	public boolean isValid(String str) {
        // 创建一个栈来存储左括号
        Stack<Character> stack = new Stack<>();
        
        // 遍历字符串的每个字符
        for (char c : str.toCharArray()) {
            // 如果是左括号，将其入栈
            if (c == '(' || c == '[' || c == '{') {
                // stac的push方法用于将元素入栈
                stack.push(c);
            } else {
                // 如果是右括号，检查栈是否为空
                // 如果为空，说明必然没有与之匹配的左括号，直接返回false
                if (stack.isEmpty()) {
                    return false;
                }
                
                // 弹出栈顶的左括号
                // stack的pop方法用于将栈顶元素弹出,并返回栈顶元素,也就是最后一个入栈的元素
                char top = stack.pop();
                
                // 检查右括号是否与栈顶的左括号匹配
                if (c == ')' && top != '(') {
                    return false;
                } else if (c == ']' && top != '[') {
                    return false;
                } else if (c == '}' && top != '{') {
                    return false;
                }
            }
            /* 遇到左括号就入栈，遇到右括号就立即弹出栈顶的左括号并进行匹配
            最后空栈说明每一个右括号必然有一个位于栈顶的左括号与之匹配
            核心方法是Stack的push()和pop() */
        }
        
        // 如果栈不为空，说明还有未闭合的左括号
        return stack.isEmpty();
    }

	/**
     * Problem: Find the longest common prefix among a string array.
     * 
     * Approach: Use the horizontal scanning method. 
     * Start with the first string as the reference and compare each character with the corresponding character in other strings.
     * Stop comparing and return the prefix when a mismatched character is found or when the end of a string is reached.
     */
    public static String[] strs1 = {"flower", "flow", "flight"};
    public static String[] strs2 = {"dog", "car", "racecar"};
    public static String[] strs3 = {};

    public String longestCommonPrefix(String[] strs) {
        // Simplifying Assumptions: all strings are in lowercase

        // Corner Case: if the input is empty or has no strings, return an empty string
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Initial: take the first string as the initial prefix
        String prefix = strs[0];
        
        // Algorithm: Horizontal Scanning
        // i - String Array index from 0 to strs.length - 1
        // Time Complexity: O(n) where n is the total number of characters in the string array
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                // Time Complexity: O(m) where m is the length of the prefix
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return ""; //Exit Condition: If the prefix becomes empty, return an empty string.
                }
            }
        }

        // End Condition: If the prefix is not empty, return the longest common prefix
        return prefix;
    }

	public boolean isRotation(String s1, String s2) {
        // Boundary Condition Check
        if (s1 == null || s2 == null) {
            return false;
        }
        
        if (s1.length() != s2.length()) {
            return false;
        }
        
        String temp = s1 + s1;
        return temp.contains(s2);
    }

	public static int[] mergeArrays(int[] array1, int[] array2) {
        // Boundary Condition Check

        int[] arrayMerged = new int[array1.length + array2.length];
        
        int index1 = 0;
        int index2 = 0;
        int indexMerged = 0;
      
        while (index1 < array1.length && index2 < array2.length) {
          if (array1[index1] < array2[index2]) {
            arrayMerged[indexMerged++] = array1[index1++];
          } else {
            arrayMerged[indexMerged++] = array2[index2++];
          }
        }
      
        while (index1 < array1.length) {
          arrayMerged[indexMerged++] = array1[index1++]; 
        }
      
        while (index2 < array2.length) {
          arrayMerged[indexMerged++] = array2[index2++];
        }
      
        return arrayMerged;
    }

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Boundary Condition Check
        if (l1 == null && l2 == null) {
            return null;
        }
        
        //初始化
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
    
        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
    
        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }
    
        return dummy.next;
    }

    public static ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        // Boundary Condition Check: if the input lists are null
        if (l1 == null && l2 == null) {
            return null;
        }

        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1; 
        } else {
            int val1 = l1.value;
            int val2 = l2.value;
            if (val1 < val2) {
                l1.next = mergeTwoListsRecursive(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoListsRecursive(l1, l2.next);
                return l2;
            }
        }
    }

	public boolean isPalindrome(String str) {
        // Boundary Condition Check

        // 删除非字母数字字符并将字符串转换为小写
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        
        // 使用双指针检查回文性质
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }    

	public String reverseString(String str) {
        // Boundary Condition Check

        char[] chars = str.toCharArray();
        // 双指针初始化，left从头部开始遍历，right从尾部开始遍历
        int left = 0;
        int right = str.length() - 1;
        
        // 当左右指针没有相遇时，执行循环体
        while (left < right) {
            // 交换左右指针指向的字符
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            
            // 移动指针
            left++;
            right--;
        }
        
        // 将字符数组转换回字符串
        return new String(chars);
    }

	public static void main(String[] args) {
		System.out.println(System.getProperty("java.version"));
	}
}