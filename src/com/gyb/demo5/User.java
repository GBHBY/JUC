package com.gyb.demo5;

/**
 * @author gb
 * @version 1.0
 * description:
 * @date 2020/11/28 0:18
 */

public class User {
    private int age;
    private String name;
    private int id;

    public User(int age, String name, int id) {
        this.age = age;
        this.name = name;
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
