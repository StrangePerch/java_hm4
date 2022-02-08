package com.company.task1;

public class Box extends Shape {
    public double volumeLeft;

    public Box(double volume) {
        this.volume = volume;
        volumeLeft = volume;
    }

    @Override
    public double getVolume() {
        return volume;
    }

    public boolean add(Shape shape) {
        volumeLeft -= shape.getVolume();
        return volumeLeft >= 0;
    }
}
