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
}
