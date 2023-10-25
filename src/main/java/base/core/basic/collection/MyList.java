package base.core.basic.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MyList {
    /* List接口扩展自Collection接口 */
    
    //声明空的ArrayList
    List<String> myList = new ArrayList<>();

    //构造函数声明带有初始值的ArrayList
    List<String> myList2 = new ArrayList<>(Arrays.asList("a", "b", "c"));

    String[] array = {"A", "B", "C"};    
    // 使用Arrays.asList()方法将数组转换为ArrayList视图，注意这里的ArrayList是Arrays类的内部类，而不是java.util.ArrayList，所以它的大小是固定的
    List<String> arrayList = new ArrayList<>(Arrays.asList(array));

    // 这种声明的ArrayList是java.util.ArrayList，它的大小是可变的
    ArrayList<String> mutableArrayList = new ArrayList<>(Arrays.asList(array));

    public static void listMethods() {
        List<String> myList = new ArrayList<>(Arrays.asList("A", "B", "C"));

        myList.add("D");

        // add()方法的index参数不能大于ArrayList的size，否则会抛出IndexOutOfBoundsException异常
        // 这里myList的size是4, 最后一个元素的index是3
        myList.add(4, "E");

        // 如果index小于myList的size，那么add()方法会将元素插入到指定的index位置，而不是替换指定index位置的元素
        // 由于插入点后面的元素都会被移动，所以add()方法的时间复杂度是O(n)
        myList.add(0, "F");

        myList.set(0, "G");

        myList.get(0);

        myList.indexOf("G");

        myList.lastIndexOf("G");

        myList.remove(0);

        myList.remove("D");

        myList.addAll(Arrays.asList("F", "G"));

        List<Integer> myList2 = new ArrayList<>(Arrays.asList(3, 0, 2, 1));

        myList2.remove(1);

        //在List的泛型是Integer的情况下，必须用Integer.valueOf()方法将int转换为Integer，否则方法会当作是指定index位置的元素
        myList2.remove(Integer.valueOf(3));

        System.out.println(myList2);
    }

    
    
    // add() - add an element to the ArrayList
    // size() - get the size of the ArrayList
    // contains() - check if the ArrayList contains an element
    // isEmpty() - check if the ArrayList is empty
    // indexOf() - get the index of the first occurrence of an element in the ArrayList
    // lastIndexOf() - get the index of the last occurrence of an element in the ArrayList
    // toArray() - convert an ArrayList to an array

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
    
    public static void forLoopArrayList(List<String> myList) {
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }

        for (String element : myList) {
            System.out.println(element);
        }
    }

    // forEach() - iterate over the ArrayList
    public static void forEachArrayList(List<String> myList) {
        myList.forEach(element -> System.out.println(element));
        myList.forEach(System.out::println);
    }

    // iterator() - get an iterator over the elements in the ArrayList

    // listIterator() - get a list iterator over the elements in the ArrayList
    // listIterator(int index) - get a list iterator over the elements in the ArrayList starting at the specified index
    // spliterator() - get a spliterator over the elements in the ArrayList
    // parallelStream() - get a parallel stream over the elements in the ArrayList

    // stream() - get a stream over the elements in the ArrayList
    public static void streamArrayList(List<String> myList) {
        myList.stream().forEach(System.out::println);
    }

    // removeIf() - remove elements of the ArrayList based on a predicate
    // replaceAll() - replace all elements of the ArrayList with the specified element
    
}
