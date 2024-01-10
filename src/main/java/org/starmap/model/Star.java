package org.starmap.model;

public class Star {
    private String name;
    private double xPosition;
    private double yPosition;
    private double brightness;
    private boolean visible = true;


    public Star(String name, double xPosition, double yPosition, double brightness) {
        this.name = name;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.brightness = brightness;
    }

    public String getName() {
        return name;
    }

    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public double getBrightness() {
        return brightness;
    }

    public void setName(String newName) {
        this.name =newName;
    }

    public void setXPosition(double newX) {
        this.xPosition=newX;
    }

    public void setYPosition(double newY) {this.yPosition=newY;}

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }
}
