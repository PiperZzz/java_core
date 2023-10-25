package base.core;

import java.util.*;
import java.util.stream.Collectors;

import base.core.basic.collection.MyArray;
import base.core.basic.collection.MyList;
import base.core.basic.collection.MySet;
import base.core.leetcode.dp.CoinChange;

public class CoreApplication {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		int[] array = {1,2,3,4,5};
		System.out.println(Arrays.toString(array));
		System.out.println("Hello World!");

		char[] charArray = {'a', 'b', 'c'};
		Character[] charObjectArray = {'a', 'b', 'c'};
		int[] intArray = {1, 2, 3};
		Integer[] integerArray = {1, 2, 3};
		String[] stringArray = {"a", "b", "c"};
		String str = "abc";
		StringBuffer stringBuffer = new StringBuffer("abc");

		List<String> listStrings = new ArrayList<>();
		List<String> listStrings2 = new LinkedList<>(Arrays.asList(stringArray));
		ArrayList<String> arrayListStrings = new ArrayList<>(Arrays.asList(stringArray));
		List<Character> listCharacters = new ArrayList<>();
		List<Character> listCharacters2 = new LinkedList<>(Arrays.asList(charObjectArray));
		ArrayList<Character> arrayListCharacters = new ArrayList<>(Arrays.asList(charObjectArray));
		List<Integer> listIntegers = new ArrayList<>();
		List<Integer> listIntegers2 = new LinkedList<>(Arrays.asList(integerArray));
		ArrayList<Integer> arrayListIntegers = new ArrayList<>(Arrays.asList(integerArray));
		Map<String, Integer> map = new HashMap<>();
		Set<String> set = new HashSet<>();
		Queue<String> queue = new LinkedList<>();
		Stack<String> stack = new Stack<>();
		Deque<String> deque = new LinkedList<>();
		Deque<String> deque2 = new ArrayDeque<>();

		stringArray = listStrings.toArray(new String[0]);
		integerArray = listIntegers.toArray(new Integer[0]);

		Map<Character, Integer> map2 = new HashMap<>();

		Map<Character, Integer> map3 = new HashMap<>();

		// 获取当前 Java 版本
		String version = System.getProperty("java.version");

		// 打印当前 Java 版本
		System.out.println(version);

		for (int i = 0; i < 10; i++) {
			System.out.println(i);
		}

		Integer[] coins = {1, 2, 5};
		Integer amount = 10;

		System.out.println(CoinChange.minCoinsGreedy(coins, amount));

		Integer integer = 1;
		Integer integer2 = 1;
		int i = 1;

		System.out.println(integer == integer2);
		System.out.println(integer == i);

		System.out.println(integer.equals(integer2));
		System.out.println(integer.equals(i));

		MySet.setMethods();

		MyList.listMethods();
	}
}