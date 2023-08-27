package base.core.basic.singleton;

public class MySingletonLazyThreadSafe {
    private static MySingletonLazyThreadSafe INSTANCE;
    
    private MySingletonLazyThreadSafe() {
    }

    public static synchronized MySingletonLazyThreadSafe getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MySingletonLazyThreadSafe();
        }
        return INSTANCE;
    }
}
