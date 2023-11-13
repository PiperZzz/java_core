package base.core.basic.java8newer;

/**
 * Functional Interface（函数式接口）只有一个抽象方法，对于这个方法的实现（通常是lambda表达式）就是一种行为逻辑的实现,
 * lambda表达式的Signature（签名）必须和Functional Interface（函数式接口）的抽象方法的Signature（签名）完全一致
 */
@FunctionalInterface
/* @FunctionalInterface 注解提供了编译器层面的强制检查，功能和@Override类似 */
public interface MyFunctionalInterface {
    boolean accept(String a, int b);
    /* 重载这个抽象方法也不合法 */

    default void someDefaultMethod() {
        /* 一个Functional Interface（函数式接口）可以有多个default方法，但是只能有一个抽象方法 */
        System.out.println("This is a default method in a functional interface");
    }

    static void someStaticMethod() {
        /* 一个Functional Interface（函数式接口）可以有多个static方法，但是只能有一个抽象方法 */
        System.out.println("This is a static method in a functional interface");
    }
}
