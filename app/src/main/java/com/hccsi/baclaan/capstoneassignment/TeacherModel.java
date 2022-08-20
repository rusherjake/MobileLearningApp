package com.hccsi.baclaan.capstoneassignment;

public class TeacherModel
{
    // Variable to store data corresponding
    // to tname keyword in database
    private String name, password, status;
    private int idNumber;

    // Mandatory empty constructor
    // for use of FirebaseUI
    public TeacherModel() {}

    // Getter and setter method
    public String getName()
    {
        return name;
    }
    public void settname(String tname)
    {
        this.name = tname;
    }
    public int getidNumber()
    {
        return idNumber;
    }
    public void settidNumber(int tidNumber)
    {
        this.idNumber = tidNumber;
    }
    public String getpassword()
    {
        return password;
    }
    public void settpassword(String tpassword)
    {
        this.password = tpassword;
    }
    public String getstatus()
    {
        return status;
    }
    public void settstatus(String tstatus)
    {
        this.status = tstatus;
    }
}




//public class TeacherModel
//{
//    String tidNumber, tname, tpassword,tstatus;
//    TeacherModel()
//    {
//
//    }
//    public TeacherModel(String tidNumber, String tname, String tpassword, String tstatus) {
//        this.tidNumber = tidNumber;
//        this.tname = tname;
//        this.tpassword = tpassword;
//        this.tstatus = tstatus;
//    }
//
//    public String getIdNumber()
//    {
//        return tidNumber;
//    }
//    public void setIdNumber(String tidNumber)
//    {
//        this.tidNumber = tidNumber;
//    }
//
//    public String getName()
//    {
//        return tname;
//    }
//
//    public void setName(String tname)
//    {
//        this.tname = tname;
//    }
//
//    public String getPassword()
//    {
//        return tpassword;
//    }
//
//    public void setPassword(String tpassword)
//    {
//        this.tpassword = tpassword;
//    }
//
//    public String getStatus()
//    {
//        return tstatus;
//    }
//
//    public void setStatus(String tstatus)
//    {
//        this.tstatus = tstatus;
//    }
//}
