package hotel.management;

/** A hotel room and its current guest, if it is occupied. */
public final class Room {
    private final int roomNumber;
    private final String roomType;
    private final double nightlyRate;
    private String guestName;

    public Room(int roomNumber, String roomType, double nightlyRate) {
        if (roomNumber <= 0) {
            throw new IllegalArgumentException("Room number must be greater than zero.");
        }
        if (roomType == null || roomType.trim().isEmpty()) {
            throw new IllegalArgumentException("Room type must not be blank.");
        }
        if (nightlyRate <= 0) {
            throw new IllegalArgumentException("Nightly rate must be greater than zero.");
        }
        this.roomNumber = roomNumber;
        this.roomType = roomType.trim();
        this.nightlyRate = nightlyRate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getNightlyRate() {
        return nightlyRate;
    }

    public String getGuestName() {
        return guestName;
    }

    public boolean isAvailable() {
        return guestName == null;
    }

    void checkIn(String guestName) {
        this.guestName = guestName;
    }

    void checkOut() {
        guestName = null;
    }
}
