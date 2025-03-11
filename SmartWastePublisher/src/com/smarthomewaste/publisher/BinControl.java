package com.smarthomewaste.publisher;

import java.util.HashMap;
import java.util.Map;

public class BinControl implements SmartBinControl {

    private Map<String, Bin> bins = new HashMap<>();

    public BinControl() {
        bins.put("Paper", new Bin("Paper", 40, 100, "Paper"));
        bins.put("Organic", new Bin("Organic", 60, 120, "Organic"));
        bins.put("Metal", new Bin("Metal", 30, 200, "Metal"));
        bins.put("Plastic", new Bin("Plastic", 50, 150, "Plastic"));
        bins.put("Glass", new Bin("Glass", 20, 80, "Glass"));
        bins.put("E-waste", new Bin("E-waste", 15, 50, "E-waste"));
    }

    @Override
    public String checkWasteType(String binId) {
        Bin bin = bins.get(binId);
        if (bin == null) {
            System.out.println("Bin with ID " + binId + " not found.");
            return "Bin not found";
        }
        System.out.println("Checking waste type for bin: " + binId);
        return bin.getWasteType();
    }

    @Override
    public boolean isBinFull(String binId) {
        Bin bin = bins.get(binId);
        return bin != null && bin.isFull();
    }

    @Override
    public int addWasteToBin(String binId, int wasteAmount) {
        Bin bin = bins.get(binId);
        if (bin != null) {
            if (bin.isFull()) {
                System.out.println("The bin is full. Cannot add more waste.");
                return bin.getLevel();
            } else {
                int newLevel = bin.getLevel() + wasteAmount;
                if (newLevel > bin.getCapacity()) {
                    newLevel = bin.getCapacity();
                }
                bin.setLevel(newLevel);
                System.out.println("Added " + wasteAmount + " units of waste to " + binId + ". New level: " + newLevel + "/" + bin.getCapacity());
                return newLevel;
            }
        }
        System.out.println("Bin not found.");
        return -1;
    }

    @Override
    public String getBinCapacity(String binId) {
        Bin bin = bins.get(binId);
        if (bin != null) {
            return String.valueOf(bin.getCapacity());
        } else {
            return "Bin not found";
        }
    }

    private class Bin {

        private String binId;  // Bin ID as a string (e.g., Paper, Organic, etc.)
        private int level;
        private int capacity;
        private String wasteType;
        private boolean isFull;

        public Bin(String binId, int level, int capacity, String wasteType) {
            this.binId = binId;
            this.level = level;
            this.capacity = capacity;
            this.wasteType = wasteType;
            this.isFull = level >= capacity;
        }

        public String getBinId() { return binId; }
        public int getLevel() { return level; }
        public void setLevel(int level) {
            this.level = level;
            this.isFull = level >= capacity;
        }

        public int getCapacity() { return capacity; }
        public String getWasteType() { return wasteType; }
        public boolean isFull() { return isFull; }
    }

    @Override
    public void displayAllBinStatuses() {
        if (bins.isEmpty()) {
            System.out.println("No bins to display.");
            return;
        }

        System.out.println("Displaying all bin statuses:");
        System.out.println("+---------------------+------------------------+---------------------+");
        System.out.println("|      Waste Type     |    Current Level       | Bin Full Status     |");
        System.out.println("+---------------------+------------------------+---------------------+");

        for (Bin bin : bins.values()) {
            String binFullStatus = bin.isFull() ? "Full" : "Not Full";
            System.out.printf("| %-19s | %-22s | %-19s |\n",
                bin.getWasteType(),
                bin.getLevel() + "/" + bin.getCapacity(),
                binFullStatus
            );
        }

        System.out.println("+---------------------+------------------------+---------------------+");
    }
}
