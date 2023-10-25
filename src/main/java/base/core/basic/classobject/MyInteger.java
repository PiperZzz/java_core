package base.core.basic.classobject;

public class MyInteger {
    public static void integerPool() {
        Integer integer = 1;
		Integer integer2 = 1;
		int i = 1;

		System.out.println(integer == integer2);
		System.out.println(integer == i);

		System.out.println(integer.equals(integer2));
		System.out.println(integer.equals(i));

        // 由于有类似于String的常量池，所以在-128~127之间的Integer对象，使用==和equals都是相等的
    }

    public static void integerStaticMethods() {
        
        // Integer的MAX_VALUE和MIN_VALUE是Integer的静态常量，他们的类型是int
        System.out.println(Integer.MAX_VALUE);

        // Integer的valueOf方法，返回的是Integer对象
        System.out.println(Integer.valueOf(1));

        // Integer的parseInt方法，返回的是int类型
        System.out.println(Integer.parseInt("1"));

        // Integer的toString方法，返回的是String类型
    }
}
