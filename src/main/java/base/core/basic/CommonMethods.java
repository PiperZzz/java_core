package base.core.basic;

import java.math.*;
import java.util.*;
import java.util.stream.Collectors;

public class CommonMethods {
    /* int[]和Integer[]之间是无法Auto Boxing的 */

    public static void listToArray() {
        // 声明一个整数数组
        Integer[] integerArray = {1, 2, 3, 4, 5};
        // 先用Arrays的静态方法将数组转换为List，再将该List作为ArrayList的构造函数的参数
        List<Integer> list = new ArrayList<>(Arrays.asList(integerArray));
        list.add(6);
        Integer[] integerArray2 = new Integer[1];
        // toArray()方法参数Integer是指定的目标数组类型，[0]是目标数组的长度
        // 如果指定数组的长度小于ArrayList的size，则会将数组的长度自动调整到可以装下ArrayList中的全部元素
        // 否则数组中的空位将被填充为null
        integerArray2 = list.toArray(new Integer[0]);
        System.out.println(Arrays.toString(integerArray2));
    }

    public static void arrayMethods() {
        int[] intArray = {1, 2, 3, 4, 5};
        
        System.out.println(Arrays.toString(intArray));

        System.out.println(Arrays.binarySearch(intArray, 3));
 
        Arrays.sort(intArray);

        System.out.println(intArray.length);

        System.out.println(intArray[intArray.length - 1]);

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

    public void stringMethods() {
        String str = "Hello World";
        String str2 = new String("Hello World");
        
        for (int i = 0; i < str.length() - 1; i++) {
            System.out.println(str.charAt(i));
        }
        
        char[] charArray = str2.toCharArray();
        
        for (char c : charArray) {
            System.out.println(c);
        }

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
    }
}
