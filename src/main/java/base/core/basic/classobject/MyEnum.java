package base.core.basic.classobject;

// 这是带参枚举（Enum With Parameters）或被称为有实例变量枚举（Enum With Instance Variables）
public enum MyEnum {
    MOTORCYCLE("Half", 1), // 这被合称为枚举常量，括号中的参数被称为枚举构造函数的参数（Enum Constructor Arguments）
    COMPACT("Single", 2),
    REGULAR("Double", 2),
    LARGE("Triple", 3); // 必须用“；”（分号），也不能用“，”（逗号），原因是后边不再是枚举常量的声明

    // 虽然枚举实体变量不是必须被声明成final，但是为了保持整个枚举类的immutable，最好也要这样做
    private final String sizeType; // 被称作实例变量（Instance Variable）
    private final int size; // 实例变量们被声明的顺序严格对应上面枚举常量中构造函数的参数参数的顺序，也是说sizeType对枚举常量（）中的第一个参数，size对第二个
    // 参数个数在255以内，参数类型可以是primitive type也可以是reference type
    
    // 带参枚举类的构造函数必须要被明确定义，否则会出编译错误，并且必须是private
    private MyEnum(String sizeType, int size) {
        // 如果有实例变量是Collection，必须要用不可变视图，可以参考Immutable Class的写法
        this.sizeType = sizeType;
        this.size = size;
    }
    
    // 只提供getter获得参数的值，不提供任何setter类型方法
    public String getSizeType() {
        return sizeType;
    }

    public int getSize() {
        return size;
    }
}
