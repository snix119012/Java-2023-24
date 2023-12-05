package org.simplestore.util;

import org.simplestore.model.Inventory;
import org.simplestore.model.Product;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InventoryLoader {
    public static void loadInventory(String filePath, Inventory inventory) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                double price = Double.parseDouble(parts[2].trim());
                inventory.addProduct(new Product(id, name, price));
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            System.err.println("File was not found");
        } catch (NumberFormatException e2){
            e2.printStackTrace();
            System.err.println("Can not covert String into other data type");
        } catch (IOException e3){
        e3.printStackTrace();}
    }

}