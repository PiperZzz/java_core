package base.core.basic.system_desgin.parking_lot;

// 假设Spot是最小空间单元，Motorcycle占用1和Compact Car占用2，Regular Car占用3个，Van占用3个Regular Car的空间
// 这种量化是为了简化判断逻辑
public enum ParkingSpotType {
    MOTORCYCLE(1, Constants.VehicleType.MOTORCYCLE),
    COMPACT(2, Constants.VehicleType.CAR),
    REGULAR(3, Constants.VehicleType.CAR),
    LARGE(9, Constants.VehicleType.VAN);

    private final int spotCount;
    private final Constants.VehicleType vehicleType;

    private ParkingSpotType(int spotCount, Constants.VehicleType vehicleType) {
        this.spotCount = spotCount;
        this.vehicleType = vehicleType;
    }

    public int getSpotCount() {
        return spotCount;
    }

    public Constants.VehicleType getVehicleType() {
        return vehicleType;
    }
}
