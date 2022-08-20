package com.hccsi.baclaan.capstoneassignment;

public class StudentModel {


    // Variable to store data corresponding
    // to tname keyword in database
    private String name, course, password, status, year;
    private int idNumber;

    // Mandatory empty constructor
    // for use of FirebaseUI
    public StudentModel() {}

    // Getter and setter method
    public String getName()
    {
        return name;
    }
    public void setsname(String sname)
    {
        this.name = sname;
    }

    public String getCourse()
    {
        return course;
    }
    public void setscourse(String scourse)
    {
        this.course = scourse;
    }

    public String getPassword()
    {
        return password;
    }
    public void setspassword(String spassword)
    {
        this.password = spassword;
    }

    public String getStatus()
    {
        return status;
    }
    public void setsstatus(String sstatus)
    {
        this.status = sstatus;
    }

    public String getYear()
    {
        return year;
    }
    public void setsyear(String syear)
    {
        this.year = syear;
    }

    public int getidNumber()
    {
        return idNumber;
    }
    public void setsidNumber(int sidNumber)
    {
        this.idNumber = sidNumber;
    }
}