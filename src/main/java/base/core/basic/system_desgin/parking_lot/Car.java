package base.core.basic.system_desgin.parking_lot;

import base.core.basic.system_desgin.parking_lot.Constants.CarType;

public class Car extends Vehicle {
    public Car(String licensePlate, CarType carType) {
        super(licensePlate, carType, 1);
    }
}
