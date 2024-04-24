package com.example.linkedlistrealisation.userTypes;

import com.example.linkedlistrealisation.comparators.PointComparator;
import com.example.linkedlistrealisation.interfaces.UserTypeInterface;
import com.example.linkedlistrealisation.models.Point;


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
    public String readValueSer(Object point) {
        Point pointToSer = (Point)point;
        return pointToSer.toString();
    }

    @Override
    public Object parseValueDeser(String str) {
        int len = str.length();
        String substr = str.substring(1,len-1);

        String[] parameterOfPoint = substr.split(", ");
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
