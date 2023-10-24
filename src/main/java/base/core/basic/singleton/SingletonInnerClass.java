package base.core.basic.singleton;

public class SingletonInnerClass {
    private SingletonInnerClass() {
    }

    private static class MySingletonLazyThreadSafeInnerClassHolder {
        private static final SingletonInnerClass INSTANCE = new SingletonInnerClass();
    }

    public static SingletonInnerClass getInstance() {
        return MySingletonLazyThreadSafeInnerClassHolder.INSTANCE;
    }
}
