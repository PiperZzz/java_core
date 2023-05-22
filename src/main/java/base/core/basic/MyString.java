package base.core.basic;

public class MyString {
    String s = "Hello String"; //immutable, thread safe, high performance

    StringBuffer sb = new StringBuffer("Hello String Buffer"); // thread safe, synchronized, low performance
    StringBuilder sb2 = new StringBuilder("Hello String Builder");  // not thread safe, not synchronized, high performance
    // SQL query is good practice for StringBuffer and StringBuilder

    // String pool is a special storage area in Java heap that stores String literals

    // New String () vs String Literal 

    // String equals() vs == operator difference

    // The common methods of the String class include length(), charAt(), substring(), equals(), indexOf(), toLowerCase(), toUpperCase(), trim(), etc.
}
