package org.smartcity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CitySimulation {
    public static void main(String[] args) {
        SmartCity city = new SmartCity();
        List<Building> buildings = new ArrayList<>();

        // Symulacja trwa przez 10 "dni"
        for (int day = 1; day <= 10; day++) {
            System.out.println("Day " + day + " in Smart City");
            Random random1 = new Random();
            int buildingOrnot = random1.nextInt(2);
            if(buildingOrnot==0){
                Building newBuilding = createRandomBuilding(day);
                System.out.println("New building added: " + newBuilding.getAddress());
                buildings.add(newBuilding);
            }
            for (Building building : buildings) {
                new Thread(() -> {
                    building.operate();
                }).start();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static Building createRandomBuilding(int day) {
        Random random = new Random();
        int buildingType = random.nextInt(3);
        String address = "Building " + day + "-" + (random.nextInt(100) + 1);

        switch (buildingType) {
            case 0:
                return new Apartment(address, random.nextInt(10) + 5, random.nextInt(100) + 1);
            case 1:
                return new Office(address, random.nextInt(20) + 10, random.nextInt(300) + 50);
            case 2:
                return new Shop(address, random.nextInt(5) + 1, "Type " + (random.nextInt(3) + 1));
            default:
                return new Apartment(address, random.nextInt(10) + 5, random.nextInt(100) + 1);
        }
    }
}
