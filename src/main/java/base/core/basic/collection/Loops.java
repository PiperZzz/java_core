package base.core.basic.collection;

public class Loops {
    /*
     * for loop 比 while loop 更适合循环次数确定的场景
     * 用来评估loop condition的变量叫loop control variable，当它的值发生变化的时候，loop condition会自动被重新评估。
     * 但要注意这两个operations并不是原子化的。在并发环境中，loop control variable可能被其它线程修改。
     * 
     */

    public static void forLoop() {
        int[] intArray = {1, 2, 3, 4, 5};

        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }

        for (int item : intArray) {
            System.out.println(item);
        }
    }

    public static void whileLoop() {
        int[] intArray = {1, 2, 3, 4, 5};

        int i = 0;
        while (i < intArray.length) {
            System.out.println(intArray[i]);
            i++;
        }
    }
    
    public static void doWhileLoop() {
        int[] intArray = {1, 2, 3, 4, 5};

        int i = 0;
        do {
            System.out.println(intArray[i]);
            i++;
        } while (i < intArray.length);
    }
}