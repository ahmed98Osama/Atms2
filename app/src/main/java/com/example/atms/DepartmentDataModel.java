package com.example.atms;


// model class
public class DepartmentDataModel {

    private String departmentName;
    private String departmentHead;

    public DepartmentDataModel() {
    }

    public DepartmentDataModel(String departmentName, String departmentHead) {
        this.departmentName = departmentName;
        this.departmentHead = departmentHead;
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
