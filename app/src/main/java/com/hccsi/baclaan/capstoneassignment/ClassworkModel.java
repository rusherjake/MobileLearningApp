package com.hccsi.baclaan.capstoneassignment;

public class ClassworkModel {
    // Variable to store data corresponding
    // to tname keyword in database
    private String auth;
    private String classid;
    private String classworkid;
    private String date;
    private String details;
    private String duedate;
    private String link;
    private String remark;
    private String grade;
    private String studentname;


    // Mandatory empty constructor
    // for use of FirebaseUI
    public ClassworkModel() {}

    // Getter and setter method
    public String getauth()
    {
        return auth;
    }
    public void setauth(String auth)
    {
        this.auth = auth;
    }

    public String getclassid()
    {
        return classid;
    }
    public void setclassid(String classid)
    {
        this.classid = classid;
    }

    public String getclassworkid()
    {
        return classworkid;
    }
    public void setclassworkid(String classworkid)
    {
        this.classworkid = classworkid;
    }

    public String getdate()
    {
        return date;
    }
    public void setdate(String date)
    {
        this.date = date;
    }

    public String getdetails()
    {
        return details;
    }
    public void setdetails(String details)
    {
        this.details = details;
    }

    public String getduedate()
    {
        return duedate;
    }
    public void setduedate(String duedate)
    {
        this.duedate = duedate;
    }

    public String getlink()
    {
        return link;
    }
    public void setlink(String link)
    {
        this.link = link;
    }

    public String getremark()
    {
        return remark;
    }
    public void setremark(String remark)
    {
        this.remark = remark;
    }

    public String getgrade()
    {
        return grade;
    }
    public void setgrade(String grade)
    {
        this.grade = grade;
    }

    public String getstudentname()
    {
        return studentname;
    }
    public void setstudentname(String studentname)
    {
        this.studentname = studentname;
    }

}
