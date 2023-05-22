package base.core.basic;

public class MyString {
    String s = "Hello String"; //immutable, thread safe, high performance

    StringBuffer sb = new StringBuffer("Hello String Buffer"); // thread safe, synchronized, low performance
    StringBuilder sb2 = new StringBuilder("Hello String Builder");  // not thread safe, not synchronized, high performance

    // String pool is a special storage area in Java heap that stores String literals

    // String equals() and == operator difference
}
