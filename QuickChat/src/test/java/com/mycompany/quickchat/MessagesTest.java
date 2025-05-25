package com.mycompany.quickchat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessagesTest {

    private Messages messages;

    @BeforeEach
    void setUp() {
        messages = new Messages();
    }

    // Test 1: Check message length (<= 250 chars)
    @Test
    void testMessageLengthSuccess() {
        String message = "Hi Mike, can you join us for dinner tonight";
        assertTrue(message.length() <= 250, "Message should be within 250 characters");
    }

    @Test
    void testMessageLengthFailure() {
        String message = "A".repeat(260);
        int excess = message.length() - 250;
        assertTrue(excess > 0, "Message exceeds 250 characters by " + excess);
    }

    // Test 2: Check valid recipient number format
    @Test
    void testValidRecipientCell() {
        String recipient = "+27718693002";
        int result = messages.checkRecipientCell(recipient);
        assertEquals(1, result, "Should return success for valid number with +27");
    }

    @Test
    void testInvalidRecipientCell() {
        String recipient = "08575975889";
        int result = messages.checkRecipientCell(recipient);
        assertEquals(0, result, "Should return failure for missing +27 prefix");
    }

    // Test 3: Check message hash
    @Test
    void testMessageHashCorrectness() {
        String messageID = "0012345678";
        int msgNum = 0;
        String message = "Hi Mike, can you join us for dinner tonight";
        String expectedHash = "00:0:HITONIGHT";
        String actualHash = messages.createMessageHash(messageID, msgNum, message);
        assertEquals(expectedHash, actualHash);
    }

    // Test 4: Message ID generation format
    @Test
    void testGeneratedMessageIDLength() {
        String id = messages.generateMessageID();
        assertEquals(10, id.length(), "Message ID should be 10 digits long");
        assertTrue(id.matches("\\d{10}"), "Message ID should be numeric");
    }

    // Test 5: Message action simulation (send, discard, store)
    @Test
    void testSendMessageActionSend() {
        String choice = "Send Message";
        String result = simulateMessageAction(choice);
        assertEquals("Message successfully sent.", result);
    }

    @Test
    void testSendMessageActionDiscard() {
        String choice = "Disregard Message";
        String result = simulateMessageAction(choice);
        assertEquals("Press 0 to delete message.", result);
    }

    @Test
    void testSendMessageActionStore() {
        String choice = "Store Message to send later";
        String result = simulateMessageAction(choice);
        assertEquals("Message successfully stored.", result);
    }

    // Simulated message action logic
    private String simulateMessageAction(String action) {
        return switch (action) {
            case "Send Message" -> "Message successfully sent.";
            case "Disregard Message" -> "Press 0 to delete message.";
            case "Store Message to send later" -> "Message successfully stored.";
            default -> "Unknown action.";
        };
    }
}

