package com.smarthome.energy.publisher;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appliance {
	
	private String applianceId;
	private double energyLimit;
	private boolean isOn;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private double energConsumed;
	private double workingTime;
	
	private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public Appliance(String applianceId, double energyLimit) {
		this.applianceId = applianceId;
		this.energyLimit = energyLimit; 
		this.isOn = false;
		this.energConsumed = 0.0;
		this.workingTime = 0.0;
	}
	
	public String getApplianceId() {
		return applianceId;
	}
	
	public double getEnergyLimit() {
		return energyLimit;
	}
	
	public boolean isOn() {
		return isOn;
	}
	
	
	public double getWorkingTime() {
		return workingTime;
	}
	
	public void setWorkingTime(double workingTime) {
		this.workingTime = workingTime;
	}
	
	public double getEnergConsumed() {
		return energConsumed;
	}
	
	public void setEnergyConsumed(double energConsumed) {
		this.energConsumed = energConsumed;
	}
	
	public String getStartTime() {
		return startTime != null ? startTime.format(format) : "N/A";
	}
	
	public String getEndTime() {
		return endTime != null ? endTime.format(format) : "N/A";
	}
	
	
	public void turnOn() {
		if(!isOn) {
			this.isOn = true;
			this.startTime = LocalDateTime.now(); 
			System.out.println("Appliance "+ applianceId + " turned On at "+ startTime.format(format));
		}else {
			System.out.println("Appliance "+ applianceId + " is already On ");
		}
	}
	
	public void turnOff() {
		if(isOn) {
			this.isOn = false;
			this.endTime = LocalDateTime.now(); 
			
			System.out.println("Appliance "+ applianceId + " turned Off at "+ endTime.format(format));
			
		}else {
			System.out.println("Appliance "+ applianceId + " is already Off ");
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
