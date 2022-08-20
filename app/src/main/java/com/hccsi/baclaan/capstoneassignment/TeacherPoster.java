package com.hccsi.baclaan.capstoneassignment;

public class TeacherPoster {
    private String name;
    private String idNumber;
    private String password;
    private String status;

    public TeacherPoster() {
    }

    public TeacherPoster(String name, String idNumber, String password, String status) {
        this.name = name;
        this.idNumber = idNumber;
        this.password = password;
        this.status = status;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getidNumber() {
        return idNumber;
    }

    public void setidNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String date) {
        this.status = status;
    }
}

