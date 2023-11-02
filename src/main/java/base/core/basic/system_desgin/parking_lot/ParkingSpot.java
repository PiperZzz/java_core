package base.core.basic.system_desgin.parking_lot;

// 停车位类是必须有的
// 一个停车位可能会容纳多种不同车辆的逻辑，决定了车辆类和停车位类之间是Many-to-One关系
public class ParkingSpot {
    private final ParkingSpotType parkingSpotType;

    // 这种设计是假设如果一个Parking Spot被任何一种车辆占用，就不能再被其他车辆占用
    // 即使一辆摩托车占用了一个Van的停车位，这个停车位也不能再被其他摩托车占用
    // 否则这里应该是List<Vehicle> vehiclesParkedList，逻辑会更复杂
    private Vehicle vehicle;

    public ParkingSpot(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
        this.vehicle = null;
    }

    public ParkingSpotType getParkingSpotType() {
        return this.parkingSpotType;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void removeVehicle() {
        this.vehicle = null;
    }

    public boolean isSpotAvailable() {
        return this.vehicle == null;
    }
}
