package com.company.task1;

public abstract class SolidOfRevolution extends Shape {
    private double radius;

    public SolidOfRevolution(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
