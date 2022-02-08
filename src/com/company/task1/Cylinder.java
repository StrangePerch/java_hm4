package com.company.task1;

public class Cylinder extends SolidOfRevolution {
    public double height;

    public Cylinder(double radius, double height) {
        super(radius);
    }

    @Override
    public double getVolume() {
        volume = Math.PI * Math.pow(getRadius(), 2) * height;
        return volume;
    }
}
