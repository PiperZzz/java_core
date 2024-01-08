package base.core.basic.classobject;

public class MyString {
    /* String的immutable指的是它引用（Reference）的内容（Content）无法修改，即便可以重新赋值也只是更改内容的引用
     * 所以这种immutablility自动就是thread safe */
    String str = "Hello String";

    /* 不考虑线程安全，推荐使用StringBuilder */
    StringBuilder stringBuilder = new StringBuilder("Hello String Builder"); 

    /* 由于要考虑线程安全，推荐使用StringBuffer，但性能下降 */
    StringBuffer stringBuffer = new StringBuffer("Hello String Buffer"); 
    /* SQL query is good practice for StringBuffer and StringBuilder */


    public static void stringCompare() {
    // String pool is a special storage area in Java heap that stores String literals

    // New String () vs String Literal 

    // String equals() vs == operator difference

    String s1 = "abc";
    String s2 = "abc";

    String s3 = new String("abc");
    String s4 = new String("abc");
    
    System.out.println(s1 == s2); // true // String pool String literal  
    System.out.println(s3 == s4); // false // Heap memory New String() object reference

    System.out.println(s1.equals(s2)); // true  // String literal   
    System.out.println(s3.equals(s4)); // true  // New String()
    }

    // The common methods of the String class include length(), charAt(), substring(), equals(), indexOf(), toLowerCase(), toUpperCase(), trim(), etc.
    public void stringMethods() {
        String str = "Hello World";
        String str2 = new String("Hello World");

        int indexOfSubStr = str.indexOf("Hello"); // Returns the index within this string of the first occurrence of the specified substring.
        System.out.println(indexOfSubStr); // Output: 0
        
        String subStr = str.substring(0, 5); // Returns a new string that is a substring of this string, starting at the specified index and ending at the specified index.
        System.out.println(subStr); // Output: Hello
        
        // String的长度length()方法
        for (int i = 0; i < str.length() - 1; i++) {
            System.out.println(str.charAt(i));
        }
        
        // String到char[]的转换
        char[] charArray = str2.toCharArray();

        // char[]到String的转换
        // String的构造函数可以接受char[]作为参数
        String str3 = new String(charArray);
        // String的静态方法valueOf()可以接受char[]作为参数
        String str4 = String.valueOf(charArray);

        // String[]到String的转换
        String[] strArray = {"a", "b", "c", "d", "e"};
        // String的静态方法join()可以接受String[]作为参数
        String str5 = String.join("", strArray);
        
        for (char c : charArray) {
            System.out.println(c);
        }

    }
}
