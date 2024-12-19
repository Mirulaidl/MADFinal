package com.example.madfinal;

public class user {
    public String email,password,fullname,phone,ic;

    public user(){

    }

    public user(String email, String password, String fullname, String phone, String ic) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.ic = ic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }
}
