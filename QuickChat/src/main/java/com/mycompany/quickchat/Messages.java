/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;




import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class Messages {

    private int totalMessages = 0;
    private ArrayList<String> sentMessages = new ArrayList<>();

    public boolean checkMessageID(String messageID) {
        return messageID.length() == 10;
    }

    public int checkRecipientCell(String cell) {
        return (cell.startsWith("+27") && cell.length() == 12) ? 1 : 0;
    }

    public String generateMessageID() {
        Random rand = new Random();
        long num = (long)(rand.nextDouble() * 1_000_000_0000L);
        return String.format("%010d", num);
    }

    public String createMessageHash(String messageID, int msgNum, String message) {
        String[] words = message.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        String hash = messageID.substring(0, 2) + ":" + msgNum + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    public String SentMessage() {
        String[] options = {"Send Message", "Disregard Message", "Store Message to send later"};
        int choice = JOptionPane.showOptionDialog(null, "Choose action for message:",
                "Message Action",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        return options[choice];
    }

    public void addMessage(String messageID, String recipient, String message, String hash) {
        totalMessages++;
        sentMessages.add("Message ID: " + messageID + "\nMessage Hash: " + hash +
                         "\nRecipient: " + recipient + "\nMessage: " + message);
    }

    public String printMessages() {
        return String.join("\n\n", sentMessages);
    }

    public int returnTotalMessages() {
        return totalMessages;
    }

    @SuppressWarnings("unchecked")
    public void storedMessage(String messageID, String recipient, String message, String hash) {
        JSONObject msg = new JSONObject();
        msg.put("MessageID", messageID);
        msg.put("Recipient", recipient);
        msg.put("Message", message);
        msg.put("MessageHash", hash);

        try (FileWriter file = new FileWriter("stored_messages.json", true)) {
            file.write(msg.toJSONString() + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error storing message: " + e.getMessage());
        }
    }
}
