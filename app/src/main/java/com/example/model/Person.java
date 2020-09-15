package com.example.model;

import java.util.jar.Attributes;

public class Person {
    String Name,Phoneno;
    int Age;
    public Person(String Name,int Age,String Phoneno)
    {
        this.Name=Name;
        this.Age=Age;
        this.Phoneno=Phoneno;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getName() {
        return Name;
    }


    public void setAge(int age)
    {
        this.Age=age;
    }

    public int getAge() {
        return Age;
    }

    public void setPhoneno(String phoneno) {
        this.Phoneno = phoneno;
    }
    public String getPhoneno()
    {
        return Phoneno;
    }


}
