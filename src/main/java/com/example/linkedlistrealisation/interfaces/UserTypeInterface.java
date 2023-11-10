package com.example.linkedlistrealisation.interfaces;

import java.io.InputStreamReader;
import java.util.Comparator;

public interface UserTypeInterface{
    String typeName();
     Object create();

     String readValueSer(Object obj);
     Object parseValueDeser(String ss);

     Comparator getTypeComparator();
}
