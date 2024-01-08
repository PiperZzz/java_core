package base.core.basic.classobject;

import java.math.*;
import java.util.*;

public class CommonMethods {

    public static void staticMethods() {
        // Math类的静态方法
        Math.abs(-1);
        Math.max(1, 2);
        Math.min(1, 2);
        
        // Integer类的静态方法
        int max =Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
    }

    public static void arrayMethods() {
        int[] intArray = {1, 2, 3, 4, 5};

        // Arrays.equals()方法可以用来比较两个数组是否相等
        Arrays.equals(intArray, intArray);
        
        Arrays.toString(intArray);

        Arrays.binarySearch(intArray, 3);
 
        Arrays.sort(intArray);

        int length = intArray.length;

        int item = intArray[intArray.length - 1];

        int[] intArray2 = new int[5];
        System.arraycopy(intArray, 0, intArray2, 0, intArray.length);
    }

    public static void listMethods() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        System.out.println(list.size());

        System.out.println(list.get(list.size() - 1));

        list.add(6);

        list.set(0, null);

        list.remove(0);

        list.sort(null);
    }

    public void collectionStreamMethods() {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();

        // 非静态stream()方法在集合List、Set和Map的Entry中
        list.stream().forEach(System.out::println);
        set.stream().forEach(System.out::println);
        map.entrySet().stream().forEach(System.out::println);

        int[] intArray = {1, 2, 3, 4, 5};
        // 静态stream()方法在Arrays中   
        Arrays.stream(intArray).forEach(System.out::println);
    }

    int int1 = 10;
    int int2 = 3;
    Integer integer1 = 10;
    Integer integer2 = 3;
    /* The constructor Integer(String) has been deprecated since version 9 and marked for removal */
    Integer integer3 = new Integer("10");

    /* BigInteger只能用constructor初始化*/
    BigInteger bigInteger1 = new BigInteger("10");

    public void practiseObjectMethods() {
        System.out.println("int1 == int2: " + (int1 == int2));
        System.out.println("integer1 == integer2: " + (integer1 == integer2));
        System.out.println("integer1.equals(integer2): " + integer1.equals(integer2));
        System.out.println("bigInteger1.equals(bigInteger3): " + bigInteger1.equals(integer3));
    }

    public void calculateQuotientRemainder() {
        BigInteger dividend = new BigInteger("15");
        BigInteger divisor = new BigInteger("2");

        BigInteger quotient = dividend.divideAndRemainder(divisor)[0];
        BigInteger remainder = dividend.divideAndRemainder(divisor)[1];

        System.out.println("商：" + quotient);
        System.out.println("余数：" + remainder);
    }

    int[] intArray = {1, 2, 3, 4, 5};
    Integer[] integerArray = {1, 2, 3, 4, 5};
    char[] charArray = {'a', 'b', 'c', 'd', 'e'};
    String[] strArray = {"a", "b", "c", "d", "e"};

    Map<Character, Integer> map = new HashMap<>();

    char char1 = 'a';
    
    public void mapPutAutoBoxing() {
        map.put(char1, int1);
        
        System.out.println(map);
    }

    

    public void arrayToList() {
        int[] intArray1 = new int[5];
        Integer[] intArray2 = {1, 2, 3, 4, 5};
        int l = intArray1.length;
    }
}
