package base.core.basic.system_desgin.parking_lot;

import java.util.Arrays;

public class ParkingLot {
    private final int totalSpots;
    private final ParkingSpot[] parkingSpots;
    private int remainingSpots;

    public ParkingLot(ParkingSpot[] parkingSpots) {
        this.parkingSpots = Arrays.copyOf(parkingSpots, parkingSpots.length);
        this.totalSpots = parkingSpots.length;
        this.remainingSpots = totalSpots;
    }

    public String parkVehicle(Vehicle vehicle) {
        if (isParkingLotFull()) {
            return "Parking lot is full";
        }

        ParkingSpot parkingSpot = findParkingSpot(vehicle);

        if (parkingSpot == null) {
            return "No parking spot available";
        }

        remainingSpots--;

        return "Vehicle parked successfully";
    }

    private ParkingSpot findParkingSpot(Vehicle vehicle) {
        switch(vehicle.getParkingSpotType()) {
            case MOTORCYCLE:
                /* 对于MOTORCYCLE来说，需要优先从最小的停车位停车，
                因为它可以停进任何大小的停车位，以避免浪费空间
                这里做一个简化假设，停车位在数组里已经是按空间从小到大排序过的 */
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
        // 如果对时间复杂有要求，可以考虑用一个额外的HashMap<Vehicle, ParkingSpot>来记录
        // 或者可以用HashSet<Vehicle.getLicensePlate>来判断
        // 这里为了简化，直接遍历数组
        if (isParkingLotEmpty()) {
            return "Parking lot is empty";
        }

        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getVehicle() != null && parkingSpot.getVehicle().equals(vehicle)) {
                parkingSpot.removeVehicle();
                remainingSpots++;
                return "Vehicle vacated successfully";
            }
        }
        return "Vehicle not found";
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
