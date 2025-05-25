/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;


import javax.swing.JOptionPane;




public class QuickChat {
   
public static Messages task1 = new Messages(); 
   
    public static void main(String[] args) {

        login user1 = new login(); // Create an instance of the Login class
        String user = "";
        String password = "";
        String firstName = "";
        String lastName = "";

        // User registration loop
        while (user1.checkUserName(user) == false || user1.checkPasswordComplexity(password) == false) {
            JOptionPane.showMessageDialog(null, "Please fill in the following inputs to Register: ");
            user = JOptionPane.showInputDialog("Enter the username:");
            password = JOptionPane.showInputDialog("Enter the password:");
            firstName = JOptionPane.showInputDialog("Enter your First Name:");
            lastName = JOptionPane.showInputDialog("Enter your Last Name:");

            user1.registerUser(firstName, lastName, user, password);
        }

        boolean checkLoginDetails = false;
        // User login loop
        while (!checkLoginDetails) {
            JOptionPane.showMessageDialog(null, "Please fill in the following inputs to Login: ");
            user = JOptionPane.showInputDialog("Enter the username:");
            password = JOptionPane.showInputDialog("Enter the password:");

            if (user1.loginUser(user, password)) {
                checkLoginDetails = true;
                System.out.println("here");
                JOptionPane.showMessageDialog(null, user1.returnLoginStatus(user, password, firstName, lastName));
       
     
 
             
                int taskOptions = 0;
                JOptionPane.showMessageDialog(null, "Welcome to QuickChat");

                // Task management loop
                while (taskOptions != 3) {
                    taskOptions = Integer.parseInt(JOptionPane.showInputDialog("Task Options:\nOption 1) Send Messages\nOption 2) Show Recently Sent Messages\nOption 3) Quit\n"));

                    switch (taskOptions) {
                        case 1:
    int numberOfMessages = Integer.parseInt(JOptionPane.showInputDialog("Enter number of Messages to add: "));
    for (int i = 0; i < numberOfMessages; i++) {
        String messageID = task1.generateMessageID();
        while (!task1.checkMessageID(messageID)) {
            messageID = task1.generateMessageID();
        }

        int currentMsgCount = task1.returnTotalMessages();

        String recipient;
        do {
            recipient = JOptionPane.showInputDialog("Enter recipient cell number (must start with +27 and be 9 digits after +27):");
        } while (task1.checkRecipientCell(recipient) != 1);

        String messageContent = JOptionPane.showInputDialog("Enter your message (Max 250 characters):");
        if (messageContent.length() > 250) {
            JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters.");
            i--; // Retry this message
            continue;
        } else {
            JOptionPane.showMessageDialog(null, "Message Sent");
        }

        String messageHash = task1.createMessageHash(messageID, currentMsgCount, messageContent);
        String userChoice = task1.SentMessage();

        if (userChoice.equals("Store Message to send later")) {
            task1.storedMessage(messageID, recipient, messageContent, messageHash);
        }

        if (!userChoice.equals("Disregard Message")) {
            task1.addMessage(messageID, recipient, messageContent, messageHash);
        }

        JOptionPane.showMessageDialog(null,
            "Message Details:\n" +
            "Message ID: " + messageID + "\n" +
            "Message Hash: " + messageHash + "\n" +
            "Recipient: " + recipient + "\n" +
            "Message: " + messageContent
        );
    }

    JOptionPane.showMessageDialog(null, "Total Messages Sent: " + task1.returnTotalMessages());
    break;


                           
                        case 2:
                            // Option 2: Show Report
                            String output = "Coming Soon";
           
                            JOptionPane.showMessageDialog(null, output);

                            break;
                     
                        case 3:
                            // Option 3: Quit
                            JOptionPane.showMessageDialog(null, "Quitting QuickChat");
                            break;

                        default:
                            // Default case for invalid options
                            JOptionPane.showMessageDialog(null, "Invalid Option");

                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, user1.returnLoginStatus(user, password, firstName, lastName));
            }

        }

    }

}