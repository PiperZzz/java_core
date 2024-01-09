package base.core.basic.classobject;

public class SwitchCase {
    public static void switchCase() {
        MyEnum myEnum = MyEnum.MOTORCYCLE;
        switch (myEnum.getSizeType()) {
            case "Half":
                System.out.println("Half");
                break;
            case "Single":
                System.out.println("Single");
                break;
            case "Double":
                System.out.println("Double");
                break;
            case "Triple":
                System.out.println("Triple");
                break;
            default:
                System.out.println("Unknown");
        }
    }
}
