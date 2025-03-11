package com.smarthomewaste.publisher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BinControl implements SmartBinControl {

    private Map<String, Bin> bins = new HashMap<>();

    public BinControl() {
        bins.put("Paper", new Bin(1, 40, 100, "Paper"));
        bins.put("Organic", new Bin(2, 60, 120, "Organic"));
        bins.put("Metal", new Bin(3, 30, 200, "Metal"));
        bins.put("Plastic", new Bin(4, 50, 150, "Plastic"));
        bins.put("Glass", new Bin(5, 20, 80, "Glass"));
        bins.put("E-waste", new Bin(6, 15, 50, "E-waste"));
    }

    @Override
    public String checkWasteType(String binId) {
        // Check if the bin exists in the map
        Bin bin = bins.get(binId);

        // If the bin is not found, return an appropriate message
        if (bin == null) {
            System.out.println("Bin with ID " + binId + " not found.");
            return "Bin not found"; // Or handle the error as you see fit
        }

        // Print out the bin's waste type
        System.out.println("Checking waste type for bin: " + binId);
        return bin.getWasteType(); // Return the waste type from the Bin object
    }

    // Updated isBinFull method
    @Override
    public boolean isBinFull(String binId) {
        Bin bin = bins.get(binId);
        if (bin == null) {
            return false;  // Bin not found, assuming it isn't full
        }
        return bin.isFull();  // Return if the bin is full based on the current level and capacity
    }

    // Updated addWasteToBin method
    @Override
    public int addWasteToBin(String binId, int wasteAmount) {
        Bin bin = bins.get(binId);
        if (bin != null) {
            if (bin.isFull()) {
                System.out.println("The bin is full. Cannot add more waste.");
                return bin.getLevel();  // Return the current level, no change
            } else {
                int newLevel = bin.getLevel() + wasteAmount;
                if (newLevel > bin.getCapacity()) {
                    newLevel = bin.getCapacity();  // Set to capacity if the amount exceeds the limit
                }
                bin.setLevel(newLevel);  // Update the bin level
                System.out.println("Added " + wasteAmount + " units of waste to " + binId + ". New level: " + newLevel + "/" + bin.getCapacity());
                return newLevel;  // Return the new level of the bin
            }
        } else {
            System.out.println("Bin not found.");
            return -1;  // Bin not found
        }
    }

    // Updated getBinCapacity method
    @Override
    public String getBinCapacity(String binId) {
        Bin bin = bins.get(binId);
        if (bin != null) {
            return String.valueOf(bin.getCapacity());  // Return the capacity as a string
        } else {
            return "Bin not found";  // Bin doesn't exist
        }
    }

    private class Bin {

        private int binId;
        private int level;
        private int capacity; // Total bin capacity
        private String wasteType; // Type of waste (Plastic, Organic, etc.)
        private boolean isFull;

        public Bin(int binId, int level, int capacity, String wasteType) {
            this.binId = binId;
            this.level = level;
            this.capacity = capacity;
            this.wasteType = wasteType;
            this.isFull = level >= capacity; // Automatically set isFull based on level and capacity
        }

        // Getters and setters for all fields
        public int getBinId() { return binId; }
        public void setBinId(int binId) { this.binId = binId; }

        public int getLevel() { return level; }
        public void setLevel(int level) {
            this.level = level;
            this.isFull = level >= capacity; // Update isFull status when level changes
        }

        public int getCapacity() { return capacity; }
        public void setCapacity(int capacity) { this.capacity = capacity; }

        public String getWasteType() { return wasteType; }
        public void setWasteType(String wasteType) { this.wasteType = wasteType; }

        public boolean isFull() { return isFull; }
    }

    @Override
    public void displayAllBinStatuses() {
        // Check if the bins map is empty
        if (bins.isEmpty()) {
            System.out.println("No bins to display.");
            return;
        }

        // Iterate over the bins and display their statuses
        System.out.println("Displaying all bin statuses:");
        System.out.println("+-----------------+---------------------+---------------------+------------------------+");
        System.out.println("| Bin ID          | Waste Type          | Current Level/Capacity | Bin Full Status     |");
        System.out.println("+-----------------+---------------------+---------------------+------------------------+");

        for (Bin bin : bins.values()) {
            String binFullStatus = bin.isFull() ? "Full" : "Not Full";
            System.out.printf("| %-15d | %-19s | %-22s | %-17s |\n",
                bin.getBinId(),
                bin.getWasteType(),
                bin.getLevel() + "/" + bin.getCapacity(),
                binFullStatus
            );
        }

        System.out.println("+-----------------+---------------------+---------------------+------------------------+");
    }

}
