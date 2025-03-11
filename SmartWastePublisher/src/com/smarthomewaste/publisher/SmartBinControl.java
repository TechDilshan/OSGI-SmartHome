package com.smarthomewaste.publisher;

public interface SmartBinControl {
    String checkWasteType(String binId);
    void displayAllBinStatuses();
    boolean isBinFull(String binId);
    int addWasteToBin(String binId, int wasteAmount);
    String getBinCapacity(String binId);
}
