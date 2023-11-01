package base.core.basic.system_desgin.parking_lot;

import base.core.basic.system_desgin.parking_lot.Constants.CarType;

public abstract class Vehicle {
    private final String licensePlate;
    private final CarType carType;
    private final int spotsNeeded;

    protected Vehicle(String licensePlate, CarType carType, int spotsNeeded) {
        this.licensePlate = licensePlate;
        this.carType = carType;
        this.spotsNeeded = spotsNeeded;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public CarType getCarType() {
        return this.carType;
    }

    public int getSpotsNeeded() {
        return this.spotsNeeded;
    }
}