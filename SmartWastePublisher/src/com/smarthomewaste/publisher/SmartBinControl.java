package com.smarthomewaste.publisher;

public interface SmartBinControl {

//    int checkFillLevel(String binId);
    String checkWasteType(String binId);
//    void sendNotification(String binId, String message);
//    void sortWaste(String binId, String wasteType);
//    String getWasteReductionTips(String binId);
    void displayAllBinStatuses();
//    void resetBin(String binId);
//    void schedulePickup(String binId);
	boolean isBinFull(String binId);
	int addWasteToBin(String binId, int wasteAmount);
	String getBinCapacity(String binId);

}