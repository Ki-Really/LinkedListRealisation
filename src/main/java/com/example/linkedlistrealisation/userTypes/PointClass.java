package com.example.linkedlistrealisation.userTypes;

import com.example.linkedlistrealisation.comparators.PointComparator;
import com.example.linkedlistrealisation.interfaces.UserTypeInterface;
import com.example.linkedlistrealisation.models.Point;

import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.Comparator;

public class PointClass implements UserTypeInterface,Cloneable {
    @Override
    public String typeName() {
        return "Point";
    }

    @Override
    public Object create() {
        return new Point((int)(Math.random()*100)-50,(int)(Math.random()*100)-50);
    }

    @Override
    public Object clone() {
        try{
           return (PointClass) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readValueSer(Object point) {
        Point pointToSer = (Point)point;
        return pointToSer.toString();
    }

    @Override
    public Object parseValueDeser(String str) {
        String[] parameterOfPoint = str.split(" ");
            if(parameterOfPoint.length == 2){
                int x = Integer.parseInt(parameterOfPoint[0]);
                int y = Integer.parseInt(parameterOfPoint[1]);
                return new Point(x,y);
            }else{
                return null;
            }
    }

    @Override
    public PointComparator getTypeComparator() {
        return new PointComparator();
    }
}