package base.core.basic.ood.parking;

/* 车辆类是必须有的
如果车辆种类过于庞杂，可以用abstract把Vehicle基类，再延申出子类型 */
public class Vehicle {
    /* 必须包含一个车辆的唯一身份标识，对于车辆是车牌号
    这个唯一身份标识是要被用于判断类实例是否相同的依据
    题目如果不在意车辆的唯一性，可以大大简化设计，但考虑到这一点会加分 */
    private final String licensePlate;

    /* 必须包含一个车辆停车所需空间信息
    假如题目的意思是，车辆类型同为Car，但分Regular和Compact两种，有不同停车空间需求
    那就干脆用车辆的停车空间信息，当需要车辆类型时，去用车辆空间信息映射到车辆类型 */
    private final ParkingSpotType parkingSpotType;

    protected Vehicle(String licensePlate, ParkingSpotType parkingSpotType) {
        this.licensePlate = licensePlate;
        this.parkingSpotType = parkingSpotType;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public ParkingSpotType getParkingSpotType() {
        return this.parkingSpotType;
    }

    // 上面提到的车辆唯一身份标识，就要作为hashCode()和euqals()一致性判断的依据
    @Override
    public int hashCode() {
        return this.licensePlate.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Vehicle)) {
            return false;
        }

        Vehicle vehicle = (Vehicle)obj;
        return this.licensePlate.equals(vehicle.getLicensePlate());
    }
}