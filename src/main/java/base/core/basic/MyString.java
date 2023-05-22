package base.core.basic;

public class MyString {
    String s = "Hello String"; // String is a immutable class

    StringBuffer sb = new StringBuffer("Hello String Buffer"); // thread safe
    StringBuilder sb2 = new StringBuilder("Hello String Builder");  // not thread safe, better performance

    // String pool is a special storage area in Java heap that stores String literals
}
