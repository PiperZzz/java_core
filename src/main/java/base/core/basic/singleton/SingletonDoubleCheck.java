package base.core.basic.singleton;

public class SingletonDoubleCheck {
    private static SingletonDoubleCheck INSTANCE;
    
    private SingletonDoubleCheck() {
    }

    public static SingletonDoubleCheck getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonDoubleCheck.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SingletonDoubleCheck();
                }
            }
        }
        return INSTANCE;
    }
}
