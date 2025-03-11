package com.smarthome.energygrid.publisher;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class SolarEnergyProducer {

	private String GeneratedSolarEnergy;
	private double solarEnergyGenerated;
    private LocalDateTime lastUpdate;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public SolarEnergyProducer() {
        this.solarEnergyGenerated = 0.0;
        this.lastUpdate = LocalDateTime.now();
    }
    
    public void generateEnergy() {
        Random rand = new Random();
        double energyProduced = rand.nextDouble() * 5; // generate energy 0 to 5 kWh
        solarEnergyGenerated += energyProduced;
        
        DecimalFormat df = new DecimalFormat("#.##"); //convert value 2 decimal
      
        
        lastUpdate = LocalDateTime.now();
        System.out.println("Solar Energy Generated: " + df.format(energyProduced) + " kWh at " + lastUpdate.format(format));
    }

    public String getTotalGeneratedEnergy() {
    	 DecimalFormat df = new DecimalFormat("#.##");
    	 GeneratedSolarEnergy = df.format(solarEnergyGenerated);
    	
        return GeneratedSolarEnergy;
    }
    
    public String getLastUpdate() {
        return lastUpdate.format(format);
    }
    
}
