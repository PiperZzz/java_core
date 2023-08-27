package base.core.basic.singleton;

public class MySingletonLazyThreadSafeEnum {
    private MySingletonLazyThreadSafeEnum() {
    }

    private enum MySingletonLazyThreadSafeEnumHolder {
        INSTANCE;
        private final MySingletonLazyThreadSafeEnum instance;

        MySingletonLazyThreadSafeEnumHolder() {
            instance = new MySingletonLazyThreadSafeEnum();
        }

        private MySingletonLazyThreadSafeEnum getInstance() {
            return instance;
        }
    }

    public static MySingletonLazyThreadSafeEnum getInstance() {
        return MySingletonLazyThreadSafeEnumHolder.INSTANCE.getInstance();
    }
}
