package base.core.basic.singleton;

public class MySingletonLazyThreadSafeInnerClass {
    private MySingletonLazyThreadSafeInnerClass() {
    }

    private static class MySingletonLazyThreadSafeInnerClassHolder {
        private static final MySingletonLazyThreadSafeInnerClass INSTANCE = new MySingletonLazyThreadSafeInnerClass();
    }

    public static MySingletonLazyThreadSafeInnerClass getInstance() {
        return MySingletonLazyThreadSafeInnerClassHolder.INSTANCE;
    }
}
