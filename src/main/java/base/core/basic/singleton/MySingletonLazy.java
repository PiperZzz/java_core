package base.core.basic.singleton;

public class MySingletonLazy {
    private static MySingletonLazy INSTANCE;
    
    private MySingletonLazy() {
    }

    public static MySingletonLazy getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MySingletonLazy();
        }
        return INSTANCE;
    }
}
