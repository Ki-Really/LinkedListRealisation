package com.example.linkedlistrealisation.comparators;

import com.example.linkedlistrealisation.models.Point;

import java.util.Comparator;


public class PointComparator implements Comparator<Point> {

    @Override
    public int compare(Point o1, Point o2) {
        return Double.compare(Math.sqrt((int)Math.pow(o1.getX(),2)+(int)Math.pow(o1.getY(),2)),
                (int)Math.sqrt((int)Math.pow(o2.getX(),2)+(int)Math.pow(o2.getY(),2)));
    }
}
