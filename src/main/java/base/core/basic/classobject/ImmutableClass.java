package base.core.basic.classobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

// 类本身必须是final的，不能被任何类继承
public final class ImmutableClass<T> {
    // 所有的成员变量必须是private的，确保成员变量不能从类的外部直接访问和修改
    // 所有的成员变量必须是final的，确保成员变量只能在构造中被赋值一次
    private final Collection<T> data;

    public ImmutableClass(Collection<T> data) {
        // 被用于构建成员变量的参数应被立即创建出不可变视图，成员变量赋值实际上是传递的不可变视图对象
        this.data = Collections.unmodifiableCollection(new ArrayList<T>(data));
        // Unmodifiable View 不可变视图是一个被剥夺了所有能改变其状态的方法的特殊对象
        // 当任何方法尝试修改不可变视图时，会抛出UnsupportedOperationException异常
    }

    // 不提供任何Setter性质的方法
    public Collection<T> getData() {
        return data;
    }
}
