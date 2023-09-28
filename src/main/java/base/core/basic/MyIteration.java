package base.core.basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class MyIteration {
    static ArrayList<String> list = new ArrayList<>();
    
    static {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
    }
    
    public void forEachList() {
        for (String element : list) {
            System.out.println(element);
        }
    }

    public void forEachListLambda() {
        list.forEach(element -> System.out.println(element));
    }

    public void forEachListLambdaMethodReference() {
        list.forEach(System.out::println);
    }

    Iterator<String> iterator = list.iterator();

    public void iteratorList() {
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);
        }
    }

    public void iteratorListLambda() {
        iterator.forEachRemaining(element -> System.out.println(element));
    }
    
    public void iteratorListLambdaMethodReference() {
        iterator.forEachRemaining(System.out::println);
    }

    public void iteratorListRemove() {
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.equals("B")) {
                iterator.remove();
            }
        }
    }
    
    public void iteratorListRemoveLambda() {
        iterator.forEachRemaining(element -> {
            if (element.equals("B")) {
                iterator.remove();
            }
        });
    }

    public void iteratorListRemoveLambdaMethodReference() {
        iterator.forEachRemaining(element -> {
            if (element.equals("B")) {
                iterator.remove();
            }
        });
    }
    
    public void streamList() {
        list.stream().forEach(element -> System.out.println(element));
    }

    public void streamListMethodReference() {
        list.stream().forEach(System.out::println);
    }
    
    public void streamListRemove() {
        list.removeIf(element -> element.equals("B"));
    }

    public void streamListRemoveMethodReference() {
        list.removeIf("B"::equals);
    }
    
    static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
    }

    public void forEachMap() {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public void forEachMapLambda() {
        map.forEach((k, v) -> System.out.println((k + " " + v)));
    }
}