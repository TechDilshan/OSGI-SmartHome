package com.smarthome.energygrid.publisher;

public class GridEnergyUsage {
	
	private double importGridEnergy;
    private double exportGridEnergy;

    public GridEnergyUsage() {
        this.importGridEnergy = 0.0;
        this.exportGridEnergy = 0.0;
    }
    
    public void importFromGrid(double energy) {
        if (energy < 0) {
            System.out.println("Error: Energy value cannot be  negative.");
            return;
        }
        importGridEnergy += energy;
        System.out.println("Imported " + energy + " kWh from grid. Total imported: " + importGridEnergy + " kWh");
    }

    public void exportToGrid(double energy) {
        if (energy < 0) {
            System.out.println("Error: Energy value cannot be negative.");
            return;
        }
        exportGridEnergy += energy;
        System.out.println("Exported " + energy + " kWh to grid. Total exported: " + exportGridEnergy + " kWh");
    }
    
    public double getImportGridEnergy() {
        return importGridEnergy;
    }

    public double getExportGridEnergy() {
        return exportGridEnergy;
    }

}
