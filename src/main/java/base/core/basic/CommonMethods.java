package base.core.basic;

import java.math.*;

public class CommonMethods {
    /* Sring Methods */
    String str = "Hello World";
    String str2 = new String("Hello World");

    public void practiseStringMethods() {
        for (int i = 0; i < str.length() - 1; i++) {
            System.out.println(str.charAt(i));
        }
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



}
