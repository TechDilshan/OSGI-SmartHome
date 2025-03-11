package com.smartthermostat.subscriber;

import com.smartthermostat.publisher.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SmartThermostatUI extends JFrame {
    private ThermostatControl thermostatControl;
    private JTextField roomField, tempField;
    private JTextArea statusArea;

    public SmartThermostatUI(ThermostatControl thermostatControl) {
        this.thermostatControl = thermostatControl;

        setTitle("Smart Thermostat Control");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel for Room Input
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JLabel roomLabel = new JLabel("Room:");
        roomField = new JTextField(10);
        JButton onButton = new JButton("Turn ON");
        JButton offButton = new JButton("Turn OFF");

        topPanel.add(roomLabel);
        topPanel.add(roomField);
        topPanel.add(onButton);
        topPanel.add(offButton);

        // Middle Panel for Temperature Controls
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout());

        JLabel tempLabel = new JLabel("Temperature:");
        tempField = new JTextField(5);
        JButton setTempButton = new JButton("Set Temp");

        middlePanel.add(tempLabel);
        middlePanel.add(tempField);
        middlePanel.add(setTempButton);

        // Bottom Panel for Actions
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JButton statusButton = new JButton("Get Room Status");
        JButton displayAllButton = new JButton("Display All");
        JButton addRoomButton = new JButton("Add Room");
        JButton deleteRoomButton = new JButton("Delete Room");

        bottomPanel.add(statusButton);
        bottomPanel.add(displayAllButton);
        bottomPanel.add(addRoomButton);
        bottomPanel.add(deleteRoomButton);

        // Status Display Area
        statusArea = new JTextArea(10, 40);
        statusArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusArea);

        // Add components to Frame
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.WEST);

        // Button Action Listeners
        onButton.addActionListener(e -> turnOnThermostat());
        offButton.addActionListener(e -> turnOffThermostat());
        setTempButton.addActionListener(e -> setTemperature());
        statusButton.addActionListener(e -> getThermostatStatus());
        displayAllButton.addActionListener(e -> displayAllThermostats());
        addRoomButton.addActionListener(e -> addRoom());
        deleteRoomButton.addActionListener(e -> deleteRoom());

        setVisible(true);
    }

    private void turnOnThermostat() {
        String room = roomField.getText().trim();
        if (!room.isEmpty()) {
            thermostatControl.turnOnThermostat(room);
            statusArea.append("Turned ON thermostat in " + room + "\n");
        } else {
            JOptionPane.showMessageDialog(this, "Enter a room name!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void turnOffThermostat() {
        String room = roomField.getText().trim();
        if (!room.isEmpty()) {
            thermostatControl.turnOffThermostat(room);
            statusArea.append("Turned OFF thermostat in " + room + "\n");
        } else {
            JOptionPane.showMessageDialog(this, "Enter a room name!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setTemperature() {
        String room = roomField.getText().trim();
        String tempStr = tempField.getText().trim();

        if (room.isEmpty() || tempStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter room name and temperature to set temperature!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the thermostat is ON before setting the temperature
        String status = thermostatControl.getThermostatStatus(room);
        if (!status.contains("ON")) {
            JOptionPane.showMessageDialog(this, "Cannot set temperature! Thermostat in " + room + " is OFF.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double temperature = Double.parseDouble(tempStr);
            thermostatControl.setTemperature(room, temperature);
            statusArea.append("Set " + room + " temperature to " + temperature + "°C\n");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid temperature!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getThermostatStatus() {
        String room = roomField.getText().trim();
        if (!room.isEmpty()) {
            String status = thermostatControl.getThermostatStatus(room);
            statusArea.append(status + "\n");
        } else {
            JOptionPane.showMessageDialog(this, "Enter a room name!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayAllThermostats() {
        statusArea.setText(""); // Clear previous data
        StringBuilder sb = new StringBuilder();
//        
//        sb.append("\n----------------------------\n");
//        sb.append("| Room Name     | Status | Temperature (°C) |\n");
//        sb.append("------------------------------\n");

        // Fetch all room statuses dynamically
        for (String room : thermostatControl.getAllRooms()) {  // Using a new method
            String status = thermostatControl.getThermostatStatus(room);
            sb.append(String.format("| %-12s | %-6s | %-14s |\n", room, (status.contains("ON") ? "ON " : "OFF"), status.replaceAll("[^0-9.]", "")));
        }

        sb.append("----------------------------\n");

        statusArea.append(sb.toString()); // Update UI text area
    }

    private void addRoom() {
        String room = roomField.getText().trim();
        if (!room.isEmpty()) {
            thermostatControl.addRoom(room);
            statusArea.append("Added room: " + room + "\n");
        } else {
            JOptionPane.showMessageDialog(this, "Enter a room name!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteRoom() {
        String room = roomField.getText().trim();
        if (!room.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + room + "?", 
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                thermostatControl.deleteRoom(room);
                statusArea.append("Deleted room: " + room + "\n");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Enter a room name!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Simulate the OSGi service injection (Replace with actual OSGi lookup)
    	ThermostatControl thermostatControl = new ThermostatControlImpl();
        new SmartThermostatUI(thermostatControl);
    }
}
