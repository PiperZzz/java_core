package base.core.basic.singleton;

public class MySingletonEager {
    private static final MySingletonEager INSTANCE = new MySingletonEager();

    private MySingletonEager() {
    }

    public static MySingletonEager getInstance() {
        return INSTANCE;
    }
}
