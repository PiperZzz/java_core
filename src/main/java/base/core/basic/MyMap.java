package base.core.basic;

import java.util.HashMap;
import java.util.Map;

public class MyMap {
    public Map<String, String> map = new HashMap<String, String>();

    public void entrySetTrerse() {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
    
    public void keySetTrerse() {
        for (String key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }
    }

    public void forEachTrerse() {
        map.forEach((k, v) -> System.out.println((k + " " + v)));
    }
}