package hotel.management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** Holds hotel rooms and applies check-in and check-out rules. */
public final class Hotel {
    private final List<Room> rooms = new ArrayList<Room>();

    public void addRoom(Room room) {
        if (findRoom(room.getRoomNumber()) != null) {
            throw new IllegalArgumentException("That room number already exists.");
        }
        rooms.add(room);
    }

    public List<Room> getRoomsSortedByNumber() {
        return sortedRooms(new Comparator<Room>() {
            @Override
            public int compare(Room first, Room second) {
                return Integer.compare(first.getRoomNumber(), second.getRoomNumber());
            }
        });
    }

    public List<Room> getRoomsSortedByPrice() {
        return sortedRooms(new Comparator<Room>() {
            @Override
            public int compare(Room first, Room second) {
                return Double.compare(first.getNightlyRate(), second.getNightlyRate());
            }
        });
    }

    private List<Room> sortedRooms(Comparator<Room> comparator) {
        List<Room> result = new ArrayList<Room>(rooms);
        Collections.sort(result, comparator);
        return result;
    }

    public void checkIn(int roomNumber, String guestName) {
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new IllegalArgumentException("Guest name must not be blank.");
        }
        Room room = requireRoom(roomNumber);
        if (!room.isAvailable()) {
            throw new IllegalArgumentException("That room is already occupied.");
        }
        room.checkIn(guestName.trim());
    }

    public void checkOut(int roomNumber) {
        Room room = requireRoom(roomNumber);
        if (room.isAvailable()) {
            throw new IllegalArgumentException("That room is already available.");
        }
        room.checkOut();
    }

    private Room requireRoom(int roomNumber) {
        Room room = findRoom(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("No room has that number.");
        }
        return room;
    }

    private Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
}
