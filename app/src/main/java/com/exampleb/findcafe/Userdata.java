package com.exampleb.findcafe;

public class Userdata {
    String fullname,username,phoneno,password,email;

    public Userdata(){

    }

    public Userdata(String fullname, String username, String phoneno, String password, String email) {
        this.fullname = fullname;
        this.username = username;
        this.phoneno = phoneno;
        this.password = password;
        this.email = email;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
