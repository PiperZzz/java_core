package base.core.basic.classobject;

public class MyClass {
    private final int id;

    public MyClass(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    // 重写equals()方法，确保类实例的相等性判断逻辑符合业务需求
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof MyClass)) {
            return false;
        }

        MyClass myClass = (MyClass)obj;
        return this.id == myClass.getId();
    }

    @Override
    // 重写hashCode()方法，确保类实例的相等性判断逻辑符合业务需求
    public int hashCode() {
        return this.id;
    }
    
}
