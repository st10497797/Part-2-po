/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Donnell
 */
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

public class QuickChat {
    
// List to hold all sent messages
    private static ArrayList<Message> sentMessages = new ArrayList<>();
   // Counter for the total number of sent messages
    private static int totalMessagesSent = 0;

    public static void main(String[] args) {

        // Capture user's first and last name
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");

        // User registration: username must meet criteria
        String username;
        while (true) {
            username = JOptionPane.showInputDialog("Enter your username:");
            if (LoginPart2.checkUsername(username)) {
                JOptionPane.showMessageDialog(null, "Username successfully created.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Username must contain an underscore and be no more than 5 characters.");
            }
        }

        // User registration: password must meet complexity requirements
        String password;
        while (true) {
            password = JOptionPane.showInputDialog("Enter your password:");
            if (LoginPart2.checkPasswordComplexity(password)) {
                JOptionPane.showMessageDialog(null, "Password successfully captured.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Password must have at least 8 characters, a capital letter, a number, and a special character.");
            }
        }

        // User registration: phone number validation
        String phoneNumber;
        while (true) {
            phoneNumber = JOptionPane.showInputDialog("Enter your phone number (start with +27):");
            if (LoginPart2.checkCellNumber(phoneNumber)) {
                JOptionPane.showMessageDialog(null, "Phone number successfully added.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Phone number must start with +27 and be valid.");
            }
        }

        // Final registration confirmation
        JOptionPane.showMessageDialog(null, LoginPart2.registerUser(username, password));

        // Login section
        JOptionPane.showMessageDialog(null, "---LOGIN SECTION---");

        // Keep prompting for login until correct credentials are entered
        while (true) {
            String enteredUsername = JOptionPane.showInputDialog("Login - enter your username:");
            String enteredPassword = JOptionPane.showInputDialog("Login - enter your password:");

            if (LoginPart2.loginUser(username, password, enteredUsername, enteredPassword)) {
                JOptionPane.showMessageDialog(null, LoginPart2.returnLoginStatus(username, password, enteredUsername, enteredPassword, firstName, lastName));
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Login failed: username or password incorrect. Please try again.");
            }
        }

        // Greet user after successful login
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        // Ask user how many messages they would like to send
        int messageLimit = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to send?"));

         // Main menu loop
        while (true) {
            String menu = "Choose an option:\n1) Send Message\n2) Show recently sent messages\n3) Quit";
            int choice = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (choice) {
                case 1 -> {
                    // Prevent user from sending more than the allowed number of messages
                    if (totalMessagesSent >= messageLimit) {
                        JOptionPane.showMessageDialog(null, "Message limit reached.");
                        break;
                    }

                    // Get and validate recipient's phone number
                    String recipient = JOptionPane.showInputDialog("Enter recipient's phone number (+ and up to 10 digits):");
                    while (!Message.checkRecipientCell(recipient)) {
                        recipient = JOptionPane.showInputDialog("Invalid recipient. Must start with '+' and be up to 11 characters. Try again:");
                    }

                    // Get message text
                    String text = JOptionPane.showInputDialog("Enter your message text:");

                     // Show message action options
                    String[] options = {"Send", "Store to Json", "Disregard"};
                    int action = JOptionPane.showOptionDialog(null, "Choose what to do with the message: ",
                            "Message Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                            null, options, options[0]);

                    // If message is disregarded or dialog closed
                    if (action == 2 || action == JOptionPane.CLOSED_OPTION) {
                        JOptionPane.showMessageDialog(null, "Message disregarded.");
                        break;
                    }

                    try {
                        // Create and validate message
                        Message msg = new Message(recipient, text, totalMessagesSent);
                        sentMessages.add(msg);
                        totalMessagesSent++;

                        if (action == 0) { // Send
                            JOptionPane.showMessageDialog(null, "Message successfully sent\n" + msg.displayMessageDetails());

                            // Prompt to delete message after sending
                            String input = JOptionPane.showInputDialog("Press 0 to delete message");
                            if ("0".equals(input)) {
                                sentMessages.remove(msg);
                                totalMessagesSent--;
                                JOptionPane.showMessageDialog(null, "Message deleted.");
                            }

                        } else if (action == 1) { // Store
                            try (FileWriter file = new FileWriter("stored_messages.json", true)) {
                                JSONObject json = new JSONObject();
                                json.put("Recipient", recipient);
                                json.put("Message", text);
                                json.put("Hash", msg.generateMessageHash());
                                file.write(json.toString(4) + ",\n");
                                JOptionPane.showMessageDialog(null, "Message successfully stored\n" + msg.displayMessageDetails());
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Failed to store message.");
                            }
                        }

                    } catch (IllegalArgumentException e) {
                        // Handle invalid message input (e.g., over 250 chars)
                        JOptionPane.showMessageDialog(null, "Failed to send message: " + e.getMessage());
                    } catch (Exception e) {
                        // Handle unexpected errors
                        JOptionPane.showMessageDialog(null, "Unexpected error: " + e.getMessage());
                    }
                }

                // Show sent messages (feature not yet implemented)
                case 2 -> JOptionPane.showMessageDialog(null, "Coming Soon.");

                // Exit application
                case 3 -> {
                    JOptionPane.showMessageDialog(null, "Total messages sent: " + totalMessagesSent + "\nExiting app.");
                    System.exit(0);
                }

                default -> JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
            }
        }
    }
}
