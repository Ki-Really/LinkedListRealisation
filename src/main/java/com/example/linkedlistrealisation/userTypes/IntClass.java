package com.example.linkedlistrealisation.userTypes;

import com.example.linkedlistrealisation.comparators.IntComparator;
import com.example.linkedlistrealisation.interfaces.UserTypeInterface;
import com.example.linkedlistrealisation.models.Point;

import java.io.InputStreamReader;
import java.util.Comparator;

public class IntClass implements UserTypeInterface {
    @Override
    public String typeName() {
        return "Integer";
    }

    @Override
    public Object create() {
        return (int)(Math.random()*100)-50;
    }

    @Override
    public String readValueSer(Object intValue) {
        return  intValue.toString();
    }

    @Override
    public Integer parseValueDeser(String str) {

        return Integer.parseInt(str);
    }

    @Override
    public IntComparator getTypeComparator() {
        return new IntComparator();
    }

}
