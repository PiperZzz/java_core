package base.core.basic.collection;

import java.util.*;

public class MySet {
    /* Set接口扩展自Collection接口 */
    
    public static void setMethods() {
        // Set的构造函数可以接收Collection类型的参数，但是会去除重复元素
        Set<Integer> set = new HashSet<>(Arrays.asList(1,2,3,3,4,5,5));

        System.out.println(set);

        int i = 6;
        if(set.contains(i)){
            set.add(i);
        };
    }
}
