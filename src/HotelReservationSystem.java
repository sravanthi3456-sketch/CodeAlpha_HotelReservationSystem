
import javax.swing.*;
import java.awt.*;

public class HotelReservationSystem extends JFrame {

    String guestName;
    String phoneNumber;
    int bookedRoom = 0;

    // ADD GUEST
    void addGuest() {
        guestName = JOptionPane.showInputDialog(this, "Enter Guest Name:");
        if (guestName == null || guestName.trim().isEmpty()) return;

        phoneNumber = JOptionPane.showInputDialog(this, "Enter Phone Number:");
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) return;

        JOptionPane.showMessageDialog(this,
                "Guest Added Successfully!\nName: " + guestName
                + "\nPhone: " + phoneNumber);
    }

    // VIEW ROOMS
    void viewRooms() {
        String rooms = "AVAILABLE HOTEL ROOMS\n\n"
                + "Room 101 - Single - Rs.1000\n"
                + "Room 102 - Single - Rs.1000\n"
                + "Room 201 - Double - Rs.2000\n"
                + "Room 202 - Double - Rs.2000\n"
                + "Room 301 - Deluxe - Rs.3000";

        JOptionPane.showMessageDialog(this, rooms,
                "Available Rooms", JOptionPane.INFORMATION_MESSAGE);
    }

    // BOOK ROOM
    void bookRoom() {
        if (guestName == null || guestName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add guest first!");
            return;
        }

        if (bookedRoom != 0) {
            JOptionPane.showMessageDialog(this, "A room is already booked!");
            return;
        }

        String room = JOptionPane.showInputDialog(this,
                "Enter Room Number (101, 102, 201, 202, 301):");

        if (room == null) return;

        try {
            int roomNumber = Integer.parseInt(room);

            if (roomNumber == 101 || roomNumber == 102
                    || roomNumber == 201 || roomNumber == 202
                    || roomNumber == 301) {

                bookedRoom = roomNumber;

                JOptionPane.showMessageDialog(this,
                        "Room Booked Successfully!\nGuest: " + guestName
                        + "\nRoom Number: " + bookedRoom);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Room Number!");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid number!");
        }
    }

    // SEARCH BOOKING
    void searchBooking() {
        if (bookedRoom == 0) {
            JOptionPane.showMessageDialog(this, "No booking found!");
            return;
        }

        JOptionPane.showMessageDialog(this,
                "BOOKING DETAILS\n\n"
                + "Guest Name: " + guestName
                + "\nPhone: " + phoneNumber
                + "\nRoom Number: " + bookedRoom);
    }

    // CANCEL BOOKING
    void cancelBooking() {
        if (bookedRoom == 0) {
            JOptionPane.showMessageDialog(this, "No booking to cancel!");
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to cancel booking?",
                "Cancel Booking",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            bookedRoom = 0;
            guestName = null;
            phoneNumber = null;

            JOptionPane.showMessageDialog(this,
                    "Booking cancelled successfully!");
        }
    }

    // CHECK OUT
    void checkOut() {
        if (bookedRoom == 0) {
            JOptionPane.showMessageDialog(this, "No active booking!");
            return;
        }

        bookedRoom = 0;
        guestName = null;
        phoneNumber = null;

        JOptionPane.showMessageDialog(this,
                "Guest checked out successfully!");
    }

    // DASHBOARD
    HotelReservationSystem() {
        setTitle("Hotel Reservation System");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel(
                "HOTEL RESERVATION SYSTEM", SwingConstants.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBackground(new Color(30, 64, 100));
        title.setOpaque(true);
        title.setBorder(BorderFactory.createEmptyBorder(25, 10, 25, 10));

        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(3, 2, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 80, 50, 80));

        String[] features = {
            "ADD GUEST", "VIEW ROOMS", "BOOK ROOM",
            "SEARCH BOOKING", "CANCEL BOOKING", "CHECK OUT"
        };

        for (String feature : features) {
            JButton button = new JButton(feature);
            button.setFont(new Font("Arial", Font.BOLD, 18));

            if (feature.equals("ADD GUEST")) {
                button.addActionListener(e -> addGuest());
            } else if (feature.equals("VIEW ROOMS")) {
                button.addActionListener(e -> viewRooms());
            } else if (feature.equals("BOOK ROOM")) {
                button.addActionListener(e -> bookRoom());
            } else if (feature.equals("SEARCH BOOKING")) {
                button.addActionListener(e -> searchBooking());
            } else if (feature.equals("CANCEL BOOKING")) {
                button.addActionListener(e -> cancelBooking());
            } else if (feature.equals("CHECK OUT")) {
                button.addActionListener(e -> checkOut());
            }

            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new HotelReservationSystem());
    }
}