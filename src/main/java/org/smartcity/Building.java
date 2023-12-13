package org.smartcity;

public abstract class Building {
    private String address;
    private int floors;

    public Building(String address, int floors) {
        this.address = address;
        this.floors = floors;
    }

    public Building(String address) {
        this.address = address;
    }

    public Building() {

    }

    public abstract void operate();

    public String getAddress() {
        return address;
    }

    public int getFloors() {
        return floors;
    }
    public void setAddres(String address){this.address=address;}
    public void setAddres(int floors){this.floors=floors;}
}