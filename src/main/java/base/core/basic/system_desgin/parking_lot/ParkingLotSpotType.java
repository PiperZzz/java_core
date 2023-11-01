package base.core.basic.system_desgin.parking_lot;

public enum ParkingLotSpotType {
    MOTORCYCLE(1),   // Motorcycle spot occupies 1 space
    COMPACT(1),      // Compact car spot occupies 1 space
    REGULAR(2),      // Regular car spot occupies 2 spaces
    LARGE(3);        // Large spot occupies 3 spaces

    private int spaceCount;

    private ParkingLotSpotType(int spaceCount) {
        this.spaceCount = spaceCount;
    }

    public int getSpaceCount() {
        return spaceCount;
    }

    public void setSpaceCount(int spaceCount) {
        this.spaceCount = spaceCount;
    }
}
