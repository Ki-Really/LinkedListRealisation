package com.example.linkedlistrealisation;

import com.example.linkedlistrealisation.interfaces.UserTypeInterface;
import com.example.linkedlistrealisation.userTypes.IntClass;
import com.example.linkedlistrealisation.userTypes.PointClass;

import java.util.ArrayList;
import java.util.List;


public class UserFactory {
    private ArrayList<UserTypeInterface> list = new ArrayList<>();

    public UserFactory() {
        this.list.add(new IntClass());
        this.list.add(new PointClass());
    }

    public ArrayList<String> getTypeNameList(){
        List<String> typeNameList = list.stream().map(UserTypeInterface::typeName).toList();
        return new ArrayList<String>(typeNameList);
    }
    public UserTypeInterface getBuilderByName(String name){
        return list.stream().filter(userTypeInterface -> userTypeInterface.typeName().equals(name))
                .findAny().orElse(null);
    }
}