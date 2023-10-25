package base.core.basic.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyArray {
    /* int[]和Integer[]之间是无法Auto Boxing的 */

    public static void arrayToList() {
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
        
        // toArray()方法参数中，引用类型是指定的目标数组类型，这里是Integer，[int]是目标数组的长度,这是0可以担保目标数组的长度和ArrayList的size一致
        // 如果指定数组的长度小于ArrayList的size，则会将数组的长度自动调整到可以装下ArrayList中的全部元素
        // 否则数组中的空位将被填充为null
        integerArray2 = list.toArray(new Integer[0]);

        // Arrays的静态方法toString()可以将数组转换为字符串
        System.out.println(Arrays.toString(integerArray2));
    }
}
