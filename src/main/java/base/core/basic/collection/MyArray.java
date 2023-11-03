package base.core.basic.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyArray {

    /* Array不属于Collection，是一种独立的数据结构 */

    /* int[]和Integer[]之间是无法Auto Boxing的 */

    // 这个new int[]是在堆上创建了一个数组对象，然后将这个数组对象的引用赋值给intArray
    int[] intArray = new int[]{1, 2, 3, 4, 5};

    public static void methodsFromArrays() {
        // 声明一个整数数组
        Integer[] integerArray = {1, 2, 3, 4, 5};
        
        // 先用Arrays的静态方法将数组转换为List
        // 该List只是Array的一个视图（Arrays的内部类实例）
        // 不是java.util.ArrayList，所以不能调用add()方法
        List<Integer> asList = Arrays.asList(integerArray);
        
        // 再将该List作为ArrayList的构造函数的参数
        // 此时的ArrayList是java.util.ArrayList
        // ArrayList的构造函数的参数可以是Collection包括List、Set、Queue、Deque、Stack
        List<Integer> list = new ArrayList<>(asList);
        
        // 此时可以调用add()方法
        list.add(6);

        // 声明一个整数数组，长度为5，但不初始化
        // 声明数组时，可以不初始化，但必须指定数组的长度，否则会报错
        Integer[] integerArray2 = new Integer[5];
        integerArray2[0] = 1;
        integerArray2[1] = 2;
        integerArray2[2] = 3;
        integerArray2[3] = 4;
        integerArray2[4] = 5;

        // Arrays的静态方法asList()可以将数组转换为List
        // 它可以接受两种类型参数，要么是一个数组，要么是多个元素，或者无参数
        // 如果参数是一个数组，那么返回的List是Array的一个视图，不是java.util.ArrayList
        List<Integer> asList2 = Arrays.asList(integerArray2);
        // 如果参数是多个元素，那么返回的List是java.util.ArrayList
        List<Integer> asList3 = Arrays.asList(1, 2, 3, 4, 5);
        // 如果参数是无参数，那么返回的List是一个空的java.util.ArrayList
        List<Integer> asList4 = Arrays.asList();

        // Arrays的静态方法copyOf()可以将数组复制到一个新的数组中
        // 第二个参数是新数组的长度，如果新数组的长度小于原数组的长度，则只会复制原数组的前n个元素
        // 如果新数组的长度大于原数组的长度，则会在新数组的后面填充null
        Integer[] integerArrayCopy = Arrays.copyOf(integerArray2, 10);

        // Arrays的静态方法sort()可以对数组进行自然升序排序
        Arrays.sort(integerArray2);

        // Arrays的静态方法sort()可以对数组进行自定义排序，这里自定义Comparator为降序
        // Comparator是一个函数式接口，可以用lambda表达式来实现
        // lambda表达式的参数类型是Integer，所以这里的a和b都是Integer类型
        // lambda表达式的返回值类型是int，所以这里返回a-b
        // 但是自定义Comparator排序不能对基本类型数组进行排序，只能对引用类型数组进行排序
        Arrays.sort(integerArray2, (a, b) -> b - a);

        // Arrays的静态方法fill()可以将数组的所有元素填充为指定的值
        Arrays.fill(integerArray2, 0);
        
        // toArray()方法参数中，引用类型是指定的目标数组类型，这里是Integer，[int]是目标数组的长度,这是0可以担保目标数组的长度和ArrayList的size一致
        // 如果指定数组的长度小于ArrayList的size，则会将数组的长度自动调整到可以装下ArrayList中的全部元素
        // 否则数组中的空位将被填充为null
        Integer[] integerArray3 = list.toArray(new Integer[0]);

        // Arrays的静态方法toString()可以将数组转换为字符串
        System.out.println(Arrays.toString(integerArray3));
    }
}
