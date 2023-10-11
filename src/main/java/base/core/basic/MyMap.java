package base.core.basic;

import java.util.HashMap;
import java.util.Map;

public class MyMap {
    Map<String, String> hashMap = new HashMap<>();

    public void entrySetTrerse() {
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
    
    public void keySetTrerse() {
        for (String key : hashMap.keySet()) {
            System.out.println(key + " " + hashMap.get(key));
        }
    }

    public void forEachTrerse() {
        hashMap.forEach((k, v) -> System.out.println((k + " " + v)));
    }
}