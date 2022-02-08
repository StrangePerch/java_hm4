package com.company.task1;

public class Ball extends SolidOfRevolution {

    public Ball(double radius) {
        super(radius);
    }


    @Override
    public double getVolume() {
        volume = 4 / 3.f * Math.PI * Math.pow(getRadius(), 3);
        return volume;
    }
}
