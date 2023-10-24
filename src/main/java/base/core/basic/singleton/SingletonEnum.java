package base.core.basic.singleton;

public class SingletonEnum {
    private SingletonEnum() {
    }

    private enum MySingletonLazyThreadSafeEnumHolder {
        INSTANCE;
        private final SingletonEnum instance;

        MySingletonLazyThreadSafeEnumHolder() {
            instance = new SingletonEnum();
        }

        private SingletonEnum getInstance() {
            return instance;
        }
    }

    public static SingletonEnum getInstance() {
        return MySingletonLazyThreadSafeEnumHolder.INSTANCE.getInstance();
    }
}
