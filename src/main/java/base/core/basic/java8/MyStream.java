package base.core.basic.java8;

import java.util.ArrayList;
import java.util.Arrays;

public class MyStream {
    public static void streamMethods() {
        ArrayList<String> myList = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));

        // forEach()方法是Java8中新增的方法，它的参数是一个Consumer接口，Consumer接口是一个函数式接口，它的抽象方法是accept()方法

        myList.stream().filter(s -> s.compareTo("F") > 0).forEach(s -> System.out.println(s));
    }
}
