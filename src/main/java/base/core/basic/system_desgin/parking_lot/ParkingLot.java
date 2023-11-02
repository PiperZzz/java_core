package base.core.basic.system_desgin.parking_lot;

import java.util.Arrays;
import java.util.HashMap;

public class ParkingLot {
    private final int totalSpots;
    // 可以用totalSpots来控制vehicleToParkingSpotMap的大小，这样可以省去一个额外的ParkingSpot型Array
    private final ParkingSpot[] parkingSpots;
    private int remainingSpots;
    
    // 这种映射设计是假设如果一个Parking Spot被任何一种车辆占用，就不能再被其他车辆占用，
    // 即使一辆摩托车占用了一个Van的停车位，这个停车位也不能再被其他摩托车占用
    private HashMap<Vehicle, ParkingSpot> vehicleToParkingSpotMap;

    public ParkingLot(ParkingSpot[] parkingSpots) {
        this.parkingSpots = Arrays.copyOf(parkingSpots, parkingSpots.length);;
        this.totalSpots = parkingSpots.length;
        this.remainingSpots = totalSpots;
        this.vehicleToParkingSpotMap = new HashMap<>();
    }

    public String parkVehicle(Vehicle vehicle) {
        if (vehicleToParkingSpotMap.containsKey(vehicle)) {
            return "Vehicle already parked";
        }

        if (isParkingLotFull()) {
            return "Parking lot is full";
        }
        
        ParkingSpot parkingSpot = findParkingSpot(vehicle);

        if (parkingSpot == null) {
            return "No parking spot available";
        }

        vehicleToParkingSpotMap.put(vehicle, findParkingSpot(vehicle));
        return "Vehicle parked successfully";
    }

    private ParkingSpot findParkingSpot(Vehicle vehicle) {
        switch(vehicle.getParkingSpotType()) {
            case MOTORCYCLE:
                for (ParkingSpot parkingSpot : parkingSpots) {
                    if (parkingSpot.isSpotAvailable()) {
                        parkingSpot.setVehicle(vehicle);
                        return parkingSpot;
                    }
                }
                break;
            case COMPACT:
                for (ParkingSpot parkingSpot : parkingSpots) {
                    if (parkingSpot.getParkingSpotType() == ParkingSpotType.COMPACT && parkingSpot.isSpotAvailable()) {
                        parkingSpot.setVehicle(vehicle);
                        return parkingSpot;
                    }
                }
                break;
            case REGULAR:
                for (ParkingSpot parkingSpot : parkingSpots) {
                    if (parkingSpot.getParkingSpotType() == ParkingSpotType.REGULAR && parkingSpot.isSpotAvailable()) {
                        parkingSpot.setVehicle(vehicle);
                        return parkingSpot;
                    }
                }
                break;
            case LARGE:
                for (ParkingSpot parkingSpot : parkingSpots) {
                    if (parkingSpot.getParkingSpotType() == ParkingSpotType.LARGE && parkingSpot.isSpotAvailable()) {
                        parkingSpot.setVehicle(vehicle);
                        return parkingSpot;
                    }
                }
                break;
        }
        return null;
    }

    public String vacateVehicle(Vehicle vehicle) {
        //TODO
        return "";
    }

    public int getTotalSpotsOfVehicleTypeTaken(Constants.VehicleType vehicleType) {
        //TODO
        return 1;
    }

    public boolean isSpotFullForVehicleType(Constants.VehicleType vehicleType) {
        //TODO
        return false;
    }
    
    public boolean isParkingLotEmpty() {
        return this.remainingSpots == this.totalSpots;
    }
    
    public boolean isParkingLotFull() {
        return this.remainingSpots == 0;
    }
    
    public int getRemainingSpots() {
        return this.remainingSpots;
    }

    public int getTotalSpots() {
        return this.totalSpots;
    }
}
