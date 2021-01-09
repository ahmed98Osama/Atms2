package com.example.atms;


// model class
public class DepartmentDataModel {

    private String departmentName;
    private String departmentHead;

    private String id;

    public DepartmentDataModel() {
    }

    public DepartmentDataModel(String departmentName, String departmentHead, String id) {
        this.departmentName = departmentName;
        this.departmentHead = departmentHead;
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(String departmentHead) {
        this.departmentHead = departmentHead;
    }


}
