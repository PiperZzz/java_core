package base.core.basic.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MyList {
    // List is an ordered collection of elements
    // List allows duplicate elements
    // List is an interface
    // ArrayList is a class that implements List interface
    // ArrayList is not synchronized
    // ArrayList is not thread safe
    // ArrayList is a resizable array
    // ArrayList is a dynamic array
    
    // ArrayList is a better choice if your program does not require a lot of insertion and deletion O(n)
    // ArrayList is a better choice if your program requires random access to the list O(1)
    
    //声明空的ArrayList
    List<String> myList = new ArrayList<>();

    //构造函数声明带有初始值的ArrayList
    List<String> myList2 = new ArrayList<>(Arrays.asList("a", "b", "c"));

    String[] array = {"A", "B", "C"};    
    // 使用Arrays.asList()方法将数组转换为ArrayList视图，注意这里的ArrayList是Arrays类的内部类，而不是java.util.ArrayList，所以它的大小是固定的
    List<String> arrayList = new ArrayList<>(Arrays.asList(array));

    // 这种声明的ArrayList是java.util.ArrayList，它的大小是可变的
    ArrayList<String> mutableArrayList = new ArrayList<>(Arrays.asList(array));



    
    // ArrayList 常用构造函数
    // ArrayList() - constructs an empty ArrayList
    // ArrayList(Collection c) - constructs an ArrayList containing the elements of the specified collection
    // ArrayList(int initialCapacity) - constructs an ArrayList with the specified initial capacity
    // ArrayList<E> - constructs an empty ArrayList with the specified initial capacity
    // ArrayList<E> - constructs an ArrayList containing the elements of the specified collection


    // ArrayList 常用方法
    // add() - add an element to the ArrayList
    // addAll() - add all elements of a collection to the ArrayList
    // get() - get an element from the ArrayList
    // set() - update an element in the ArrayList
    // remove() - remove an element from the ArrayList
    // removeAll() - remove all elements of a collection from the ArrayList
    // clear() - remove all elements of the ArrayList
    // size() - get the size of the ArrayList
    // contains() - check if the ArrayList contains an element
    // isEmpty() - check if the ArrayList is empty
    // indexOf() - get the index of the first occurrence of an element in the ArrayList
    // lastIndexOf() - get the index of the last occurrence of an element in the ArrayList
    // toArray() - convert an ArrayList to an array
    // subList() - get a sublist from the ArrayList

    // sort() - sort the ArrayList
    public static void sortArrayList(List<String> myList) {
        
        // Before Java 8 Merge Sort
        // After Java 8 TimSort, merge sort + insertion sort
        myList.sort((a, b) -> a.compareTo(b));
        myList.sort(Comparator.naturalOrder());
        
        // Before Java 8 Merge Sort
        // After Java 8 Quick Sort + Merge Sort
        Collections.sort(myList);
    }

    // addIfAbsent() - add an element to the ArrayList if it is not already present

    // reverse() - reverse the ArrayList

    // clone() - clone an ArrayList

    // trimToSize() - trim the capacity of the ArrayList to the current size
    // ensureCapacity() - increase the capacity of the ArrayList
    // containsAll() - check if the ArrayList contains all elements of a collection
    // retainAll() - retain all elements of a collection in the ArrayList

    // forEach() - iterate over the ArrayList
    public static void forEachArrayList(List<String> myList) {
        myList.forEach(System.out::println);
    }

    // iterator() - get an iterator over the elements in the ArrayList

    // listIterator() - get a list iterator over the elements in the ArrayList
    // listIterator(int index) - get a list iterator over the elements in the ArrayList starting at the specified index
    // spliterator() - get a spliterator over the elements in the ArrayList
    // parallelStream() - get a parallel stream over the elements in the ArrayList

    // stream() - get a stream over the elements in the ArrayList

    // removeIf() - remove elements of the ArrayList based on a predicate
    // replaceAll() - replace all elements of the ArrayList with the specified element
    
}
