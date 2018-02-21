package com.ema.ema.model;

import java.util.List;

public class Employee {

    private String name;
    private String designation;
    private String id;
    private int age;
    private List<Project> lookup;

    public Employee() {
    }

    public Employee(String name, String designation, String id, int age) {
        this.designation = designation;
        this.name = name;
        this.id = id;
        this.age = age;
    }



    public String getId() {
        return id;
    }

    public  int getAge(){
        return age;
    }

    public List<Project> getLookup() {
        return lookup;
    }

    public void setLookup(List<Project> lookup) {
        this.lookup = lookup;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesignation() {
        return designation;
    }

    public String getName() {
        return name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}