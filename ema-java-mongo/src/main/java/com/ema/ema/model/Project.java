package com.ema.ema.model;

import org.bson.types.ObjectId;

public class Project {


    private String name;
    private String type;
    private ObjectId employeeId;

    public Project(){

    }

    public Project(String name, String type, ObjectId employeeId) {
        this.name = name;
        this.type = type;
        this.employeeId = employeeId;
    }

    public ObjectId getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(ObjectId employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

}
