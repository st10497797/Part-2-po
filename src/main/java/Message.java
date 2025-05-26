/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Donnell
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Message {

    private String recipient;
    private String messageText;
    private String messageID;
    private String hash;

    public Message(String recipient, String messageText, int count) {
        if (!checkRecipientCell(recipient)) {
            throw new IllegalArgumentException("Invalid recipient phone number.");
        }

        if (messageText.length() > 250) {
            throw new IllegalArgumentException("Message must not exceed 250 characters.");
        }

        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID(count);
        this.hash = generateMessageHash();
    }

    public static boolean checkRecipientCell(String phone) {
        return phone != null && phone.matches("^\\+\\d{1,10}$");
    }

    private String generateMessageID(int count) {
        return "MSG" + String.format("%03d", count) + "-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }

    public String generateMessageHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String combined = recipient + messageText + messageID;
            byte[] hashBytes = digest.digest(combined.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return "HASH_ERROR";
        }
    }

    public String displayMessageDetails() {
        return "To: " + recipient + "\nMessage: " + messageText + "\nID: " + messageID + "\nHash: " + hash;
    }

    // Getters if needed
    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getHash() {
        return hash;
    }
}
