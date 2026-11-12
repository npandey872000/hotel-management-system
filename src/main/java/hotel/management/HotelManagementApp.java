package hotel.management;

import java.util.List;
import java.util.Scanner;

/** A text menu for the hotel management example. */
public final class HotelManagementApp {
    private final Hotel hotel = new Hotel();
    private final Scanner scanner = new Scanner(System.in);

    private HotelManagementApp() {
        hotel.addRoom(new Room(101, "Single", 75.00));
        hotel.addRoom(new Room(102, "Double", 110.00));
        hotel.addRoom(new Room(201, "Suite", 180.00));
    }

    public static void main(String[] args) {
        new HotelManagementApp().run();
    }

    private void run() {
        boolean running = true;
        System.out.println("Hotel Management System");
        while (running) {
            showMenu();
            String choice = read("Choose an option: ");
            try {
                switch (choice) {
                    case "1": showRooms(hotel.getRoomsSortedByNumber(), "room number"); break;
                    case "2": showRooms(hotel.getRoomsSortedByPrice(), "nightly price"); break;
                    case "3": checkInGuest(); break;
                    case "4": checkOutGuest(); break;
                    case "0": running = false; break;
                    default: System.out.println("Please choose a listed option.");
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Could not complete that action: " + exception.getMessage());
            }
            System.out.println();
        }
        System.out.println("Goodbye!");
    }

    private void showMenu() {
        System.out.println("1. View rooms by room number");
        System.out.println("2. View rooms by nightly price");
        System.out.println("3. Check in a guest");
        System.out.println("4. Check out a guest");
        System.out.println("0. Exit");
    }

    private void checkInGuest() {
        int roomNumber = readRoomNumber();
        String guestName = read("Guest name: ");
        hotel.checkIn(roomNumber, guestName);
        System.out.println("Guest checked in to room " + roomNumber + ".");
    }

    private void checkOutGuest() {
        int roomNumber = readRoomNumber();
        hotel.checkOut(roomNumber);
        System.out.println("Room " + roomNumber + " is now available.");
    }

    private int readRoomNumber() {
        String value = read("Room number: ");
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Room number must be a whole number.");
        }
    }

    private void showRooms(List<Room> rooms, String sortedBy) {
        System.out.println("Rooms sorted by " + sortedBy + ":");
        for (Room room : rooms) {
            String status = room.isAvailable()
                    ? "Available" : "Occupied by " + room.getGuestName();
            System.out.printf("Room %d — %s — $%.2f per night — %s%n",
                    room.getRoomNumber(), room.getRoomType(), room.getNightlyRate(), status);
        }
    }

    private String read(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
