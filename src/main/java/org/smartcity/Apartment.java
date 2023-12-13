package org.smartcity;

public class Apartment extends Building {
    private final int residents;

    public Apartment(String address, int floors, int residents) {
        super(address, floors);
        this.residents =residents;
    }
    public int getResidents(){return residents;}
    @Override
    public void operate(){
        System.out.println("Apartament at " + getAddress() + " has a  " + residents  + " residents.");
    }

}
