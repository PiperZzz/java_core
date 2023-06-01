package base.core.basic;

public class MyString {
    String s = "Hello String"; //immutable, thread safe, high performance

    StringBuffer sb = new StringBuffer("Hello String Buffer"); // thread safe, synchronized, low performance
    StringBuilder sb2 = new StringBuilder("Hello String Builder");  // not thread safe, not synchronized, high performance
    // SQL query is good practice for StringBuffer and StringBuilder


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
}
