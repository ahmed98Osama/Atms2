package com.example.atms;

public class EmployeeDataModel {

    private String email;
    private String id;
    private String location;
    private String name;
    private String pass;
    private String  phone;
    private String uri;
    private String department;
    private String available;



    public EmployeeDataModel() {
    }

    public EmployeeDataModel(String email, String id, String location, String name, String pass,
                             String phone, String uri, String department, String available) {
        this.email = email;
        this.id = id;
        this.location = location;
        this.name = name;
        this.pass = pass;
        this.phone = phone;
        this.uri = uri;
        this.department = department;
        this.available = available;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
