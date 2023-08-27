package base.core.basic.singleton;

public class MySingletonLazyThreadSafeDoubleCheck {
    private static MySingletonLazyThreadSafeDoubleCheck INSTANCE;
    
    private MySingletonLazyThreadSafeDoubleCheck() {
    }

    public static MySingletonLazyThreadSafeDoubleCheck getInstance() {
        if (INSTANCE == null) {
            synchronized (MySingletonLazyThreadSafeDoubleCheck.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MySingletonLazyThreadSafeDoubleCheck();
                }
            }
        }
        return INSTANCE;
    }
}
