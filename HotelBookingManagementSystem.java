import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * WEEK 4 CAPSTONE PROJECT - Option 3
 * Hotel Booking Management System
 * ---------------------------------------------
 * Features: Add/Cancel bookings, calculate total cost.
 * Data stored using ArrayList in memory, persisted to a local file
 * (bookings.txt) so records survive between runs.
 */

// ---------- MODEL ----------
class Booking {
    int bookingId;
    String guestName;
    String roomType;
    int nights;
    double totalCost;

    Booking(int bookingId, String guestName, String roomType, int nights, double totalCost) {
        this.bookingId = bookingId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.nights = nights;
        this.totalCost = totalCost;
    }

    String toFileFormat() {
        return bookingId + "," + guestName + "," + roomType + "," + nights + "," + totalCost;
    }

    static Booking fromFileFormat(String line) {
        String[] parts = line.split(",");
        return new Booking(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                Integer.parseInt(parts[3]),
                Double.parseDouble(parts[4])
        );
    }

    void display() {
        System.out.println("Booking ID: " + bookingId + " | Guest: " + guestName +
                " | Room: " + roomType + " | Nights: " + nights + " | Total Cost: " + totalCost);
    }
}

// ---------- SERVICE (OOP + Collections + File Handling) ----------
class HotelBookingManager {
    private static final String FILE_NAME = "bookings.txt";
    private ArrayList<Booking> bookings = new ArrayList<>();

    // Room prices per night
    private static final double SINGLE_ROOM_PRICE = 1500;
    private static final double DOUBLE_ROOM_PRICE = 2500;
    private static final double SUITE_PRICE = 5000;

    HotelBookingManager() {
        loadBookings();
    }

    private void loadBookings() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    bookings.add(Booking.fromFileFormat(line));
                }
            }
            System.out.println("Loaded " + bookings.size() + " booking(s) from file.\n");
        } catch (IOException e) {
            System.out.println("Error loading bookings: " + e.getMessage());
        }
    }

    private void saveBookings() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Booking b : bookings) {
                writer.write(b.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving bookings: " + e.getMessage());
        }
    }

    private double getRoomPrice(String roomType) {
        switch (roomType.toLowerCase()) {
            case "single": return SINGLE_ROOM_PRICE;
            case "double": return DOUBLE_ROOM_PRICE;
            case "suite": return SUITE_PRICE;
            default: return -1;
        }
    }

    void addBooking(int id, String guestName, String roomType, int nights) {
        double pricePerNight = getRoomPrice(roomType);
        if (pricePerNight == -1) {
            System.out.println("Invalid room type! Choose Single, Double, or Suite.");
            return;
        }
        double totalCost = pricePerNight * nights;
        bookings.add(new Booking(id, guestName, roomType, nights, totalCost));
        saveBookings();
        System.out.println("Booking added successfully! Total Cost: " + totalCost);
    }

    void cancelBooking(int id) {
        boolean removed = bookings.removeIf(b -> b.bookingId == id);
        if (removed) {
            saveBookings();
            System.out.println("Booking cancelled successfully!");
        } else {
            System.out.println("Booking ID " + id + " not found.");
        }
    }

    void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        System.out.println("\n----- All Bookings -----");
        double grandTotal = 0;
        for (Booking b : bookings) {
            b.display();
            grandTotal += b.totalCost;
        }
        System.out.println("Grand Total Revenue: " + grandTotal);
    }
}

// ---------- MAIN / VIEW (Menu) ----------
public class HotelBookingManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HotelBookingManager manager = new HotelBookingManager();
        int choice;

        do {
            System.out.println("===== Hotel Booking Management System =====");
            System.out.println("1. Add Booking");
            System.out.println("2. Cancel Booking");
            System.out.println("3. View All Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Booking ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter Guest Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Room Type (Single/Double/Suite): ");
                        String roomType = sc.nextLine();
                        System.out.print("Enter Number of Nights: ");
                        int nights = Integer.parseInt(sc.nextLine());

                        if (nights <= 0) {
                            System.out.println("Nights must be positive.");
                        } else {
                            manager.addBooking(id, name, roomType, nights);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid numeric input!");
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter Booking ID to cancel: ");
                        int id = Integer.parseInt(sc.nextLine());
                        manager.cancelBooking(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Booking ID!");
                    }
                    break;

                case 3:
                    manager.viewBookings();
                    break;

                case 4:
                    System.out.println("Exiting Hotel Booking System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please select 1-4.");
            }

        } while (choice != 4);

        sc.close();
    }
}
