/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */


/**
 *
 * @author Donnell
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class MessageTest {

    @Test
    public void testValidMessageCreation() {
        // Use a , valid 10 - digit SA number after +27
        Message msg = new Message("+2761234567", "Hello, this is a test message.", 1);

        assertEquals("+2761234567", msg.getRecipient());
        assertEquals("Hello, this is a test message.", msg.getMessageText());
        assertTrue(msg.getMessageID().startsWith("MSG001-"));
        assertNotNull(msg.getHash());
        assertEquals(64, msg.getHash().length()); // SHA-256 hash  length
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRecipientThrowsException() {
        new Message("0612345678", "This should fail", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMessageExceedsCharacterLimit() {
        String longText = new String(new char[251]).replace('\0', 'A'); // 251 characters
        new Message("+2761234567", longText, 1);
    }

    @Test
    public void testCheckRecipientCell_Valid() {
        assertTrue(Message.checkRecipientCell("+2767891234"));
    }

    @Test
    public void testCheckRecipientCell_Invalid() {
        assertFalse(Message.checkRecipientCell("2767891234")); // missing +
        assertFalse(Message.checkRecipientCell("+abc123"));    // letters
        assertFalse(Message.checkRecipientCell(null));         // null case
        assertFalse(Message.checkRecipientCell("+2767891234567")); // too many digits
    }

    @Test
    public void testDisplayMessageDetails() {
        Message msg = new Message("+2761112233", "Test Message", 5);
        String details = msg.displayMessageDetails();

        assertTrue(details.contains("To: +2761112233"));
        assertTrue(details.contains("Message: Test Message"));
        assertTrue(details.contains("ID: MSG005-"));
        assertTrue(details.contains("Hash:"));
    }

    @Test
    public void testHashConsistency() {
        Message msg1 = new Message("+2761112233", "Test Message", 5);
        String hash1 = msg1.generateMessageHash();
        assertEquals(msg1.getHash(), hash1);
    }
}
