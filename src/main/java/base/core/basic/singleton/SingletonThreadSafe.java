package base.core.basic.singleton;

public class SingletonThreadSafe {
    private static SingletonThreadSafe INSTANCE;
    
    private SingletonThreadSafe() {
    }

    public static synchronized SingletonThreadSafe getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonThreadSafe();
        }
        return INSTANCE;
    }
}
