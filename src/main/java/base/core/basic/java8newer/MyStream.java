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
        
        /* forEach()方法是Iterable接口地默认方法，而Collection继承了Iterable接口
         * 所以List、Set、Queue、Deque、Stack都可以调用forEach()方法
         * Iterable接口中forEach()方法的参数是一个Consumer接口
         * Consumer接口是一个函数式接口（Functional Interface）
         * 它的抽象方法是accept()方法 */
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

    /* stream()方法返回Stream<T>类型对象，T是调用stream()方法的集合对象泛型
    * filter()方法的参数是一个Predicate<T>函数式接口，通过实现Predicate<T>接口的test()方法来实现过滤规则
    * Functional Interface（函数式接口）只有一个抽象方法，对于这个方法的实现（通常是lambda表达式）就是一种行为逻辑的实现
    */
    public static void filterMehod() {
        ArrayList<String> myList = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));

        /* filter()方法的参数就是为实现过滤规则而存在的
         * Lambda表达式就是对过滤逻辑规则抽象方法Test()的实现，只要Test()方法（的实现）返回True就会被认为通过测试而被保留下来
         * 这里的规则是：只要比B“大”的元素就被视为通过测试而被保留下来
         */
        myList.stream().filter(s -> s.compareTo("B") > 0).forEach(s -> System.out.println(s));
    }

    /* map()方法的参数是一个Function<T, R>函数式接口，通过实现Function<T, R>接口的apply()方法来实现转换规则
     * Function<T, R>函数式接口的抽象方法apply()的参数是T类型，返回值是R类型
     */
    public static void mapMethod() {
        ArrayList<String> myList = new ArrayList<>(Arrays.asList("AAA", "BBBB", "C", "DD"));

        /* map()方法的参数就是为实现转换规则而存在的
         * Lambda表达式就是对转换逻辑规则抽象方法apply()的实现，apply()方法（的实现）返回的值就会被认为是转换后的值
         * 这里的规则是：将每个String元素的长度作为转换后的新元素
         */
        myList.stream().map(String::length).forEach(l -> System.out.println(l));
        /* String::length 是一个方法引用（Method Reference） 
         * 方法引用是一种特殊的Lambda表达式，它的语法是：类名::方法名
         * 这里是对Function<T, R>函数式接口中apply()方法的实现，apply()方法接受一个String类型的参数，并返回一个Integer类型的结果
         * 这与String类的length()方法的签名是一致的，即“类名::方法名”
        */
    }
}
