package base.core.basic.java8newer;

import java.util.*;
import org.springframework.lang.NonNull;


public class MyStream {

    public static void forEachMethod() {
        List<String> myList = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Set<String> mySet = new HashSet<>(Arrays.asList("A", "B", "C", "D"));
        Queue<String> myQueue = new LinkedList<>(Arrays.asList("A", "B", "C", "D"));
        Deque<String> myDeque = new LinkedList<>(Arrays.asList("A", "B", "C", "D"));
        Stack<String> myStack = new Stack<>();
        myStack.addAll(Arrays.asList("A", "B", "C", "D"));
        
        /* forEach()方法是Java8中新增的方法, 由于它是Iterable接口地默认方法，而Collection继承了Iterable接口，所以List、Set、Queue、Deque、Stack都可以调用forEach()方法
        Iterable接口中forEach()方法的参数是一个Consumer接口，Consumer接口是一个函数式接口（Functional Interface），它的抽象方法是accept()方法 */
        myList.forEach(s -> System.out.println(s));
        mySet.forEach(s -> System.out.println(s));
        myQueue.forEach(s -> System.out.println(s));
        myDeque.forEach(s -> System.out.println(s));
        myStack.forEach(s -> System.out.println(s));

        Map<String, String> myMap = new HashMap<>();
        myMap.put("A", "A");
        myMap.put("B", "B");
        myMap.put("C", "C");
        myMap.put("D", "D");

        /* 由于Map并不继承于Collection接口，所以Map不能调用forEach()方法，
        但是Map提供了自己的forEach()方法，该方法的参数是一个BiConsumer接口，BiConsumer接口是一个函数式接口，它的抽象方法是accept()方法 */
        myMap.forEach((k, v) -> System.out.println(k + " " + v));

        // 在Java 11以后，var可以用在lambda表达式的参数上，相比无类型参数的一个优势：可以用@NotNull注解来标记参数不为null
        myList.forEach((@NonNull var s) -> System.out.println(s));
        myMap.forEach((@NonNull var k, @NonNull var v) -> System.out.println(k + " " + v));
    }

    public static void filterMehod() {
        ArrayList<String> myList = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));

        myList.stream().filter(s -> s.compareTo("F") > 0).forEach(s -> System.out.println(s));
    }
}
